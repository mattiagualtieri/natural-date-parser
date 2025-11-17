package com.guti.parser.pipeline.rule.rules;

import com.guti.parser.pipeline.ParseContext;
import com.guti.parser.pipeline.rule.Rule;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.TokenType;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import static com.guti.tokenizer.constant.TokenType.*;

public class AbsoluteDateRule extends Rule {

  private final List<List<TokenType>> patterns =
      List.of(
          List.of(ORDINAL, KEYWORD, MONTH, NUMBER),
          List.of(NUMBER, MONTH, NUMBER),
          List.of(MONTH, NUMBER, NUMBER),
          List.of(MONTH, ORDINAL, NUMBER),
          List.of(ORDINAL, KEYWORD, MONTH),
          List.of(NUMBER, MONTH),
          List.of(ORDINAL, KEYWORD, MONTH),
          List.of(MONTH, NUMBER),
          List.of(MONTH, ORDINAL));

  @Override
  public List<List<TokenType>> getPatterns() {
    return patterns;
  }

  @Override
  public boolean apply(ParseContext ctx, List<Token> tokens, int pos) {

    List<Token> slice = tokens.subList(pos, pos + this.length());

    Token dayToken = null;
    Token monthToken = null;
    Token yearToken = null;

    // Identify DAY (NUMBER or ORDINAL)
    if (slice.get(0).type() == NUMBER || slice.get(0).type() == ORDINAL) {
      dayToken = slice.get(0);
    } else if (slice.get(1).type() == NUMBER || slice.get(1).type() == ORDINAL) {
      dayToken = slice.get(1);
    }

    // Identify MONTH
    if (slice.get(0).type() == MONTH) {
      monthToken = slice.get(0);
    } else if (slice.get(1).type() == MONTH) {
      monthToken = slice.get(1);
    } else if (slice.size() > 2 && slice.get(2).type() == MONTH) {
      monthToken = slice.get(2);
    }

    // Identify YEAR (if present)
    if (slice.size() == 3) {
      if (slice.get(2).type() == NUMBER) {
        yearToken = slice.get(2);
      }
    } else if (slice.size() == 4) {
      if (slice.get(3).type() == NUMBER) {
        yearToken = slice.get(3);
      }
    }

    // Validate presence
    if (dayToken == null || monthToken == null) {
      return false;
    }

    // Validate "of" keyword
    if (slice.size() == 4
        && slice.get(1).type() == KEYWORD
        && !"of".equalsIgnoreCase(slice.get(1).value().toString())) {
      return false;
    }

    // Extract values
    int day = (Integer) dayToken.value();
    Month month = (Month) monthToken.value();
    Year year = (yearToken != null) ? Year.of((Integer) yearToken.value()) : null;
    MonthDay monthDay = MonthDay.of(month, day);

    ctx.setAbsoluteDate(monthDay, month, year);

    return true;
  }
}

package com.guti.parser.pipeline.rule.rules;

import com.guti.parser.pipeline.rule.Rule;
import com.guti.parser.pipeline.rule.pattern.Pattern;
import com.guti.tokenizer.word.KeywordWord;
import com.guti.tokenizer.word.KeywordWord.Keyword.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import static com.guti.tokenizer.TokenType.*;
import static com.guti.tokenizer.word.KeywordWord.Keyword.*;

public class WeekdayRule extends Rule {

  private final List<Pattern> patterns = List.of(getModifierWeekdayPattern(), getWeekdayPattern());

  @Override
  public List<Pattern> getPatterns() {
    return patterns;
  }

  private Pattern getModifierWeekdayPattern() {
    return Pattern.of(
        "MODIFIER_WEEKDAY",
        (tokens, ctx) -> {
          KeywordWord.Keyword modifier = (KeywordWord.Keyword) tokens.get(0).value();
          if (Set.of(THIS, NEXT, LAST).contains(modifier)) {
            ctx.setWeekdayModifier(modifier);
            DayOfWeek value = (DayOfWeek) tokens.get(1).value();
            ctx.setWeekday(value);
            return true;
          }
          return false;
        },
        List.of(KEYWORD, WEEKDAY));
  }

  private Pattern getWeekdayPattern() {
    return Pattern.of(
        "WEEKDAY",
        (tokens, ctx) -> {
          DayOfWeek value = (DayOfWeek) tokens.get(0).value();
          ctx.setWeekday(value);
          return true;
        },
        List.of(WEEKDAY));
  }
}

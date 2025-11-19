package com.guti.parser.pipeline.rule.rules;

import com.guti.parser.pipeline.rule.Rule;
import com.guti.parser.pipeline.rule.pattern.Pattern;
import com.guti.tokenizer.constant.Keyword;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.guti.tokenizer.constant.TokenType.*;

public class RelativeQuantityRule extends Rule {

  private final List<Pattern> patterns =
      List.of(getInNumberUnitPattern(), getNumberUnitAgoPattern());

  @Override
  public List<Pattern> getPatterns() {
    return patterns;
  }

  private Pattern getInNumberUnitPattern() {
    return Pattern.of(
        "IN_NUMBER_UNIT",
        (tokens, ctx) -> {
          if (tokens.get(0).value() == Keyword.IN) {
            Integer value = (Integer) tokens.get(1).value();
            ChronoUnit unit = (ChronoUnit) tokens.get(2).value();
            ctx.setRelative(value, unit);
            return true;
          }
          return false;
        },
        KEYWORD,
        NUMBER,
        UNIT);
  }

  private Pattern getNumberUnitAgoPattern() {
    return Pattern.of(
        "NUMBER_UNIT_AGO",
        (tokens, ctx) -> {
          if (tokens.get(2).value() == Keyword.AGO) {
            Integer value = (Integer) tokens.get(0).value();
            ChronoUnit unit = (ChronoUnit) tokens.get(1).value();
            ctx.setRelative(-value, unit);
            return true;
          }
          return false;
        },
        NUMBER,
        UNIT,
        KEYWORD);
  }
}

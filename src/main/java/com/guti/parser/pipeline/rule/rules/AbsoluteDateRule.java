package com.guti.parser.pipeline.rule.rules;

import com.guti.parser.pipeline.rule.Rule;
import com.guti.parser.pipeline.rule.pattern.Pattern;
import com.guti.tokenizer.constant.Keyword;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import static com.guti.tokenizer.constant.TokenType.*;

public class AbsoluteDateRule extends Rule {

  private final List<Pattern> patterns =
      List.of(
          getNumberOfMonthYearPattern(),
          getNumberMonthYearPattern(),
          getMonthNumberYearPattern(),
          getNumberOfMonthPattern(),
          getNumberMonthPattern(),
          getMonthNumberPattern());

  @Override
  public List<Pattern> getPatterns() {
    return patterns;
  }

  private Pattern getNumberOfMonthYearPattern() {
    return Pattern.of(
        "NUMBER_OF_MONTH_YEAR",
        (tokens, ctx) -> {
          int day = (Integer) tokens.get(0).value();
          if (Keyword.OF != tokens.get(1).value()) {
            return false;
          }
          Month month = (Month) tokens.get(2).value();
          Year year = Year.of((Integer) tokens.get(3).value());
          MonthDay monthDay = MonthDay.of(month, day);
          ctx.setAbsoluteDate(monthDay, month, year);
          return true;
        },
        NUMBER,
        KEYWORD,
        MONTH,
        YEAR);
  }

  private Pattern getNumberMonthYearPattern() {
    return Pattern.of(
        "NUMBER_MONTH_YEAR",
        (tokens, ctx) -> {
          int day = (Integer) tokens.get(0).value();
          Month month = (Month) tokens.get(1).value();
          Year year = Year.of((Integer) tokens.get(2).value());
          MonthDay monthDay = MonthDay.of(month, day);
          ctx.setAbsoluteDate(monthDay, month, year);
          return true;
        },
        NUMBER,
        MONTH,
        YEAR);
  }

  private Pattern getMonthNumberYearPattern() {
    return Pattern.of(
        "MONTH_NUMBER_YEAR",
        (tokens, ctx) -> {
          int day = (Integer) tokens.get(1).value();
          Month month = (Month) tokens.get(0).value();
          Year year = Year.of((Integer) tokens.get(2).value());
          MonthDay monthDay = MonthDay.of(month, day);
          ctx.setAbsoluteDate(monthDay, month, year);
          return true;
        },
        MONTH,
        NUMBER,
        YEAR);
  }

  private Pattern getNumberOfMonthPattern() {
    return Pattern.of(
        "NUMBER_OF_MONTH",
        (tokens, ctx) -> {
          int day = (Integer) tokens.get(0).value();
          if (Keyword.OF != tokens.get(1).value()) {
            return false;
          }
          Month month = (Month) tokens.get(2).value();
          MonthDay monthDay = MonthDay.of(month, day);
          ctx.setAbsoluteDate(monthDay, month, null);
          return true;
        },
        NUMBER,
        KEYWORD,
        MONTH);
  }

  private Pattern getNumberMonthPattern() {
    return Pattern.of(
        "NUMBER_MONTH",
        (tokens, ctx) -> {
          int day = (Integer) tokens.get(0).value();
          Month month = (Month) tokens.get(1).value();
          MonthDay monthDay = MonthDay.of(month, day);
          ctx.setAbsoluteDate(monthDay, month, null);
          return true;
        },
        NUMBER,
        MONTH);
  }

  private Pattern getMonthNumberPattern() {
    return Pattern.of(
        "MONTH_NUMBER",
        (tokens, ctx) -> {
          int day = (Integer) tokens.get(1).value();
          Month month = (Month) tokens.get(0).value();
          MonthDay monthDay = MonthDay.of(month, day);
          ctx.setAbsoluteDate(monthDay, month, null);
          return true;
        },
        MONTH,
        NUMBER);
  }
}

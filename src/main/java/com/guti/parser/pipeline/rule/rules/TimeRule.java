package com.guti.parser.pipeline.rule.rules;

import static com.guti.tokenizer.constant.Keyword.*;
import static com.guti.tokenizer.constant.TokenType.*;

import com.guti.parser.pipeline.rule.Rule;
import com.guti.parser.pipeline.rule.pattern.Pattern;
import com.guti.tokenizer.constant.MeridiemKeyword;

import java.time.LocalTime;
import java.util.List;

public class TimeRule extends Rule {

  private final List<Pattern> patterns =
      List.of(
          getAtHourMinuteMeridiemPattern(),
          getAtHourMinutePattern(),
          getAtTimeMeridiemPattern(),
          getAtNumberMeridiemPattern(),
          getHourMinuteMeridiemPattern(),
          getAtTimePattern(),
          getAtNumberPattern(),
          getAtTimeKeywordPattern(),
          getTimeMeridiemPattern(),
          getTimePattern(),
          getTimeKeywordPattern());

  @Override
  public List<Pattern> getPatterns() {
    return patterns;
  }

  private Pattern getAtHourMinuteMeridiemPattern() {
    return Pattern.of(
        "AT_HOUR_MINUTE_MERIDIEM",
        (tokens, ctx) -> {
          if (tokens.get(0).value() != AT) {
            return false;
          }
          Integer hours = (Integer) tokens.get(1).value();
          if (hours < 0 || hours > 24) {
            return false;
          }
          Integer minutes = (Integer) tokens.get(2).value();
          if (minutes < 0 || minutes > 59) {
            return false;
          }
          if (hours < 12 && tokens.get(3).value() == MeridiemKeyword.PM) {
            hours += 12;
          }
          LocalTime value = LocalTime.of(hours, minutes);
          ctx.setExplicitTime(value);
          return true;
        },
        KEYWORD,
        NUMBER,
        NUMBER,
        MERIDIEM);
  }

  private Pattern getAtHourMinutePattern() {
    return Pattern.of(
        "AT_HOUR_MINUTE",
        (tokens, ctx) -> {
          if (tokens.get(0).value() != AT) {
            return false;
          }
          Integer hours = (Integer) tokens.get(1).value();
          if (hours < 0 || hours > 24) {
            return false;
          }
          Integer minutes = (Integer) tokens.get(2).value();
          if (minutes < 0 || minutes > 59) {
            return false;
          }
          LocalTime value = LocalTime.of(hours, minutes);
          ctx.setExplicitTime(value);
          return true;
        },
        KEYWORD,
        NUMBER,
        NUMBER);
  }

  private Pattern getAtTimeMeridiemPattern() {
    return Pattern.of(
        "AT_TIME_MERIDIEM",
        (tokens, ctx) -> {
          if (tokens.get(0).value() != AT) {
            return false;
          }
          LocalTime value = (LocalTime) tokens.get(1).value();
          if (value.getHour() < 12 && tokens.get(2).value() == MeridiemKeyword.PM) {
            value = value.plusHours(12);
          }
          ctx.setExplicitTime(value);
          return true;
        },
        KEYWORD,
        TIME,
        MERIDIEM);
  }

  private Pattern getAtNumberMeridiemPattern() {
    return Pattern.of(
        "AT_NUMBER_MERIDIEM",
        (tokens, ctx) -> {
          if (tokens.get(0).value() != AT) {
            return false;
          }
          Integer number = (Integer) tokens.get(1).value();
          if (number < 12 && tokens.get(2).value() == MeridiemKeyword.PM) {
            number += 12;
          }
          LocalTime value = LocalTime.of(number, 0);
          ctx.setExplicitTime(value);
          return true;
        },
        KEYWORD,
        NUMBER,
        MERIDIEM);
  }

  private Pattern getHourMinuteMeridiemPattern() {
    return Pattern.of(
        "HOUR_MINUTE_MERIDIEM",
        (tokens, ctx) -> {
          Integer hours = (Integer) tokens.get(0).value();
          if (hours < 0 || hours > 24) {
            return false;
          }
          Integer minutes = (Integer) tokens.get(1).value();
          if (minutes < 0 || minutes > 59) {
            return false;
          }
          if (hours < 12 && tokens.get(2).value() == MeridiemKeyword.PM) {
            hours += 12;
          }
          LocalTime value = LocalTime.of(hours, minutes);
          ctx.setExplicitTime(value);
          return true;
        },
        NUMBER,
        NUMBER,
        MERIDIEM);
  }

  private Pattern getAtTimePattern() {
    return Pattern.of(
        "AT_TIME",
        (tokens, ctx) -> {
          if (tokens.get(0).value() != AT) {
            return false;
          }
          LocalTime value = (LocalTime) tokens.get(1).value();
          ctx.setExplicitTime(value);
          return true;
        },
        KEYWORD,
        TIME);
  }

  private Pattern getAtNumberPattern() {
    return Pattern.of(
        "AT_NUMBER",
        (tokens, ctx) -> {
          Integer number = (Integer) tokens.get(1).value();
          LocalTime value = LocalTime.of(number, 0);
          ctx.setExplicitTime(value);
          return true;
        },
        KEYWORD,
        TIME);
  }

  private Pattern getAtTimeKeywordPattern() {
    return Pattern.of(
        "AT_TIMEKEYWORD",
        (tokens, ctx) -> {
          if (tokens.get(0).value() != AT) {
            return false;
          }
          LocalTime value = (LocalTime) tokens.get(1).value();
          ctx.setExplicitTime(value);
          return true;
        },
        KEYWORD,
        TIME_KEYWORD);
  }

  private Pattern getTimeMeridiemPattern() {
    return Pattern.of(
        "TIME_MERIDIEM",
        (tokens, ctx) -> {
          LocalTime value = (LocalTime) tokens.get(0).value();
          if (value.getHour() < 12 && tokens.get(1).value() == MeridiemKeyword.PM) {
            value = value.plusHours(12);
          }
          ctx.setExplicitTime(value);
          return true;
        },
        TIME,
        MERIDIEM);
  }

  private Pattern getTimePattern() {
    return Pattern.of(
        "TIME",
        (tokens, ctx) -> {
          LocalTime value = (LocalTime) tokens.get(0).value();
          ctx.setExplicitTime(value);
          return true;
        },
        TIME);
  }

  private Pattern getTimeKeywordPattern() {
    return Pattern.of(
        "TIME",
        (tokens, ctx) -> {
          LocalTime value = (LocalTime) tokens.get(0).value();
          ctx.setExplicitTime(value);
          return true;
        },
        TIME_KEYWORD);
  }
}

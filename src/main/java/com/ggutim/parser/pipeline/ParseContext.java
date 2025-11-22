package com.ggutim.parser.pipeline;

import com.ggutim.tokenizer.word.KeywordWord.Keyword;

import java.time.DayOfWeek;
import java.time.LocalTime;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class ParseContext {

  // Reference
  private final LocalDateTime reference;

  // Absolute date
  private MonthDay day = null;
  private Month month = null; // 1–12
  private Year year = null;

  // Relative date
  public Integer relativeDays = null; // e.g. +1 (tomorrow), -2, etc.
  public Integer relativeWeeks = null; // e.g. "in 2 weeks"
  public Integer relativeMonths = null;
  public Integer relativeYears = null;

  // Weekday
  public DayOfWeek weekday = null;
  public Keyword weekdayModifier = null; // WeekModifier: THIS, NEXT, LAST

  // Time
  public LocalTime explicitTime = null;
  public Integer relativeMinutes = null;
  public Integer relativeHours = null;

  public ParseContext(LocalDateTime reference) {
    this.reference = reference;
  }

  public void setAbsoluteDate(MonthDay day, Month month, Year year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public boolean hasAbsoluteDate() {
    return day != null || month != null || year != null;
  }

  public void setRelative(Integer value, ChronoUnit unit) {
    switch (unit) {
      case MINUTES -> relativeMinutes = value;
      case HOURS -> relativeHours = value;
      case DAYS -> relativeDays = value;
      case WEEKS -> relativeWeeks = value;
      case MONTHS -> relativeMonths = value;
      case YEARS -> relativeYears = value;
    }
  }

  public Integer getRelative(ChronoUnit unit) {
    switch (unit) {
      case MINUTES -> {
        return relativeMinutes;
      }
      case HOURS -> {
        return relativeHours;
      }
      case DAYS -> {
        return relativeDays;
      }
      case WEEKS -> {
        return relativeWeeks;
      }
      case MONTHS -> {
        return relativeMonths;
      }
      case YEARS -> {
        return relativeYears;
      }
    }
    return 0;
  }

  public void setWeekday(DayOfWeek value) {
    this.weekday = value;
  }

  public DayOfWeek getWeekday() {
    return weekday;
  }

  public void setWeekdayModifier(Keyword value) {
    this.weekdayModifier = value;
  }

  public Keyword getWeekdayModifier() {
    return weekdayModifier;
  }

  public void setExplicitTime(LocalTime value) {
    this.explicitTime = value;
  }

  public LocalTime getExplicitTime() {
    return explicitTime;
  }

  // ========= Final resolution =========

  public LocalDateTime resolve() {

    LocalDate baseDate = reference.toLocalDate();

    // 1) START WITH ABSOLUTE DATE (highest priority)
    LocalDate date = resolveAbsoluteDate(baseDate);

    // 2) APPLY RELATIVE OFFSETS
    date = applyRelativeOffsets(date);

    // 3) APPLY WEEKDAY RULES
    date = applyWeekdayModifier(date);

    // 4) RESOLVE TIME
    LocalTime time = resolveTime(reference.toLocalTime());

    return LocalDateTime.of(date, time);
  }

  // --------------------------------------------
  // ABSOLUTE DATE RESOLUTION
  // --------------------------------------------

  private LocalDate resolveAbsoluteDate(LocalDate baseDate) {
    if (!hasAbsoluteDate()) return baseDate;

    int y = (year != null) ? year.getValue() : baseDate.getYear();
    int m = (month != null) ? month.getValue() : baseDate.getMonthValue();
    int d = (day != null) ? day.getDayOfMonth() : baseDate.getDayOfMonth();

    return LocalDate.of(y, m, d);
  }

  // --------------------------------------------
  // RELATIVE OFFSETS
  // --------------------------------------------

  private LocalDate applyRelativeOffsets(LocalDate date) {

    if (relativeDays != null) date = date.plusDays(relativeDays);

    if (relativeWeeks != null) date = date.plusWeeks(relativeWeeks);

    if (relativeMonths != null) date = date.plusMonths(relativeMonths);

    if (relativeYears != null) date = date.plusYears(relativeYears);

    return date;
  }

  // --------------------------------------------
  // WEEKDAY RESOLUTION
  // --------------------------------------------

  private LocalDate applyWeekdayModifier(LocalDate date) {
    if (weekday == null) return date;

    int targetDow = weekday.getValue();
    int currentDow = date.getDayOfWeek().getValue();

    int diff = targetDow - currentDow;

    if (weekdayModifier == null) {
      // Default: next occurrence if today is past
      if (diff < 0) diff += 7;
      return date.plusDays(diff);
    }

    return switch (weekdayModifier) {
      case THIS -> {
        if (diff < 0) diff += 7; // move to same week forward
        yield date.plusDays(diff); // move to same week forward
      }
      case NEXT -> {
        if (diff <= 0) diff += 7; // always next week
        yield date.plusDays(diff); // always next week
      }
      case LAST -> {
        if (diff >= 0) diff -= 7;
        yield date.plusDays(diff);
      }
      default -> date;
    };
  }

  // --------------------------------------------
  // TIME RESOLUTION
  // --------------------------------------------

  private LocalTime resolveTime(LocalTime referenceTime) {
    LocalTime time = (explicitTime != null) ? explicitTime : referenceTime;

    if (relativeMinutes != null) time = time.plusMinutes(relativeMinutes);

    if (relativeHours != null) time = time.plusHours(relativeHours);

    return time;
  }
}

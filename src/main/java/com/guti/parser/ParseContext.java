package com.guti.parser;

import java.time.DayOfWeek;
import java.time.LocalTime;

import java.time.*;

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
  public WeekModifier weekdayModifier = null; // WeekModifier: THIS, NEXT, LAST

  // Time
  public LocalTime explicitTime = null;
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

  public void setRelativeDays(Integer relativeDays) {
    this.relativeDays = relativeDays;
  }

  public Integer getRelativeDays() {
    return relativeDays;
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

    switch (weekdayModifier) {
      case THIS:
        if (diff < 0) diff += 7; // move to same week forward
        return date.plusDays(diff);

      case NEXT:
        if (diff <= 0) diff += 7; // always next week
        return date.plusDays(diff);

      case LAST:
        if (diff >= 0) diff -= 7;
        return date.plusDays(diff);

      default:
        return date;
    }
  }

  // --------------------------------------------
  // TIME RESOLUTION
  // --------------------------------------------

  private LocalTime resolveTime(LocalTime referenceTime) {
    LocalTime time = (explicitTime != null) ? explicitTime : referenceTime;

    if (relativeHours != null) time = time.plusHours(relativeHours);

    return time;
  }

  // --------------------------------------------
  // ENUM for weekday modifiers
  // --------------------------------------------
  public enum WeekModifier {
    THIS,
    NEXT,
    LAST
  }
}

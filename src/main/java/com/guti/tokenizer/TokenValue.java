package com.guti.tokenizer;

public enum TokenValue {

  // Number (1, one, ...)
  NUMBER,

  // Ordinal (1st, 2nd, ...)
  ORDINAL,

  // Month (January, Jan, ...)
  MONTH,

  // Year (2026, '99, ...)
  YEAR,

  // Weekday (monday, mon, ...)
  WEEKDAY,

  // Time (12:00, 17, midnight, ...)
  TIME,

  // Meridiem (AM, a.m., ...)
  MERIDIEM,

  // Unit (hours, weeks, ...)
  UNIT,

  // Keywords (in, ago, next, ...)
  AT,
  IN,
  AGO,
  FROM,
  DAY,
  AFTER,
  BEFORE,
  NEXT,
  THIS,
  PREVIOUS,
  LAST,

  // Special words (today, tomorrow, now, ...)
  TODAY,
  TOMORROW,
  YESTERDAY,
  NOW,

  // Others
  UNKNOWN,
  EOF
}

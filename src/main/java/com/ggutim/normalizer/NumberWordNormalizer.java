package com.ggutim.normalizer;

import java.util.Map;

public final class NumberWordNormalizer implements Normalizer {

  public static final Map<String, Integer> NUMBER_WORDS =
      Map.ofEntries(
          // 1–20
          Map.entry("zero", 0),
          Map.entry("one", 1),
          Map.entry("a", 1),
          Map.entry("an", 1),
          Map.entry("two", 2),
          Map.entry("three", 3),
          Map.entry("four", 4),
          Map.entry("five", 5),
          Map.entry("six", 6),
          Map.entry("seven", 7),
          Map.entry("eight", 8),
          Map.entry("nine", 9),
          Map.entry("ten", 10),
          Map.entry("eleven", 11),
          Map.entry("twelve", 12),
          Map.entry("thirteen", 13),
          Map.entry("fourteen", 14),
          Map.entry("fifteen", 15),
          Map.entry("sixteen", 16),
          Map.entry("seventeen", 17),
          Map.entry("eighteen", 18),
          Map.entry("nineteen", 19),
          Map.entry("twenty", 20),

          // Tens
          Map.entry("thirty", 30),
          Map.entry("forty", 40),
          Map.entry("fifty", 50),
          Map.entry("sixty", 60),
          Map.entry("seventy", 70),
          Map.entry("eighty", 80),
          Map.entry("ninety", 90));

  @Override
  public String normalize(String input) {
    StringBuilder result = new StringBuilder();

    String[] tokens = input.split("\\s+");

    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];

      if (token.contains("-")) {
        String[] parts = token.split("-");
        if (parts.length == 2
            && NUMBER_WORDS.containsKey(parts[0].toLowerCase())
            && NUMBER_WORDS.containsKey(parts[1].toLowerCase())) {

          int value =
              NUMBER_WORDS.get(parts[0].toLowerCase()) + NUMBER_WORDS.get(parts[1].toLowerCase());
          result.append(value);
        } else {
          result.append(token);
        }

      } else {
        Integer number = NUMBER_WORDS.get(token.toLowerCase());
        if (number != null) {
          result.append(number);
        } else {
          result.append(token);
        }
      }

      if (i < tokens.length - 1) {
        result.append(" ");
      }
    }

    return result.toString();
  }
}

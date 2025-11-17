package com.guti.normalizer;

import java.util.List;

public final class MultiWordNormalizer implements Normalizer {

  public static final List<String> EXPRESSIONS =
      List.of("day after tomorrow", "day before yesterday");

  @Override
  public String normalize(String input) {
    for (String multiWordExp : EXPRESSIONS) {
      String underscored = multiWordExp.replace(" ", "_");
      input = input.replace(multiWordExp, underscored);
    }
    return input;
  }
}

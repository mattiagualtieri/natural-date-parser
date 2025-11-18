package com.guti.normalizer;

public final class WhitespaceNormalizer implements Normalizer {

  @Override
  public String normalize(String input) {
    // Remove whitespaces
    return input.replaceAll("\\s+", " ").trim();
  }
}

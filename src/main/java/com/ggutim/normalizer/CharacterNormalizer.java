package com.ggutim.normalizer;

public final class CharacterNormalizer implements Normalizer {

  @Override
  public String normalize(String input) {
    // Keep only letters, digits, '/', ':', '-', and '.'
    return input.replaceAll("[^A-Za-z0-9\\s/:\\-.]", " ").replaceAll("\\s+", " ").trim();
  }
}

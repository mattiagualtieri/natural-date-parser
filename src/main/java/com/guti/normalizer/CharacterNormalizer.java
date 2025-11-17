package com.guti.normalizer;

public class CharacterNormalizer implements Normalizer {

  @Override
  public String normalize(String input) {
    // Keep only letters, digits, single spaces, '/', ':', '-', and '.'
    return input.replaceAll("[^A-Za-z0-9\\s/:\\-.]", " ").replaceAll("\\s+", " ").trim();
  }
}

package com.guti.normalizer;

public class LowerCaseNormalizer implements Normalizer {

  @Override
  public String normalize(String input) {
    return input.toLowerCase();
  }
}

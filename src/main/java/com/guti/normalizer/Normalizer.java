package com.guti.normalizer;

public class Normalizer {

  // TODO: synonym maps, lemmatization, multi-word merging

  public String normalize(String input) {
    input = input.trim().toLowerCase().replaceAll("[,]", "");
    for (String multiWordExp : MultiWordExpressions.EXPRESSIONS) {
      String underscored = multiWordExp.replace(" ", "_"); // TODO: this can be avoid
      input = input.replace(multiWordExp, underscored);
    }

    return input;
  }
}

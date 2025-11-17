package com.guti.tokenizer;

import com.guti.normalizer.Normalizer;
import com.guti.tokenizer.word.WordTokenizer;

import java.time.*;
import java.util.*;
import java.util.regex.*;

public final class Tokenizer {

  private final Normalizer normalizer;
  private final WordTokenizer wordTokenizer;

  public Tokenizer(Normalizer normalizer, WordTokenizer wordTokenizer) {
    this.normalizer = normalizer;
    this.wordTokenizer = wordTokenizer;
  }

  public List<Token> tokenize(String input) {
    String cleaned = normalizer.normalize(input);
    String[] parts = cleaned.split("\\s+");

    List<Token> tokens = new ArrayList<>();

    for (String part : parts) {
      tokens.add(wordTokenizer.tokenize(part));
    }

    return tokens;
  }
}

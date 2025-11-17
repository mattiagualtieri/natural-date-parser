package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;
import org.junit.jupiter.api.Test;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordTokenizerTest {

  private final WordTokenizer wordTokenizer = new WordTokenizer();

  @Test
  void tokenizeMonth() {
    Token token = wordTokenizer.tokenize("february");
    assertEquals(TokenType.MONTH, token.getType());
    assertEquals("february", token.getText());
    assertEquals(Month.FEBRUARY, token.getValue());
  }
}

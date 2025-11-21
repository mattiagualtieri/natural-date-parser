package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;

public interface Word {

  boolean match(String word);

  Token tokenize(String word);
}

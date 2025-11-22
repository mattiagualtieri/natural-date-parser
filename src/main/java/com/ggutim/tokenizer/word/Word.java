package com.ggutim.tokenizer.word;

import com.ggutim.tokenizer.Token;

public interface Word {

  boolean match(String word);

  Token tokenize(String word);
}

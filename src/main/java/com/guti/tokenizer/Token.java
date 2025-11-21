package com.guti.tokenizer;

import java.util.Objects;

public record Token(TokenType type, String text, Object value) {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Token other)) {
      return false;
    }
    return type == other.type
        && Objects.equals(text, other.text)
        && Objects.equals(value, other.value);
  }
}

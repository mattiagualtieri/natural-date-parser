package com.guti.tokenizer;

public class Token {

  private final TokenType type;
  private final String text;
  private final Object value;

  public Token(TokenType type, String text, Object value) {
    this.type = type;
    this.text = text;
    this.value = value;
  }

  public TokenType getType() {
    return type;
  }

  public String getText() {
    return text;
  }

  public Object getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Token(" + type + ", '" + text + "', " + value + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Token other)) {
      return false;
    }
    return type == other.type
        && java.util.Objects.equals(text, other.text)
        && java.util.Objects.equals(value, other.value);
  }
}

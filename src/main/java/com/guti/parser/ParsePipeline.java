package com.guti.parser;

import com.guti.parser.rule.AbsoluteDateRule;
import com.guti.parser.rule.RelativeDateKeywordRule;
import com.guti.parser.rule.Rule;
import com.guti.tokenizer.Token;

import java.time.LocalDateTime;
import java.util.List;

public class ParsePipeline {

  private final List<Rule> rules = List.of(new AbsoluteDateRule(), new RelativeDateKeywordRule());

  public LocalDateTime parse(List<Token> tokens) {
    ParseContext ctx = new ParseContext(LocalDateTime.now());

    int pos = 0;
    while (pos < tokens.size()) {
      boolean matched = false;

      for (Rule rule : rules) {
        if (rule.matches(tokens, pos)) {
          rule.apply(ctx, tokens, pos);
          pos += rule.length();
          matched = true;
          break;
        }
      }

      if (!matched) pos++;
    }

    return ctx.resolve();
  }
}

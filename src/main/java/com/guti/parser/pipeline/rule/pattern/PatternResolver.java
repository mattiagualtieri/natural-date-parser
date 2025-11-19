package com.guti.parser.pipeline.rule.pattern;

import com.guti.parser.pipeline.ParseContext;
import com.guti.tokenizer.Token;

import java.util.List;

@FunctionalInterface
public interface PatternResolver {

  boolean resolve(List<Token> tokens, ParseContext ctx);
}

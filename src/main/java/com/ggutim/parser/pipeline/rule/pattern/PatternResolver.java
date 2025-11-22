package com.ggutim.parser.pipeline.rule.pattern;

import com.ggutim.parser.pipeline.ParseContext;
import com.ggutim.tokenizer.Token;

import java.util.List;

@FunctionalInterface
public interface PatternResolver {

  boolean resolve(List<Token> tokens, ParseContext ctx);
}

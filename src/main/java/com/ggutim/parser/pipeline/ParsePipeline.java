package com.ggutim.parser.pipeline;

import com.ggutim.tokenizer.Token;

import java.time.LocalDateTime;
import java.util.List;

public interface ParsePipeline {

  LocalDateTime parse(List<Token> tokens, LocalDateTime reference);
}

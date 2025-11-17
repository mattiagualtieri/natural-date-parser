package com.guti.parser.pipeline;

import com.guti.tokenizer.Token;

import java.time.LocalDateTime;
import java.util.List;

public interface ParsePipeline {

  LocalDateTime parse(List<Token> tokens, LocalDateTime reference);
}

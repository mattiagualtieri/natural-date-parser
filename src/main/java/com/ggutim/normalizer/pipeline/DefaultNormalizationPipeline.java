package com.ggutim.normalizer.pipeline;

import com.ggutim.normalizer.*;

import java.util.List;

public class DefaultNormalizationPipeline extends NormalizationPipeline {

  @Override
  public List<Normalizer> getNormalizers() {
    return List.of(
        new LowerCaseNormalizer(),
        new CharacterNormalizer(),
        new WhitespaceNormalizer(),
        new NumberWordNormalizer(),
        new MultiWordNormalizer());
  }
}

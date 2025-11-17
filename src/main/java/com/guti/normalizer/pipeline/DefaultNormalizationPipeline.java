package com.guti.normalizer.pipeline;

import com.guti.normalizer.*;

import java.util.List;

public class DefaultNormalizationPipeline extends NormalizationPipeline {

  @Override
  public List<Normalizer> getNormalizers() {
    return List.of(new LowerCaseNormalizer(), new CharacterNormalizer(), new MultiWordNormalizer());
  }
}

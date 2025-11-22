package com.ggutim.normalizer.pipeline;

import com.ggutim.normalizer.Normalizer;

import java.util.Collections;
import java.util.List;

public abstract class NormalizationPipeline {

  List<Normalizer> getNormalizers() {
    return Collections.emptyList();
  }

  public String normalize(String input) {
    for (Normalizer normalizer : getNormalizers()) {
      input = normalizer.normalize(input);
    }
    return input;
  }
}

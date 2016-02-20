package org.coner.crispy_fish.datatype.underscore_pairs;

public interface UnderscorePairReader<P> {

    void setPairs(P pairs);

    String get(String key);
}

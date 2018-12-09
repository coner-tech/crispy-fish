package org.coner.crispy_fish.datatype.underscore_pairs

interface UnderscorePairReader<P> {

    operator fun get(pairs: P, key: String): String
}

package org.coner.crispy_fish.datatype.underscore_pairs

interface UnderscorePairReader<P : Any> {

    fun get(pairs: P, key: String): String?
}

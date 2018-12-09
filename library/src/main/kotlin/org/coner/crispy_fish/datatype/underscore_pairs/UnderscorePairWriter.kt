package org.coner.crispy_fish.datatype.underscore_pairs

interface UnderscorePairWriter<P> {

    fun pair(key: String, value: String): P
}

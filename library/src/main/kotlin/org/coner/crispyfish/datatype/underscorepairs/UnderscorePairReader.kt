package org.coner.crispyfish.datatype.underscorepairs

interface UnderscorePairReader<P : Any> {

    fun get(pairs: P, key: String): String?
}

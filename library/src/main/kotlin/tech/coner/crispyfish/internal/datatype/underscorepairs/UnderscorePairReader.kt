package tech.coner.crispyfish.internal.datatype.underscorepairs

internal interface UnderscorePairReader<P : Any> {

    fun get(pairs: P, key: String): String?
}

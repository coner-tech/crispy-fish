package tech.coner.crispyfish.internal.datatype.underscorepairs

internal interface UnderscorePairWriter<P> {

    fun pair(key: String, value: String?): P
}

package tech.coner.crispyfish.internal.datatype.underscorepairs

interface UnderscorePairWriter<P> {

    fun pair(key: String, value: String?): P
}

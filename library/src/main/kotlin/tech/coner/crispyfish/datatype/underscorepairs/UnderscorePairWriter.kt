package tech.coner.crispyfish.datatype.underscorepairs

interface UnderscorePairWriter<P> {

    fun pair(key: String, value: String?): P
}

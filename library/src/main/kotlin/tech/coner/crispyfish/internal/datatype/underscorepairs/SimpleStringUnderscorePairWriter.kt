package tech.coner.crispyfish.internal.datatype.underscorepairs

internal class SimpleStringUnderscorePairWriter : UnderscorePairWriter<String> {

    override fun pair(key: String, value: String?): String {
        val writtenValue = value ?: ""
        return "_${key}_$writtenValue"
    }
}

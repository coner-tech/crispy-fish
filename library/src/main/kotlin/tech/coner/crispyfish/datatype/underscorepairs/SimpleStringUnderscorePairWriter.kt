package tech.coner.crispyfish.datatype.underscorepairs

class SimpleStringUnderscorePairWriter : UnderscorePairWriter<String> {

    override fun pair(key: String, value: String?): String {
        val writtenValue = value ?: ""
        return "_${key}_$writtenValue"
    }
}

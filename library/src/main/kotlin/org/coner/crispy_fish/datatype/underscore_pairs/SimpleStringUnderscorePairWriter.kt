package org.coner.crispy_fish.datatype.underscore_pairs

class SimpleStringUnderscorePairWriter : UnderscorePairWriter<String> {

    override fun pair(key: String, value: String?): String {
        val writtenValue = value ?: ""
        return "_${key}_$writtenValue"
    }
}

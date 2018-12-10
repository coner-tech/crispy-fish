package org.coner.crispy_fish.filetype.classdef

class SimpleStringSplittingClassDefLineReader(fields: MutableList<String>? = null) : ClassDefLineReader {

    var line: String? = null
        set(value) {
            fields.clear()
            if (value == null || value.isBlank()) return
            val fieldsArray = value.split("\t".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            fields.addAll(fieldsArray)
            field = value
        }
    private val fields = fields ?: mutableListOf()

    override val classAbbreviation: String
        get() = fields[ClassDefIndices.CLASS_ABBREVIATION]

    override val pax: String
        get() = fields[ClassDefIndices.PAX]

    override val mysteryIndicatorAtIndex2: String
        get() = throw UnsupportedOperationException()

    override val className: String
        get() = fields[ClassDefIndices.CLASS_NAME]

    override val mysteryIndicatorAtIndex4: String
        get() = throw UnsupportedOperationException()

    override val mysteryIndicatorAtIndex5: String
        get() = throw UnsupportedOperationException()

    override val mysteryIndicatorAtIndex6: String
        get() = throw UnsupportedOperationException()

    override val category: String
        get() = fields[ClassDefIndices.CATEGORY]

}

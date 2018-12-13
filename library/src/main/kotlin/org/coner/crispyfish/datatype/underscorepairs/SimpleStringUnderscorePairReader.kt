package org.coner.crispyfish.datatype.underscorepairs

class SimpleStringUnderscorePairReader : UnderscorePairReader<String> {

    override fun get(pairs: String, key: String): String? {
        val keyStartPosition = pairs.indexOf(key)
        if (keyStartPosition < 0) {
            // pairs doesn't contain key
            return null
        }

        val keyLength = key.length
        val valueStartPosition = keyStartPosition + keyLength + 1 // +1 to account for the trailing _
        val valueStopPosition = pairs.indexOf('_', valueStartPosition)
        return if (valueStopPosition >= 0) {
            pairs.substring(valueStartPosition, valueStopPosition)
        } else {
            pairs.substring(valueStartPosition)
        }
    }
}

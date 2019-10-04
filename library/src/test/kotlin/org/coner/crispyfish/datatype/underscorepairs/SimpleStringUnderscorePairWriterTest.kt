package org.coner.crispyfish.datatype.underscorepairs

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class SimpleStringUnderscorePairWriterTest {

    private lateinit var writer: SimpleStringUnderscorePairWriter

    @Before
    fun setup() {
        writer = SimpleStringUnderscorePairWriter()
    }

    @Test
    fun whenPairValidKeyWithValidValueItShouldWrite() {
        val actual = writer.pair("key", "value")

        assertThat(actual).isEqualTo("_key_value")
    }

    @Test
    fun whenPairValidKeyWithNullValueItShouldWrite() {
        val actual = writer.pair("key", null)

        assertThat(actual).isEqualTo("_key_")
    }

}
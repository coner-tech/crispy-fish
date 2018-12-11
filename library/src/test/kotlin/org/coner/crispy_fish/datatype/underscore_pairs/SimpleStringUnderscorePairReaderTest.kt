package org.coner.crispy_fish.datatype.underscore_pairs

import org.junit.*

import org.assertj.core.api.Assertions.assertThat

class SimpleStringUnderscorePairReaderTest {

    private lateinit var reader: SimpleStringUnderscorePairReader

    private val PAIRS_EXAMPLE_BASIC = "key1_value1_key2_value2_key3_value3"

    @Before
    fun setup() {
        reader = SimpleStringUnderscorePairReader()
    }

    @Test
    fun whenGetFirstKeyItShouldReturnCorrectValue() {
        val actual = reader.get(PAIRS_EXAMPLE_BASIC, "key1")

        assertThat(actual).isEqualTo("value1")
    }

    @Test
    fun whenGetInnerKeyItShouldReturnCorrectValue() {
        val actual = reader.get(PAIRS_EXAMPLE_BASIC, "key2")

        assertThat(actual).isEqualTo("value2")
    }

    @Test
    fun whenGetLastKeyItShouldReturnCorrectValue() {
        val actual = reader.get(PAIRS_EXAMPLE_BASIC, "key3")

        assertThat(actual).isEqualTo("value3")
    }

    @Test
    fun whenGetKeyDoesNotExistItShouldReturnNull() {
        val actual = reader.get(PAIRS_EXAMPLE_BASIC, "absent")

        assertThat(actual).isNull()
    }

}

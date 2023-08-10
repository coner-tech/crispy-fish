package tech.coner.crispyfish.datatype.underscorepairs

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SimpleStringUnderscorePairWriterTest {

    private lateinit var writer: SimpleStringUnderscorePairWriter

    @BeforeEach
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
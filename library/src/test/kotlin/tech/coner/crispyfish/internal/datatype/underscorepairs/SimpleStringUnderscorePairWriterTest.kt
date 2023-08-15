package tech.coner.crispyfish.internal.datatype.underscorepairs

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.internal.datatype.underscorepairs.SimpleStringUnderscorePairWriter

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
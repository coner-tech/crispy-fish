package org.coner.crispy_fish.datatype.underscore_pairs;

import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UnderscorePairWriterTest {

    private UnderscorePairWriter writer;

    @Before
    public void setup() {
        writer = new UnderscorePairWriter();
    }

    @Test(expected = AssertionError.class)
    public void whenPairKeyIsNullItShouldThrow() throws Exception {
        writer.pair(null, "foo");
    }

    @Test
    public void whenPairValidKeyWithValidValueItShouldWrite() {
        String actual = writer.pair("key", "value");

        assertThat(actual).isEqualTo("_key_value");
    }

    @Test
    public void whenPairValidKeyWithNullValueItShouldWrite() {
        String actual = writer.pair("key", null);

        assertThat(actual).isEqualTo("_key_");
    }

}
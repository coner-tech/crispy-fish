package org.coner.crispy_fish.datatype.underscore_pairs;

import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UnderscorePairReaderTest {

    private static final String PAIRS_EXAMPLE_BASIC = "key1_value1_key2_value2_key3_value3";

    private UnderscorePairReader reader;

    @Before
    public void setup() {
        reader = new UnderscorePairReader();
    }

    @Test(expected = AssertionError.class)
    public void whenGetWithNullPairsItShouldThrow() throws Exception {
        reader.get("irrelevant");
    }

    @Test
    public void whenGetFirstKeyItShouldReturnCorrectValue() throws Exception {
        reader.setPairs(PAIRS_EXAMPLE_BASIC);

        String actual = reader.get("key1");

        assertThat(actual).isEqualTo("value1");
    }

    @Test
    public void whenGetInnerKeyItShouldReturnCorrectValue() throws Exception {
        reader.setPairs(PAIRS_EXAMPLE_BASIC);

        String actual = reader.get("key2");

        assertThat(actual).isEqualTo("value2");
    }

    @Test
    public void whenGetLastKeyItShouldReturnCorrectValue() throws Exception {
        reader.setPairs(PAIRS_EXAMPLE_BASIC);

        String actual = reader.get("key3");

        assertThat(actual).isEqualTo("value3");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetNullKeyItShouldThrow() throws Exception {
        reader.setPairs(PAIRS_EXAMPLE_BASIC);

        reader.get(null);
    }

    @Test
    public void whenGetKeyDoesNotExistItShouldReturnNull() throws Exception {
        reader.setPairs(PAIRS_EXAMPLE_BASIC);

        String actual = reader.get("absent");

        assertThat(actual).isNull();
    }
}

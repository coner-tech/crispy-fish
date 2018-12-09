package org.crispy_fish.datatype.underscore_pairs;

import org.coner.crispy_fish.datatype.underscore_pairs.SimpleStringUnderscorePairReader;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleStringUnderscorePairReaderTest {

    private static final String PAIRS_EXAMPLE_BASIC = "key1_value1_key2_value2_key3_value3";

    private SimpleStringUnderscorePairReader reader;

    @Before
    public void setup() {
        reader = new SimpleStringUnderscorePairReader();
    }

    @Test
    public void whenGetFirstKeyItShouldReturnCorrectValue() throws Exception {
        String actual = reader.get(PAIRS_EXAMPLE_BASIC, "key1");

        assertThat(actual).isEqualTo("value1");
    }

    @Test
    public void whenGetInnerKeyItShouldReturnCorrectValue() throws Exception {
        String actual = reader.get(PAIRS_EXAMPLE_BASIC, "key2");

        assertThat(actual).isEqualTo("value2");
    }

    @Test
    public void whenGetLastKeyItShouldReturnCorrectValue() throws Exception {
        String actual = reader.get(PAIRS_EXAMPLE_BASIC, "key3");

        assertThat(actual).isEqualTo("value3");
    }

    @Test
    public void whenGetKeyDoesNotExistItShouldReturnNull() throws Exception {
        String actual = reader.get(PAIRS_EXAMPLE_BASIC, "absent");

        assertThat(actual).isNull();
    }
}

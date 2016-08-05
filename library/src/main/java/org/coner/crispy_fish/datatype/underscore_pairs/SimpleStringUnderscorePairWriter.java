package org.coner.crispy_fish.datatype.underscore_pairs;

import static org.coner.crispy_fish.datatype.underscore_pairs.Constants.*;

public class SimpleStringUnderscorePairWriter implements UnderscorePairWriter<String> {

    public String pair(String key, String value) {
        assert key != null;

        if (value == null) {
            value = "";
        }

        return UNDERSCORE + key + UNDERSCORE + value;
    }
}

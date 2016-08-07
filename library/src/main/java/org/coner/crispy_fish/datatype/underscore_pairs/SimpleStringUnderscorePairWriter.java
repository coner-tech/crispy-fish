package org.coner.crispy_fish.datatype.underscore_pairs;

import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import static org.coner.crispy_fish.datatype.underscore_pairs.Constants.*;

public class SimpleStringUnderscorePairWriter implements UnderscorePairWriter<String> {

    public String pair(@NotNull String key, @Nullable String value) {
        Preconditions.checkNotNull(key, "key must not be null");

        if (value == null) {
            value = "";
        }

        return UNDERSCORE + key + UNDERSCORE + value;
    }
}

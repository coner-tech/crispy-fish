package org.crispy_fish.datatype.underscore_pairs;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static org.crispy_fish.datatype.underscore_pairs.Constants.UNDERSCORE;

public class SimpleStringUnderscorePairWriter implements UnderscorePairWriter<String> {

    public String pair(@Nonnull String key, @Nullable String value) {
        Preconditions.checkNotNull(key, "key must not be null");

        if (value == null) {
            value = "";
        }

        return UNDERSCORE + key + UNDERSCORE + value;
    }
}

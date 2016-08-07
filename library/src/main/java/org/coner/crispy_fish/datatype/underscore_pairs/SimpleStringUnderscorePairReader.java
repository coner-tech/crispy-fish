package org.coner.crispy_fish.datatype.underscore_pairs;

import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import static org.coner.crispy_fish.datatype.underscore_pairs.Constants.*;

public class SimpleStringUnderscorePairReader implements UnderscorePairReader<String> {

    public String get(@NotNull String pairs, @Nullable String key) {
        Preconditions.checkNotNull(pairs, "pairs must not be null");

        final int keyStartPosition = pairs.indexOf(key);
        if (keyStartPosition < 0) {
            // pairs doesn't contain key
            return null;
        }

        String value;
        final int keyLength = key.length();
        final int valueStartPosition = keyStartPosition + keyLength + 1; // +1 to account for the trailing _
        final int valueStopPosition = pairs.indexOf(UNDERSCORE, valueStartPosition);
        if (valueStopPosition >= 0) {
            value = pairs.substring(valueStartPosition, valueStopPosition);
        } else {
            value = pairs.substring(valueStartPosition);
        }
        return value;
    }
}

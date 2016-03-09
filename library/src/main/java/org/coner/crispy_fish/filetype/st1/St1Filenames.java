package org.coner.crispy_fish.filetype.st1;

import java.util.regex.Pattern;

public class St1Filenames {

    static final Pattern ORIGINAL_FILE_MATCHER = Pattern.compile(".*.st1$");
    static final Pattern REACCEPTED_FILE_MATCHER = Pattern.compile(".*.st1.\\d{3}$");

    static String getReacceptedFileBaseName(String originalFileBaseName) {
        return originalFileBaseName + "_st1";
    }
}

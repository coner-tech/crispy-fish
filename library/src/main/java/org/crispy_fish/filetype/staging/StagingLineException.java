package org.crispy_fish.filetype.staging;

public class StagingLineException extends Exception {

    public StagingLineException(String s) {
        super(s);
    }

    public StagingLineException(String s, Throwable throwable) {
        super(s, throwable);
    }
}

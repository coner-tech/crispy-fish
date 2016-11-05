package org.crispy_fish.filetype.ecf;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public class EventControlFileAssistant {

    private PathMatcher eventControlFileMatcher;

    public EventControlFileAssistant() {
        // no-op
    }

    public boolean isEventControlFilePath(Path path) {
        return getEventControlFileMatcher().matches(path.getFileName())
                && !path.toFile().isDirectory();
    }

    private synchronized PathMatcher getEventControlFileMatcher() {
        if (eventControlFileMatcher == null) {
            eventControlFileMatcher = buildEventControlFileMatcher();
        }
        return eventControlFileMatcher;
    }

    PathMatcher buildEventControlFileMatcher() {
        return FileSystems.getDefault().getPathMatcher("glob:*.ecf");
    }
}

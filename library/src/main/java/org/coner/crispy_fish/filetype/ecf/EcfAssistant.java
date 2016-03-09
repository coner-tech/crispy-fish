package org.coner.crispy_fish.filetype.ecf;

import org.coner.crispy_fish.filetype.st1.St1FilenameFilter;

import java.nio.file.*;

public class EcfAssistant {

    private PathMatcher eventControlFileMatcher;

    public EcfAssistant() {
        // no-op
    }

    public boolean isEcf(Path path) {
        return getEventControlFileMatcher().matches(path)
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

    public St1FilenameFilter buildSt1FilenameFilter(Path eventControlFile) {
        return new St1FilenameFilter(this, eventControlFile);
    }
}

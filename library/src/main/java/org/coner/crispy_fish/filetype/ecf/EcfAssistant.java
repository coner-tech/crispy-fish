package org.coner.crispy_fish.filetype.ecf;

import org.coner.crispy_fish.filetype.staging.StagingFilenameFilter;

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

    public StagingFilenameFilter buildStagingFilenameFilter(Path eventControlFile) {
        return new StagingFilenameFilter(this, eventControlFile);
    }
}

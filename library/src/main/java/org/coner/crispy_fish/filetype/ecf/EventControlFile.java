package org.coner.crispy_fish.filetype.ecf;

import java.nio.file.Path;

public class EventControlFile {

    private final EventControlFileAssistant ecfAssistant;
    private final Path path;
    private final boolean twoDayEvent;

    public EventControlFile(Path path, boolean twoDayEvent) {
        ecfAssistant = new EventControlFileAssistant();
        if (!ecfAssistant.isEventControlFilePath(path)) {
            throw new NotEventControlFilePathException(path);
        }
        this.path = path;
        this.twoDayEvent = twoDayEvent;
    }

    public Path getPath() {
        return path;
    }

    public boolean isTwoDayEvent() {
        return twoDayEvent;
    }
}

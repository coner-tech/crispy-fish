package org.crispy_fish.filetype.ecf;

import java.nio.file.Path;

public class EventControlFile {

    private final EventControlFileAssistant ecfAssistant;
    private final Path path;
    private final boolean twoDayEvent;
    private final int conePenalty;

    public EventControlFile(Path path, boolean twoDayEvent, int conePenalty) {
        ecfAssistant = new EventControlFileAssistant();
        if (!ecfAssistant.isEventControlFilePath(path)) {
            throw new NotEventControlFilePathException(path);
        }
        this.path = path;
        this.twoDayEvent = twoDayEvent;
        this.conePenalty = conePenalty;
    }

    public Path getPath() {
        return path;
    }

    public boolean isTwoDayEvent() {
        return twoDayEvent;
    }

    public int getConePenalty() {
        return conePenalty;
    }
}

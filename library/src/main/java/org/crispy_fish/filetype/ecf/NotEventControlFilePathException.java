package org.crispy_fish.filetype.ecf;

import java.nio.file.Path;

public class NotEventControlFilePathException extends IllegalArgumentException {
    public NotEventControlFilePathException(Path path) {
        super("Path is not an ecf file: " + String.valueOf(path));
    }
}

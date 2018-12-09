package org.coner.crispy_fish.filetype.ecf

import java.nio.file.Path

class EventControlFile(val path: Path, val isTwoDayEvent: Boolean, val conePenalty: Int) {

    private val ecfAssistant: EventControlFileAssistant

    init {
        ecfAssistant = EventControlFileAssistant()
        if (!ecfAssistant.isEventControlFilePath(path)) {
            throw NotEventControlFilePathException(path)
        }
    }
}

package tech.coner.crispyfish.filetype.ecf

import java.io.File

class EventControlFileAssistant {

    fun isEventControlFile(file: File): Boolean {
        return file.extension == "ecf" && !file.isDirectory
    }

}

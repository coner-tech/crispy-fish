package tech.coner.crispyfish.filetype.ecf

import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.isDirectory

class EventControlFileAssistant {

    fun isEventControlFile(file: Path): Boolean {
        return file.extension == "ecf" && !file.isDirectory()
    }

}

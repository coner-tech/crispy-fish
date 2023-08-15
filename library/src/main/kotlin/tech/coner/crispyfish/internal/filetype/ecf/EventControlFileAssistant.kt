package tech.coner.crispyfish.internal.filetype.ecf

import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.isDirectory

internal object EventControlFileAssistant {

    fun isEventControlFile(file: Path): Boolean {
        return file.extension == "ecf" && !file.isDirectory()
    }

}

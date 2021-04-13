package tech.coner.crispyfish.filetype.registration

import tech.coner.crispyfish.filetype.ecf.EventControlFile
import java.io.File
import java.io.FileFilter
import java.util.regex.Pattern

class RegistrationFileLocator(val eventControlFile: EventControlFile) {

    fun locate(): RegistrationFile {
        val folder = eventControlFile.file.parentFile
        val file = folder.listFiles(filter)
                .sortedByDescending(File::lastModified)
                .first()
        return RegistrationFile(file)
    }

    private val ecfFileNameWithoutExtension by lazy { eventControlFile.file.nameWithoutExtension }
    private val incrementingRegistrationFilesSuffix = Pattern.compile(
            "^\\Q${ecfFileNameWithoutExtension}_rgg\\E\\.[0-9]{3}\$",
            Pattern.CASE_INSENSITIVE
    )

    private val filter = FileFilter { file ->
        return@FileFilter if (!file.name.startsWith(ecfFileNameWithoutExtension, ignoreCase = true)) false
        else if (!file.extension.equals("rgg", ignoreCase = true) && !incrementingRegistrationFilesSuffix.matcher(file.name).matches()) false
        else true
    }
}
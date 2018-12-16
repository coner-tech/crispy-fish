package org.coner.crispyfish.filetype.staging

import org.coner.crispyfish.model.EventDay
import org.coner.crispyfish.filetype.ecf.EventControlFile
import java.io.File
import java.nio.file.Path

class StagingFileLocator(private val stagingFileAssistant: StagingFileAssistant) {

    fun locate(eventControlFile: EventControlFile, eventDay: EventDay): Path? {
        val eventControlFileParent = eventControlFile.path.parent.toFile()
        val stagingFilenameFilter = stagingFileAssistant.buildStagingFilenameFilter(
                eventControlFile,
                eventDay
        )

        val files = eventControlFileParent.listFiles(stagingFilenameFilter)
        val selectedFile = selectFile(files)
        return selectedFile?.toPath()
    }

    @JvmOverloads
    fun selectFile(files: Array<File>?, eventDay: EventDay = EventDay.ONE): File? {
        if (files == null) {
            return null
        }
        return when (files.size) {
            0 -> null
            1 -> files[0]
            else -> {
                val extension = when(eventDay) {
                    EventDay.ONE -> StagingFilenames.ORIGINAL_FILE_DAY_1_EXTENSION
                    EventDay.TWO -> StagingFilenames.ORIGINAL_FILE_DAY_2_EXTENSION
                }
                files.first { it.extension == extension }
            }
        }
    }

}

package tech.coner.crispyfish.filetype.staging

import tech.coner.crispyfish.model.EventDay
import tech.coner.crispyfish.filetype.ecf.EventControlFile
import java.io.File

class StagingFileLocator(
        private val eventControlFile: EventControlFile,
        private val stagingFileAssistant: StagingFileAssistant = StagingFileAssistant()
) {
    fun locate(eventDay: EventDay): File? {
        val eventControlFileParent = eventControlFile.file.parentFile
        val stagingFilenameFilter = stagingFileAssistant.buildStagingFilenameFilter(
                eventControlFile,
                eventDay
        )

        val files = eventControlFileParent.listFiles(stagingFilenameFilter)
        return selectFile(files)
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

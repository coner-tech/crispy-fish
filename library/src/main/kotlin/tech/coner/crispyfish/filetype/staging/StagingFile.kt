package tech.coner.crispyfish.filetype.staging

import java.io.File

class StagingFile(
        val file: File,
        private val assistant: StagingFileAssistant,
        val reader: StagingLineReader<String>
)
package org.coner.crispyfish.filetype.staging

import java.io.*
import java.util.regex.Pattern

class StagingFilenameFilter(
        val eventFileOriginalStagingBaseName: String,
        val originalFilePattern: Pattern
) : FilenameFilter {

    override fun accept(dir: File, name: String?): Boolean {
        val baseName = File(dir, name).nameWithoutExtension

        if (name == null || baseName == null) {
            return false
        }

        return if (originalFilePattern.matcher(name).matches()) {
            eventFileOriginalStagingBaseName.equals(baseName, ignoreCase = true)
        } else false

    }
}

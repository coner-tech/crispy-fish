package org.coner.crispyfish.filetype.staging

import java.io.*
import java.util.regex.Pattern

class StagingFilenameFilter(
        val eventFileOriginalStagingBaseName: String,
        val originalFilePattern: Pattern
) : FilenameFilter {

    override fun accept(dir: File, name: String?): Boolean {
        if (name == null) return false
        val baseName = File(dir, name).nameWithoutExtension
        return if (originalFilePattern.matcher(name).matches()) {
            eventFileOriginalStagingBaseName.equals(baseName, ignoreCase = true)
        } else false

    }
}

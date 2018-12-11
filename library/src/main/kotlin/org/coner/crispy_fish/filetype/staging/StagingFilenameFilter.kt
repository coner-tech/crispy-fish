package org.coner.crispy_fish.filetype.staging

import java.io.*
import java.util.regex.Pattern
import org.apache.commons.io.FilenameUtils

class StagingFilenameFilter(
        val eventFileOriginalStagingBaseName: String,
        val originalFilePattern: Pattern
) : FilenameFilter {

    override fun accept(dir: File, name: String?): Boolean {
        val baseName = FilenameUtils.getBaseName(name)

        if (name == null || baseName == null) {
            return false
        }

        return if (originalFilePattern.matcher(name).matches()) {
            eventFileOriginalStagingBaseName.equals(baseName, ignoreCase = true)
        } else false

    }
}

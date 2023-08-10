package tech.coner.crispyfish.filetype.staging

import java.util.regex.Pattern

object StagingFilenames {

    val ORIGINAL_FILE_DAY_1: Pattern = Pattern.compile(".*.st1$")
    val ORIGINAL_FILE_DAY_2: Pattern = Pattern.compile(".*.st2$")
    const val ORIGINAL_FILE_DAY_1_EXTENSION = "st1"
    const val ORIGINAL_FILE_DAY_2_EXTENSION = "st2"
    val ORIGINAL_FILE_EXTENSIONS = arrayOf(ORIGINAL_FILE_DAY_1_EXTENSION, ORIGINAL_FILE_DAY_2_EXTENSION)

}

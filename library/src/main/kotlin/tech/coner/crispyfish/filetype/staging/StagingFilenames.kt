package tech.coner.crispyfish.filetype.staging

import java.util.regex.Pattern

object StagingFilenames {

    const val ORIGINAL_FILE_DAY_1_EXTENSION = "st1"
    const val ORIGINAL_FILE_DAY_2_EXTENSION = "st2"
    val ORIGINAL_FILE_DAY_1: Pattern = Pattern.compile(".*.$ORIGINAL_FILE_DAY_1_EXTENSION")
    val ORIGINAL_FILE_DAY_2: Pattern = Pattern.compile(".*.$ORIGINAL_FILE_DAY_2_EXTENSION")
}

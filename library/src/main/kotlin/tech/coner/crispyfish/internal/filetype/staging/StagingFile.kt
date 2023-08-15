package tech.coner.crispyfish.internal.filetype.staging

import java.nio.file.Path

@JvmInline
internal value class StagingFile(
    val file: Path
)
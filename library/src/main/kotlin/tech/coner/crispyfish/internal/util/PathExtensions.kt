package tech.coner.crispyfish.internal.util

import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name

internal fun Path.orCaseInsensitiveMatch(): Path {
    if (exists()) return this
    // sometimes the program that shall not be named mangles the case of filename characters
    val regex = Regex(Regex.escape(name), RegexOption.IGNORE_CASE)
    return try {
        parent.listDirectoryEntries()
            .firstOrNull { regex.matches(it.name) }
    } catch (t: Throwable) {
        this // swallow it, there will be error handling where there is I/O
    } ?: this
}
package tech.coner.crispyfish.util

import assertk.assertThat
import assertk.assertions.isIn
import assertk.assertions.isSameAs
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import kotlin.io.path.createFile

class PathExtensionsTest {

    @TempDir
    lateinit var root: Path

    @Nested
    inner class OrCaseInsensitiveMatchTest {

        @Test
        fun `When exact match exists it should return same instance`() {
            val exists = root.resolve("Exists").createFile()

            val actual = exists.orCaseInsensitiveMatch()

            assertThat(actual).isSameAs(exists)
        }

        @Test
        fun `When exact match does not exist it should return case insensitive match`() {
            val doesNotExist = root.resolve("DoesNotExist")
            val caseInsensitiveMatch = root.resolve("doesnotexist").createFile()

            val actual = doesNotExist.orCaseInsensitiveMatch()

            assertThat(actual).isIn(doesNotExist, caseInsensitiveMatch)
        }

        @Test
        fun `When no match exists it should return same instance`() {
            val noMatchExists = root.resolve("NoMatchExists")

            val actual = noMatchExists.orCaseInsensitiveMatch()

            assertThat(actual).isSameAs(noMatchExists)
        }
    }
}
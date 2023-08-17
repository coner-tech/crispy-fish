package tech.coner.crispyfish

import assertk.assertThat
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import tech.coner.crispyfish.internal.CrispyFishClassDefinitionsImpl
import tech.coner.crispyfish.internal.CrispyFishEventImpl
import java.nio.file.Path
import kotlin.io.path.createFile

class CrispyFishTest {

    @TempDir
    lateinit var root: Path

    @Test
    fun itShouldInstantiateCrispyFishClassDefinitionsImpl() {
        val defPath = root.resolve("classes.def").createFile()

        val actual = CrispyFish.classDefinitions(defPath)

        assertThat(actual).isInstanceOf(CrispyFishClassDefinitionsImpl::class)
    }

    @Test
    fun itShouldInstantiateCrispyFishEventImpl() {
        val ecfPath = root.resolve("event.ecf").createFile()

        val actual = CrispyFish.event(ecfPath)

        assertThat(actual).isInstanceOf(CrispyFishEventImpl::class)
    }

}
package tech.coner.crispyfish.query

import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.index
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import org.junit.Test
import tech.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import tech.coner.crispyfish.filetype.ecf.EventControlFile
import tech.coner.crispyfish.model.paxResult
import tech.coner.crispyfish.model.rawResult
import java.io.File

/**
 * https://github.com/coner-tech/crispy-fish/issues/42
 * https://github.com/coner-tech/coner-trailer/issues/64
 */
class Issue42Test {

    private fun resource(relativeFilePath: String): File {
        return File(javaClass.getResource("/tech/coner/crispyfish/query/Issue42Test/$relativeFilePath").toURI())
    }

    @Test
    fun `It should query all registrations even those having raw time but lacking raw position`() {
        val classDefinitionFile = ClassDefinitionFile(resource("class2022_lscc.def"))
        val ecf = EventControlFile(
            file = resource("64-crispy-fish-staging-lines-invalid-signage.ecf"),
            classDefinitionFile = classDefinitionFile,
            isTwoDayEvent = false,
            conePenalty = 2
        )
        val actual = RegistrationsQuery(
            eventControlFile = ecf,
            categories = CategoriesQuery(classDefinitionFile).query(),
            handicaps = HandicapsQuery(classDefinitionFile).query()
        )
            .query()

        assertThat(actual).all {
            hasSize(3)
            index(0).all {
                rawResult().isNull()
                paxResult().isNull()
            }
            index(1).all {
                rawResult().isNotNull()
                paxResult().isNotNull()
            }
            index(2).all {
                rawResult().isNull()
                paxResult().isNull()
            }
        }
    }
}
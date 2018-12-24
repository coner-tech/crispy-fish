package org.coner.crispyfish.query

import assertk.all
import assertk.assert
import assertk.assertions.containsAll
import assertk.assertions.doesNotContain
import assertk.assertions.hasSize
import org.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import org.coner.crispyfish.test.ClassDefinitions
import org.junit.Test

class HandicapsQueryTest {

    @Test
    fun itShouldQueryHandicapsFromThscc2016ClassDefinitions() {
        val actual = HandicapsQuery(ClassDefinitions.Thscc2016.file).query()

        assert(actual).all {
            hasSize(51)
            containsAll(
                    ClassDefinitions.Thscc2016.cs,
                    ClassDefinitions.Thscc2016.str
            )
            doesNotContain(ClassDefinitions.Thscc2016.x)
            doesNotContain(ClassDefinitions.Thscc2016.nov)
            doesNotContain(ClassDefinitions.Thscc2016.of)
        }
    }

    @Test
    fun itShouldQueryHandicapsFromThscc2017ClassDefinitions() {
        val actual = HandicapsQuery(ClassDefinitions.Thscc2017.file).query()

        assert(actual).all {
            hasSize(51)
            containsAll(
                    ClassDefinitions.Thscc2017.cs,
                    ClassDefinitions.Thscc2017.str
            )
            doesNotContain(ClassDefinitions.Thscc2017.nov)
        }
    }

    @Test
    fun itShouldQueryHandicapsFromThscc2018ClassDefinitions() {
        val actual = HandicapsQuery(ClassDefinitions.Thscc2018.file).query()

        assert(actual).all {
            hasSize(51)
            containsAll(
                    ClassDefinitions.Thscc2018.cs,
                    ClassDefinitions.Thscc2018.str,
                    ClassDefinitions.Thscc2018.camc
            )
            doesNotContain(ClassDefinitions.Thscc2018.nov)
        }
    }
}
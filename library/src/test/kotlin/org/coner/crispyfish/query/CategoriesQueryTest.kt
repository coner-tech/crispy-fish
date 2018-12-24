package org.coner.crispyfish.query

import assertk.all
import assertk.assert
import assertk.assertions.containsExactly
import assertk.assertions.hasSize
import org.coner.crispyfish.test.ClassDefinitions
import org.junit.Test

class CategoriesQueryTest {

    @Test
    fun itShouldQueryCategoriesFromThscc2016ClassDefinitions() {
        val actual = CategoriesQuery(ClassDefinitions.Thscc2016.file).query()

        assert(actual).all {
            hasSize(3)
            containsExactly(
                    ClassDefinitions.Thscc2016.x,
                    ClassDefinitions.Thscc2016.nov,
                    ClassDefinitions.Thscc2016.of
            )
        }
    }

    @Test
    fun itShouldQueryCategoriesFromThscc2017ClassDefinitions() {
        val actual = CategoriesQuery(ClassDefinitions.Thscc2017.file).query()

        assert(actual).all {
            hasSize(3)
            containsExactly(
                    ClassDefinitions.Thscc2017.nov,
                    ClassDefinitions.Thscc2017.x,
                    ClassDefinitions.Thscc2017.of
            )
        }
    }
}
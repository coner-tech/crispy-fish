package tech.coner.crispyfish.query

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.hasSize
import tech.coner.crispyfish.test.ClassDefinitions
import org.junit.Test

class CategoriesQueryTest {

    @Test
    fun itShouldQueryCategoriesFromThscc2016ClassDefinitions() {
        val actual = CategoriesQuery(ClassDefinitions.Thscc2016.file).query()

        assertThat(actual).all {
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

        assertThat(actual).all {
            hasSize(3)
            containsExactly(
                    ClassDefinitions.Thscc2017.nov,
                    ClassDefinitions.Thscc2017.x,
                    ClassDefinitions.Thscc2017.of
            )
        }
    }

    @Test
    fun itShouldQueryCategoriesFromThscc2018ClassDefinitions() {
        val actual = CategoriesQuery(ClassDefinitions.Thscc2018.file).query()

        assertThat(actual).all {
            hasSize(4)
            containsExactly(
                    ClassDefinitions.Thscc2018.nov,
                    ClassDefinitions.Thscc2018.x,
                    ClassDefinitions.Thscc2018.mac,
                    ClassDefinitions.Thscc2018.of
            )
        }
    }
}
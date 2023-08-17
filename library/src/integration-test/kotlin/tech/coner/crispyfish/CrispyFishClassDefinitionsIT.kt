package tech.coner.crispyfish

import assertk.all
import assertk.assertThat
import assertk.assertions.containsAll
import assertk.assertions.containsExactly
import assertk.assertions.doesNotContain
import assertk.assertions.hasSize
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.model.AllClassDefinitions
import tech.coner.crispyfish.model.categories
import tech.coner.crispyfish.model.handicaps
import tech.coner.crispyfish.test.ClassDefinitions

class CrispyFishClassDefinitionsIT {

    @Test
    fun itShouldQueryAllClassDefinitionsFromThscc2016() {
        val actual = ClassDefinitions.Thscc2016.queryAllClassDefinitions()

        assertThat(actual).all {
            categories().all {
                hasSize(3)
                containsExactly(
                    ClassDefinitions.Thscc2016.x,
                    ClassDefinitions.Thscc2016.nov,
                    ClassDefinitions.Thscc2016.of
                )
            }
            handicaps().all {
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
    }

    @Test
    fun itShouldQueryCategoriesFromThscc2017ClassDefinitions() {
        val actual = ClassDefinitions.Thscc2017.queryAllClassDefinitions()

        assertThat(actual).all {
            categories().all {
                hasSize(3)
                containsExactly(
                    ClassDefinitions.Thscc2017.nov,
                    ClassDefinitions.Thscc2017.x,
                    ClassDefinitions.Thscc2017.of
                )
            }
            handicaps().all {
                hasSize(51)
                containsAll(
                    ClassDefinitions.Thscc2017.cs,
                    ClassDefinitions.Thscc2017.str
                )
                doesNotContain(ClassDefinitions.Thscc2017.nov)
            }
        }
    }

    @Test
    fun itShouldQueryCategoriesFromThscc2018ClassDefinitions() {
        val actual = ClassDefinitions.Thscc2018.queryAllClassDefinitions()

        assertThat(actual).all {
            categories().all {
                hasSize(4)
                containsExactly(
                    ClassDefinitions.Thscc2018.nov,
                    ClassDefinitions.Thscc2018.x,
                    ClassDefinitions.Thscc2018.mac,
                    ClassDefinitions.Thscc2018.of
                )
            }
            handicaps().all {
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

    private fun ClassDefinitions.queryAllClassDefinitions(): AllClassDefinitions {
        val crispyFish = CrispyFish.classDefinitions(file.file)
        return crispyFish.queryAllClassDefinitions()
    }
}
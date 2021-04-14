package tech.coner.crispyfish.filetype.classdefinition

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNullOrEmpty
import tech.coner.crispyfish.test.ClassDefinitions
import org.junit.Test

class ClassDefinitionReaderTest {

    lateinit var reader: ClassDefinitionReader

    @Test
    fun itShouldReadThscc2016StrClass() {
        val classDefinitions = ClassDefinitions.Thscc2016
        reader = ClassDefinitionReader(classDefinitions.file)
        val index = 13

        assertAll {
            assertThat(reader.readAbbreviation(index)).isEqualTo("STR")
            assertThat(reader.readPaxFactor(index)).isEqualTo("0.841")
            assertThat(reader.readName(index)).isEqualTo("Street Touring R")
            assertThat(reader.readPaxedClass(index)).isNullOrEmpty()
            assertThat(reader.readExclude(index)).isNullOrEmpty()
            assertThat(reader.readGroupName(index)).isEqualTo("Touring")
        }
    }

    @Test
    fun itShouldReadThscc2016NoviceClass() {
        val classDefinitions = ClassDefinitions.Thscc2016
        reader = ClassDefinitionReader(classDefinitions.file)
        val index = 50

        assertAll {
            assertThat(reader.readAbbreviation(index)).isEqualTo("NOV")
            assertThat(reader.readPaxFactor(index)).isEqualTo("0.000")
            assertThat(reader.readName(index)).isEqualTo("Novice")
            assertThat(reader.readPaxedClass(index)).isEqualTo("pax")
            assertThat(reader.readExclude(index)).isNullOrEmpty()
            assertThat(reader.readGroupName(index)).isEqualTo("Novice")
        }
    }
}
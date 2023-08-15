package tech.coner.crispyfish.internal.filetype.classdefinition

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNullOrEmpty
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.test.ClassDefinitions

class ClassDefinitionReaderTest {

    private lateinit var reader: ClassDefinitionReader

    @Test
    fun itShouldReadThscc2016StrClass() {
        val classDefinitions = ClassDefinitions.Thscc2016
        reader = ClassDefinitionReader(classDefinitions.file)
        val lines = reader.buildLines()
        val index = 13

        assertAll {
            assertThat(reader.readAbbreviation(lines, index)).isEqualTo("STR")
            assertThat(reader.readPaxFactor(lines, index)).isEqualTo("0.841")
            assertThat(reader.readName(lines, index)).isEqualTo("Street Touring R")
            assertThat(reader.readPaxedClass(lines, index)).isNullOrEmpty()
            assertThat(reader.readExclude(lines, index)).isNullOrEmpty()
            assertThat(reader.readGroupName(lines, index)).isEqualTo("Touring")
        }
    }

    @Test
    fun itShouldReadThscc2016NoviceClass() {
        val classDefinitions = ClassDefinitions.Thscc2016
        reader = ClassDefinitionReader(classDefinitions.file)
        val lines = reader.buildLines()
        val index = 50

        assertAll {
            assertThat(reader.readAbbreviation(lines, index)).isEqualTo("NOV")
            assertThat(reader.readPaxFactor(lines, index)).isEqualTo("0.000")
            assertThat(reader.readName(lines, index)).isEqualTo("Novice")
            assertThat(reader.readPaxedClass(lines, index)).isEqualTo("pax")
            assertThat(reader.readExclude(lines, index)).isNullOrEmpty()
            assertThat(reader.readGroupName(lines, index)).isEqualTo("Novice")
        }
    }
}
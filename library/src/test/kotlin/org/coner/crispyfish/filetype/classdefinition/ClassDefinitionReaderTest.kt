package org.coner.crispyfish.filetype.classdefinition

import assertk.assert
import assertk.assertAll
import assertk.assertions.isEqualTo
import assertk.assertions.isNullOrEmpty
import org.coner.crispyfish.test.ClassDefinitions
import org.junit.Test

class ClassDefinitionReaderTest {

    lateinit var reader: ClassDefinitionReader

    @Test
    fun itShouldReadThscc2016StrClass() {
        val classDefinitions = ClassDefinitions.Thscc2016
        reader = ClassDefinitionReader(classDefinitions.file)
        val index = 13

        assertAll {
            assert(reader.readAbbreviation(index)).isEqualTo("STR")
            assert(reader.readPaxFactor(index)).isEqualTo("0.841")
            assert(reader.readName(index)).isEqualTo("Street Touring R")
            assert(reader.readPaxedClass(index)).isNullOrEmpty()
            assert(reader.readExclude(index)).isNullOrEmpty()
            assert(reader.readGroupName(index)).isEqualTo("Touring")
        }
    }

    @Test
    fun itShouldReadThscc2016NoviceClass() {
        val classDefinitions = ClassDefinitions.Thscc2016
        reader = ClassDefinitionReader(classDefinitions.file)
        val index = 50

        assertAll {
            assert(reader.readAbbreviation(index)).isEqualTo("NOV")
            assert(reader.readPaxFactor(index)).isEqualTo("0.000")
            assert(reader.readName(index)).isEqualTo("Novice")
            assert(reader.readPaxedClass(index)).isEqualTo("pax")
            assert(reader.readExclude(index)).isNullOrEmpty()
            assert(reader.readGroupName(index)).isEqualTo("Novice")
        }
    }
}
package org.coner.crispyfish.filetype.classdefinition

import assertk.assert
import assertk.assertions.isNotNull
import assertk.assertions.isNotSameAs
import org.coner.crispyfish.test.ClassDefinitions
import org.junit.Before
import org.junit.Test

class ClassDefinitionFileTest {

    lateinit var file: ClassDefinitionFile

    @Before
    fun before() {
        file = ClassDefinitions.Thscc2016.file
    }
    
    @Test
    fun itShouldReturnReader() {
        val actual = file.reader()

        assert(actual).isNotNull()
    }

    @Test
    fun itShouldReturnUniqueReaders() {
        val first = file.reader()
        val second = file.reader()

        assert(first).isNotSameAs(second)
    }
}
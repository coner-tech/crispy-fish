package tech.coner.crispyfish.filetype.classdefinition

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isNotSameAs
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.test.ClassDefinitions

class ClassDefinitionFileTest {

    lateinit var file: ClassDefinitionFile

    @BeforeEach
    fun before() {
        file = ClassDefinitions.Thscc2016.file
    }
    
    @Test
    fun itShouldReturnReader() {
        val actual = file.reader()

        assertThat(actual).isNotNull()
    }

    @Test
    fun itShouldReturnUniqueReaders() {
        val first = file.reader()
        val second = file.reader()

        assertThat(first).isNotSameAs(second)
    }
}
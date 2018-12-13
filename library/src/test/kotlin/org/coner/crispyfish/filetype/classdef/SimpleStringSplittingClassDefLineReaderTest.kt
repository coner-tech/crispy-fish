package org.coner.crispyfish.filetype.classdef

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import java.util.ArrayList

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.atIndex
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SimpleStringSplittingClassDefLineReaderTest {

    private lateinit var simpleStringSplittingClassDefLineReader: SimpleStringSplittingClassDefLineReader

    @Mock
    lateinit var fields: MutableList<String>

    @Before
    fun setup() {
        simpleStringSplittingClassDefLineReader = SimpleStringSplittingClassDefLineReader(fields)
    }

    @Test
    fun whenSetClassDefLineValidItShouldSplit() {
        val fields = ArrayList<String>()
        simpleStringSplittingClassDefLineReader = SimpleStringSplittingClassDefLineReader(fields)
        val line = "CS\t0.814\t1\tC Street\t\t\t\tStreet\t\t"

        simpleStringSplittingClassDefLineReader.line = line

        assertThat(fields).contains("CS", atIndex(0))
        assertThat(fields).contains("0.814", atIndex(1))
        assertThat(fields).contains("C Street", atIndex(3))
        assertThat(fields).contains("Street", atIndex(7))
    }

    @Test
    @Throws(Exception::class)
    fun whenGetClassAbbreviationItShouldGetIt() {
        val classAbbreviation = "CS"
        `when`(fields[0]).thenReturn(classAbbreviation)

        val actual = simpleStringSplittingClassDefLineReader.classAbbreviation

        verify<List<String>>(fields)[0]
        assertThat(actual).isSameAs(classAbbreviation)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetPaxItShouldGetIt() {
        val pax = "0.814"
        `when`(fields[1]).thenReturn(pax)

        val actual = simpleStringSplittingClassDefLineReader.pax

        verify<List<String>>(fields)[1]
        assertThat(actual).isSameAs(pax)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetClassNameItShouldGetIt() {
        val className = "C Street"
        `when`(fields[3]).thenReturn(className)

        val actual = simpleStringSplittingClassDefLineReader.className

        verify<List<String>>(fields)[3]
        assertThat(actual).isSameAs(className)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetCategoryItShouldGetIt() {
        val category = "Street"
        `when`(fields[7]).thenReturn(category)

        val actual = simpleStringSplittingClassDefLineReader.category

        verify<List<String>>(fields)[7]
        assertThat(actual).isSameAs(category)
    }

}
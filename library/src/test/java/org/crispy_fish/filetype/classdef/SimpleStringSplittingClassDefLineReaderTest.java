package org.crispy_fish.filetype.classdef;

import org.assertj.core.data.Index;
import org.coner.crispy_fish.filetype.classdef.SimpleStringSplittingClassDefLineReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleStringSplittingClassDefLineReaderTest {

    private SimpleStringSplittingClassDefLineReader simpleStringSplittingClassDefLineReader;

    @Mock
    List<String> fields;

    @Before
    public void setup() {
        simpleStringSplittingClassDefLineReader = new SimpleStringSplittingClassDefLineReader(fields);
    }

    @Test
    public void whenSetClassDefLineValidItShouldSplit() {
        ArrayList<String> fields = new ArrayList<String>();
        simpleStringSplittingClassDefLineReader = new SimpleStringSplittingClassDefLineReader(fields);
        String line = "CS\t0.814\t1\tC Street\t\t\t\tStreet\t\t";

        simpleStringSplittingClassDefLineReader.setLine(line);

        assertThat(fields)
                .contains("CS", Index.atIndex(0))
                .contains("0.814", Index.atIndex(1))
                .contains("C Street", Index.atIndex(3))
                .contains("Street", Index.atIndex(7));
    }

    @Test
    public void whenGetClassAbbreviationItShouldGetIt() throws Exception {
        String classAbbreviation = "CS";
        when(fields.get(0)).thenReturn(classAbbreviation);

        String actual = simpleStringSplittingClassDefLineReader.getClassAbbreviation();

        verify(fields).get(0);
        assertThat(actual).isSameAs(classAbbreviation);
    }

    @Test
    public void whenGetPaxItShouldGetIt() throws Exception {
        String pax = "0.814";
        when(fields.get(1)).thenReturn(pax);

        String actual = simpleStringSplittingClassDefLineReader.getPax();

        verify(fields).get(1);
        assertThat(actual).isSameAs(pax);
    }

    @Test
    public void whenGetClassNameItShouldGetIt() throws Exception {
        String className = "C Street";
        when(fields.get(3)).thenReturn(className);

        String actual = simpleStringSplittingClassDefLineReader.getClassName();

        verify(fields).get(3);
        assertThat(actual).isSameAs(className);
    }

    @Test
    public void whenGetCategoryItShouldGetIt() throws Exception {
        String category = "Street";
        when(fields.get(7)).thenReturn(category);

        String actual = simpleStringSplittingClassDefLineReader.getCategory();

        verify(fields).get(7);
        assertThat(actual).isSameAs(category);
    }

}
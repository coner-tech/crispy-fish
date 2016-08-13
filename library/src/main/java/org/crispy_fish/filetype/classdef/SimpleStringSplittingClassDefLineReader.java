package org.crispy_fish.filetype.classdef;

import java.util.*;

public class SimpleStringSplittingClassDefLineReader implements ClassDefLineReader<String> {

    private final List<String> fields;

    public SimpleStringSplittingClassDefLineReader() {
        this.fields = new ArrayList<>();
    }

    SimpleStringSplittingClassDefLineReader(List<String> fields) {
        this.fields = fields;
    }

    public void setClassDefLine(String line) {
        fields.clear();
        String[] fieldsArray = line.split("\t");
        Collections.addAll(fields, fieldsArray);
    }

    public String getClassAbbreviation() {
        return fields.get(ClassDefIndices.CLASS_ABBREVIATION);
    }

    public String getPax() {
        return fields.get(ClassDefIndices.PAX);
    }

    public String getMysteryIndicatorAtIndex2() {
        return null;
    }

    public String getClassName() {
        return fields.get(ClassDefIndices.CLASS_NAME);
    }

    public String getMysteryIndicatorAtIndex4() {
        return null;
    }

    public String getMysteryIndicatorAtIndex5() {
        return null;
    }

    public String getMysteryIndicatorAtIndex6() {
        return null;
    }

    public String getCategory() {
        return fields.get(ClassDefIndices.CATEGORY);
    }


}

package org.coner.crispy_fish.filetype.classdef;

import java.util.*;

import static org.coner.crispy_fish.filetype.classdef.ClassDefIndices.CATEGORY;
import static org.coner.crispy_fish.filetype.classdef.ClassDefIndices.CLASS_ABBREVIATION;
import static org.coner.crispy_fish.filetype.classdef.ClassDefIndices.CLASS_NAME;
import static org.coner.crispy_fish.filetype.classdef.ClassDefIndices.PAX;

public class SimpleClassDefLineReader implements ClassDefLineReader<String> {

    private final List<String> fields;

    public SimpleClassDefLineReader() {
        this.fields = new ArrayList<String>();
    }

    SimpleClassDefLineReader(List<String> fields) {
        this.fields = fields;
    }

    public void setClassDefLine(String line) {
        fields.clear();
        String[] fieldsArray = line.split("\t");
        Collections.addAll(fields, fieldsArray);
    }

    public String getClassAbbreviation() {
        return fields.get(CLASS_ABBREVIATION);
    }

    public String getPax() {
        return fields.get(PAX);
    }

    public String getMysteryIndicatorAtIndex2() {
        return null;
    }

    public String getClassName() {
        return fields.get(CLASS_NAME);
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
        return fields.get(CATEGORY);
    }


}

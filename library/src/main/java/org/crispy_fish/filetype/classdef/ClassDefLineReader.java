package org.crispy_fish.filetype.classdef;

public interface ClassDefLineReader<L> {

    void setClassDefLine(L line);

    String getClassAbbreviation();

    String getPax();

    String getMysteryIndicatorAtIndex2();

    String getClassName();

    String getMysteryIndicatorAtIndex4();

    String getMysteryIndicatorAtIndex5();

    String getMysteryIndicatorAtIndex6();

    String getCategory();

}

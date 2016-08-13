package org.crispy_fish.filetype.staging;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StagingFilenamesTest {

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void whenMatchOriginalFileDay1WithValidNameItShouldMatch() {
        String filenameOriginalDay1Valid = "foo.st1";
        assertThat(StagingFilenames.ORIGINAL_FILE_DAY_1.matcher(filenameOriginalDay1Valid).matches()).isTrue();
    }

    @Test
    public void whenMatchOriginalFileDay1WithInvalidNamesItShouldNotMatch() {
        String[] filenamesOriginalDay1Invalid = new String[]{
                "foo.st2", "foo.st1_log", "foo.txt", "foo", "foo.st1.bak", "foo.st1.000", "foo.st1.999"
        };

        for (String input : filenamesOriginalDay1Invalid) {
            softly.assertThat(StagingFilenames.ORIGINAL_FILE_DAY_1.matcher(input).matches())
                    .as("%s should not match ORIGINAL_FILE_DAY_1's matcher", input)
                    .isFalse();
        }
    }

    @Test
    public void whenMatchOriginalFileDay2WithValidNameItShouldMatch() {
        String filenameOriginalDay2Valid = "foo.st2";
        assertThat(StagingFilenames.ORIGINAL_FILE_DAY_2.matcher(filenameOriginalDay2Valid).matches()).isTrue();
    }

    @Test
    public void whenMatchOriginalFileDay2WithInvalidNamesItShouldNotMatch() {
        String[] filenamesOriginalDay2Invalid = new String[]{
                "foo.st1", "foo.st2_log", "foo.txt", "foo", "foo.st2.bak", "foo.st2.000", "foo.st2.999"
        };

        for (String input : filenamesOriginalDay2Invalid) {
            softly.assertThat(StagingFilenames.ORIGINAL_FILE_DAY_2.matcher(input).matches())
                    .as("%s should not match ORIGINAL_FILE_DAY_2's matcher", input)
                    .isFalse();
        }
    }

}
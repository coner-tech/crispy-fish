package org.coner.crispy_fish.filetype.staging;

import org.apache.commons.io.FilenameUtils;
import org.coner.crispy_fish.filetype.ecf.EventControlFile;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;

public class StagingFileLocator {

    private final StagingFileAssistant stagingFileAssistant;

    public StagingFileLocator(StagingFileAssistant stagingFileAssistant) {
        this.stagingFileAssistant = stagingFileAssistant;
    }


    public Path locate(EventControlFile eventControlFile) {
        if (eventControlFile == null) {
            throw new IllegalArgumentException("Programmer error: eventControlFile is null");
        }

        File eventControlFileParent = eventControlFile.getPath().getParent().toFile();
        StagingFilenameFilter stagingFilenameFilter = stagingFileAssistant.buildStagingFilenameFilter(eventControlFile);

        File[] files = eventControlFileParent.listFiles(stagingFilenameFilter);
        File selectedFile = selectFile(files);
        if (selectedFile != null) {
            return selectedFile.toPath();
        } else {
            return null;
        }
    }

    File selectFile(File[] files) {
        if (files == null) {
            return null;
        }
        switch (files.length) {
            case 0:
                return null;
            case 1:
                return files[0];
            default:
                Arrays.sort(files, comparator);
                return files[0];
        }
    }

    private final Comparator<File> comparator = (left, right) -> {
        if (left == null && right == null) {
            return 0;
        } else if (left == null) {
            return 1;
        } else if (right == null) {
            return -1;
        }

        String leftFileName = left.getName();
        String rightFileName = right.getName();
        Matcher leftOriginalFileMatcher = StagingFilenames.ORIGINAL_FILE_PATTERN.matcher(leftFileName);
        boolean leftOriginal = leftOriginalFileMatcher.matches();
        Matcher rightOriginalFileMatcher = StagingFilenames.ORIGINAL_FILE_PATTERN.matcher(rightFileName);
        boolean rightOriginal = rightOriginalFileMatcher.matches();
        Matcher leftReacceptedFileMatcher = StagingFilenames.REACCEPTED_FILE_PATTERN.matcher(leftFileName);
        boolean leftReaccpeted = leftReacceptedFileMatcher.matches();
        Matcher rightReacceptedFileMatcher = StagingFilenames.REACCEPTED_FILE_PATTERN.matcher(rightFileName);
        boolean rightReaccepted = rightReacceptedFileMatcher.matches();

        if ((leftOriginal || leftReaccpeted) && !(rightOriginal || rightReaccepted)) {
            return -1;
        }
        if ((rightOriginal || rightReaccepted) && !(leftOriginal || leftReaccpeted)) {
            return 1;
        }
        if (leftOriginal && rightOriginal) {
            return 0;
        } else if (leftOriginal && rightReaccepted) {
            return 1;
        } else if (leftReaccpeted && rightOriginal) {
            return -1;
        }
        if (leftReaccpeted && rightReaccepted) {
            String leftGroup = leftReacceptedFileMatcher.group();
            String rightGroup = rightReacceptedFileMatcher.group();
            String leftExtension = FilenameUtils.getExtension(leftGroup);
            String rightExtension = FilenameUtils.getExtension(rightGroup);
            if (leftExtension != null && rightExtension != null) {
                int comparison = leftExtension.compareTo(rightExtension);
                if (comparison >= 1) {
                    return -1;
                } else if (comparison <= -1) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (leftExtension != null) {
                return -1;
            } else if (rightExtension != null) {
                return 1;
            }
        }

        return 0;
    };


}

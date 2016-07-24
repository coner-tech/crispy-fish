package org.coner.crispy_fish.filetype.staging;

import org.coner.crispy_fish.filetype.ecf.EcfAssistant;

import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import org.apache.commons.io.FilenameUtils;

public class StagingFileLocator {

    private final EcfAssistant ecfAssistant;


    public StagingFileLocator(EcfAssistant ecfAssistant) {
        this.ecfAssistant = ecfAssistant;
    }

    public Path locate(Path eventControlFile) {
        if (eventControlFile == null) {
            throw new IllegalStateException("Programmer error: eventControlFile is null");
        }
        if (!ecfAssistant.isEcf(eventControlFile)) {
            throw new IllegalArgumentException("Invalid event control file");
        }
        File eventControlFileParent = eventControlFile.getParent().toFile();
        StagingFilenameFilter stagingFilenameFilter = ecfAssistant.buildStagingFilenameFilter(eventControlFile);

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

package org.dhltest.framework.util;

import java.time.LocalDateTime;

public class FileSystemName {
    private final LocalDateTime date;

    public FileSystemName() {
        date = LocalDateTime.now();
    }

    public String generateFileName() {
        String generatedName = DateUtils.convertToString(date, "HH-mm-ss-");
        String timeStringValue = DateUtils.convertToString(date, "n").trim();
        if (timeStringValue.isEmpty()) {
            timeStringValue = "00";
        } else if (timeStringValue.length() == 1) {
            timeStringValue = "0" + timeStringValue;
        } else {
            timeStringValue = timeStringValue.substring(0, 2);
        }

        return generatedName + timeStringValue;
    }
}

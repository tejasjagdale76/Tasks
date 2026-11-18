package com.kiranacademy.rpms.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ActivityLogService {

    private static final String LOG_FILE = "activity_log.txt";

    public void log(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(LocalDateTime.now() + " | " + message + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Unable to write activity log: " + e.getMessage());
        }
    }
}

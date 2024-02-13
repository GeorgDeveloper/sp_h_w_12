package ru.georgdeveloper.taskapp.logg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserActionLogger {
    private static final String LOG_FILE_PATH = "user_actions.log";

    public static void logUserAction(String username, String methodName, Object[] args) {
        File logFile = new File(LOG_FILE_PATH);
        try (FileWriter writer = new FileWriter(logFile, true)) {
            StringBuilder messageBuilder = new StringBuilder();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = LocalDateTime.now().format(formatter);

            messageBuilder.append("[").append(formattedDateTime).append("] ");
            messageBuilder.append("User '").append(username).append("' performed action: '").append(methodName).append("'");
            if (args.length > 0) {
                messageBuilder.append(" with arguments: ");
                for (Object arg : args) {
                    messageBuilder.append(arg.toString()).append(", ");
                }
                messageBuilder.delete(messageBuilder.length() - 2, messageBuilder.length());
            }
            messageBuilder.append(".").append(System.lineSeparator());

            writer.write(messageBuilder.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


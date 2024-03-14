package com.apptracker.spendingtracker.errorResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private String timestamp;
    private String path;

    public static ErrorResponse getErrorResponse(String path, String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);
        return ErrorResponse.builder()
                .timestamp(timestamp)
                .path(path)
                .message(message)
                .status(HttpStatus.NOT_FOUND.value())
                .build();
    }
}

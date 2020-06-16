package models;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;

    public ErrorResponse() {}

    public ErrorResponse(LocalDateTime timestamp, int status, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public int getStatus() {
        return this.status;
    }

    public String getError() {
        return this.error;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public String toString() {
        LocalDateTime var10000 = this.getTimestamp();
        return "ErrorResponse(timestamp=" + var10000 + ", status=" + this.getStatus() + ", error=" + this.getError() + ")";
    }
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $timestamp = this.getTimestamp();
        result = result * 59 + ($timestamp == null ? 43 : $timestamp.hashCode());
        result = result * 59 + this.getStatus();
        Object $error = this.getError();
        result = result * 59 + ($error == null ? 43 : $error.hashCode());
        return result;
    }
}

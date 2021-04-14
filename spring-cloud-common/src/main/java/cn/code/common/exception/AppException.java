package cn.code.common.exception;

public class AppException extends RuntimeException {
    private String errorCode = "400";

    public AppException(String message) {
        super(message);
    }

    public AppException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public AppException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public AppException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public AppException(String errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}

package dev.swarnim.project.errorhandler;

/**
 * All exception should extend TodoException.
 */
public abstract class TodoException extends RuntimeException implements ErrorCode{

    private final String errorCode;
    private final String errorMessage;

    protected TodoException(String errorCode,
                            String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getDetailedErrorMessage() {
        return getErrorMessage();
    }
}

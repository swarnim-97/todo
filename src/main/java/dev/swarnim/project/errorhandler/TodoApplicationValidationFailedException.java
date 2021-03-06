package dev.swarnim.project.errorhandler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoApplicationValidationFailedException extends RuntimeException implements ErrorCode{

    private String errorList;

    public TodoApplicationValidationFailedException(String errorList) {
        this.errorList = errorList;
    }

    public TodoApplicationValidationFailedException() {
    }

    @Override
    public String getErrorCode() {
        return TodoErrorCodes.TODO0001.getErrorCode();
    }

    @Override
    public String getErrorMessage() {
        return TodoErrorCodes.TODO0001.getErrorMessage();
    }

    @Override
    public String getDetailedErrorMessage() {
        return errorList;
    }
}

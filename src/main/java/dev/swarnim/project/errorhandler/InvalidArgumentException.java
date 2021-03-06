package dev.swarnim.project.errorhandler;

public class InvalidArgumentException extends TodoException{

    public InvalidArgumentException(String errorCode,
                                    String errorMessage) {
        super(errorCode, errorMessage);
    }

    public InvalidArgumentException(TodoErrorCodes todoErrorCodes){
        super(todoErrorCodes.getErrorCode(), todoErrorCodes.getErrorMessage());
    }

}

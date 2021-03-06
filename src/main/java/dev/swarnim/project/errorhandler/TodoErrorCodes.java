package dev.swarnim.project.errorhandler;


public enum TodoErrorCodes {
    TODO0001("TODO0001", "Invalid request"),
    TODO0002("TODO0002", "Username already exists."),
    ;
    private String errorCode;
    private String errorMessage;


    TodoErrorCodes(String errorCode,
                   String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorCode(String errorCode){
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
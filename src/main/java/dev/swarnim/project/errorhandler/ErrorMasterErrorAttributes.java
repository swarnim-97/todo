package dev.swarnim.project.errorhandler;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Map;

public class ErrorMasterErrorAttributes extends DefaultErrorAttributes {
    private final String currentApiVersion;
    private final String sendReportUri;

    public ErrorMasterErrorAttributes(String currentApiVersion,
                                      String sendReportUri) {
        this.currentApiVersion = currentApiVersion;
        this.sendReportUri = sendReportUri;
    }

    @Override
    public Map<String, Object> getErrorAttributes(final WebRequest webRequest,
                                                  final ErrorAttributeOptions options) {
        final Map<String, Object> defaultErrorAttributes = super.getErrorAttributes(webRequest, options);
        final ErrorMaster error = ErrorMaster.fromDefaultAttributeMap(
                currentApiVersion, defaultErrorAttributes, sendReportUri, new Date()
        );
        return error.toAttributeMap();
    }

    /**
     * What we’re doing here is a kind of wrapper that overrides the default implementation of getErrorAttributes and
     * replaces the default values by our own ones, using the mapper method we created in ErrorMaster.
     * Note that the method signature in the interface ErrorAttributes requires us to return a Map;
     * that’s why we created that method toAttributeMap() in ErrorMaster instead of serializing the complete object in JSON.
     */
}

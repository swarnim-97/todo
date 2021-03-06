package dev.swarnim.project.errorhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  link:- https://thepracticaldeveloper.com/custom-error-handling-rest-controllers-spring-boot/
 *  We are converting our error response in google json style guide. Google JSON style guide.
 * @author Swarnim
 */
@Slf4j
@Getter
public class ErrorMaster {
    private final String apiVersion;
    private final ErrorBlock error;
    private final Date date;

    public ErrorMaster(final String apiVersion,
                       final String code,
                       final String message,
                       final String domain,
                       final String reason,
                       final String errorMessage,
                       final String errorReportUri,
                       Date date) {
        this.apiVersion = apiVersion;
        this.date = date;
        this.error = new ErrorBlock(code, message, domain, reason, errorMessage, errorReportUri);
    }

    public static ErrorMaster fromDefaultAttributeMap(final String apiVersion,
                                                      final Map<String, Object> defaultErrorAttributes,
                                                      final String sendReportBaseUri,
                                                      final Date date){
        // original attribute values are documented in org.springframework.boot.web.servlet.error.DefaultErrorAttributes
        return new ErrorMaster(
                apiVersion,
                ((Integer)defaultErrorAttributes.get("status")).toString(),
                (String)defaultErrorAttributes.getOrDefault("message", "no message available"),
                (String) defaultErrorAttributes.getOrDefault("path", "no domain available"),
                (String) defaultErrorAttributes.getOrDefault("error", "no reason available"),
                (String) defaultErrorAttributes.get("message"),
                sendReportBaseUri,
                date
        );
    }

    // utility method to return a map of serialized root attributes,
    // see the last part of the guide for more details
    public Map<String, Object> toAttributeMap() {
        Map map = new HashMap();
        map.put("apiVersion", apiVersion);
        //map.put("requestId", requestId);
        map.put("error", error);
        map.put("date", date);

        return map;
    }

    @Getter
    private static final class ErrorBlock{
        private final String code;
        private final String message;
        private final List<Error> errors;

        private ErrorBlock(final String code,
                           final String message,
                           final String domain,
                           final String reason,
                           final String errorMessage,
                           final String errorReportUri) {
            this.code = code;
            this.message = message;
            this.errors = List.of(
                    new Error(domain, reason, errorMessage, errorReportUri)
            );
        }

        private ErrorBlock(String code, String message, List<Error> errors){

            this.code = code;
            this.message = message;
            this.errors = errors;
        }

        public static ErrorBlock copyWithMessage(final ErrorBlock s,
                                                 final String message){
            return new ErrorBlock(s.code,
                    message,
                    s.errors);
        }
    }

    @Data
    @AllArgsConstructor
    private static final class Error{
        private final String domain;
        private final String reason;
        private final String message;
        private final String sendReport;
    }
}

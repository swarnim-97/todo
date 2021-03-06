package dev.swarnim.project.configuration;

import dev.swarnim.project.errorhandler.ErrorMasterErrorAttributes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebErrorConfiguration {

    @Value("${error.api.version}")
    private String currentApiVersion;

    @Value("${error.sendreport.uri}")
    private String sendReportUri;

    /**
     * We override the default {@link DefaultErrorAttributes}
     *
     * @return A custom implementation of ErrorAttributes
     */

    @Bean
    public ErrorAttributes errorAttributes() {
        return new ErrorMasterErrorAttributes(currentApiVersion, sendReportUri);
    }


    /**
     * As you see, the only thing we need is to inject an ErrorAttributes bean in the context to make Spring Boot
     * not to inject the default implementation.
     * The API version and base report URI come from the application.yml file.
     */
}

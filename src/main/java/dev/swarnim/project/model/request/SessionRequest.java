package dev.swarnim.project.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.swarnim.project.enums.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SessionRequest {

    private Device device;

    private Source source;

    private Location location;
}

package dev.swarnim.project.model.request;

import dev.swarnim.project.enums.IdentifierType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    private Long id;

    @NotBlank(message = "deviceType is not available.")
    private String deviceType;

    private String deviceMake;

    private String deviceModel;

    private String os;

    private String osVersion;

    @NotBlank(message = "identifier type is not available.")
    private IdentifierType identifierType;

    @NotBlank(message = "identification number is not available.")
    private String identificationNumber;
}

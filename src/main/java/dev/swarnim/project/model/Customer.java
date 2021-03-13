package dev.swarnim.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class Customer {
    @NotBlank
    private String name;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    private Boolean active;
    private Boolean deleted;
}

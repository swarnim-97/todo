package dev.swarnim.project.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Source {

    @JsonProperty("web")
    web("Web"),
    @JsonProperty("android")
    android("Android"),
    @JsonProperty("ios")
    ios("IOS");

    private String displayString;

    Source(String displayString) {
        this.displayString = displayString;
    }
}

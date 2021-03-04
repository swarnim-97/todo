package dev.swarnim.project.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IdentifierType {

    @JsonProperty("Imei")
    imei("Imei"),
    @JsonProperty("Ip")
    ip("Ip"),
    @JsonProperty("Deviceid")
    deviceid("DeviceId");

    private String displayText;

    IdentifierType(String displayText) {
        this.displayText = displayText;
    }

    @Override
    public String toString() {
        return "IdentifierType{" +
                "displayText='" + displayText + '\'' +
                '}';
    }
}

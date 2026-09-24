package com.seafloor.mining.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Operator ID is required")
    @Size(min = 3, max = 50, message = "Operator ID must be between 3 and 50 characters")
    @Pattern(regexp = "^[A-Za-z0-9._-]+$", message = "Operator ID can contain only letters, numbers, dots, underscores, and hyphens")
    private String operatorId;

    @NotBlank(message = "Passcode is required")
    @Size(min = 6, max = 100, message = "Passcode must be between 6 and 100 characters")
    private String passcode;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}

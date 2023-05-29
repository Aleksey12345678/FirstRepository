package com.mycompany.project1.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDto {
private boolean success;
@JsonAlias("error-codes")
private Set<String> errorCodes;

//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public Set<String> getErrorCodes() {
//        return errorCodes;
//    }
//
//    public void setErrorCodes(Set<String> errorCodes) {
//        this.errorCodes = errorCodes;
//    }
}

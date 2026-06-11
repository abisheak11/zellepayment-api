package com.zellepayment.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    private String errorMessage;
    private String url;
}

package com.vinicius.santos.kyrosb01.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponse {
    private Object body;
    private HttpStatus httpStatus;
}

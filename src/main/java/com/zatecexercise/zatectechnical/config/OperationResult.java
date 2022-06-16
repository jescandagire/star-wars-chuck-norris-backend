package com.zatecexercise.zatectechnical.config;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"message", "result"})
public class OperationResult {

    private String message;
    private Object result;

    public static final String OPERATION_SUCCESSFUL_MESSAGE = "Operation Successful";
    public static final String OPERATION_FAILURE_MESSAGE = "Operation failed";
}

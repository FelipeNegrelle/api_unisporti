package com.unisporti.api_unisporti.exception;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ErrorDetails {
    private Date timestamp;
    private int status;
    private String details;
    private String message;
    private List<Map<String, String>> errors;
}
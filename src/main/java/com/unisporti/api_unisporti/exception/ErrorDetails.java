package com.unisporti.api_unisporti.exception;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
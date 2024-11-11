package com.unisporti.api_unisporti.vo;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class LoginRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String cpf;
    private String password;
}

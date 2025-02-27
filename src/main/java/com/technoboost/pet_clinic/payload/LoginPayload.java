package com.technoboost.pet_clinic.payload;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@ToString
public class LoginPayload {
    private String username;

    private String password;

}

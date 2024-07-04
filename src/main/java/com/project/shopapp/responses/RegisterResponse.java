package com.project.shopapp.responses;

import com.project.shopapp.models.UserModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String message;

    private UserModel user;
}

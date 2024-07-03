package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("token")
    private String token;
}

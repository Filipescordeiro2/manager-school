package com.maneger.school.dto.response;

import lombok.Builder;

@Builder
public record LoginSecretaryResponse(String message,
                                     SecretaryResponse secretaryResponse) {
}

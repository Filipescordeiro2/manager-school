package com.maneger.school.dto.response;

import lombok.Builder;

@Builder
public record LoginAlunoResponse(String message,
                                 StudentResponse alunoResponse) {
}

package com.maneger.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAlunoRequest {

    private String userAccess;
    private String passwordAccess;

}

package com.maneger.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentClassRequest {

    private String studentCPF;
    private UUID schoolClassId;
}

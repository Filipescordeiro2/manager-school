package com.maneger.school.dto.response;

import lombok.Builder;

@Builder
public record LoginTeacherResponse (String message,
                                    TeacherResponse teacherResponse)  {
}

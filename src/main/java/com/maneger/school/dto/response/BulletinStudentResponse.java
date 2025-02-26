package com.maneger.school.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record BulletinStudentResponse(        UUID id,
                                              String studentName,
                                              String teacherName,
                                              String subjectName,
                                              String semester,
                                              String yearOfSemester,
                                              Double noteValue,
                                              LocalDateTime createdAt,
                                              LocalDateTime updatedAt) {
}

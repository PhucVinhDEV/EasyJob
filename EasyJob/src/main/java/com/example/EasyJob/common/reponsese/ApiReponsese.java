package com.example.EasyJob.common.reponsese;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiReponsese<T> {
    @Builder.Default
    private int status = 1000;
    private boolean hasErrors;
    private T result;
    private List<String> errors;
    private String timestamp;
}

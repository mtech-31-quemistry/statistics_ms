package com.quemistry.statistics_ms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MCQStatisticsDto {
    private long mcqId;
    private long cntAttempt;
    private long cntCorrectAnswer;
}

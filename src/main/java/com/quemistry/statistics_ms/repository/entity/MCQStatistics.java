package com.quemistry.statistics_ms.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="mcq_stats")
@IdClass(MCQStatisticsPK.class)
public class MCQStatistics {
    @Id
    private int attemptYear;
    @Id
    private int attemptMonth;
    @Id
    private int attemptDay;
    @Id
    private long mcqId;
    private long cntAttempt;
    private long cntCorrectAnswer;
}


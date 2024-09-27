package com.quemistry.statistics_ms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
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


package com.quemistry.statistics_ms.repository.entity;

import jakarta.persistence.Id;

import java.io.Serializable;

public class MCQStatisticsPK implements Serializable {

    private int attemptYear;
    private int attemptMonth;
    private int attemptDay;
    private long mcqId;

    public MCQStatisticsPK(int attemptYear, int attemptMonth, int attemptDay, long mcqId) {
        this.attemptYear = attemptYear;
        this.attemptMonth = attemptMonth;
        this.attemptDay = attemptDay;
        this.mcqId = mcqId;
    }
}

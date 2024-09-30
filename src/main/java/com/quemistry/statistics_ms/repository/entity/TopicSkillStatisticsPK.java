package com.quemistry.statistics_ms.repository.entity;

import java.io.Serializable;

public class TopicSkillStatisticsPK implements Serializable {

    private int attemptYear;
    private int attemptMonth;
    private int attemptDay;
    private long skillId;

    public TopicSkillStatisticsPK(int attemptYear, int attemptMonth, int attemptDay, long skillId) {
        this.attemptYear = attemptYear;
        this.attemptMonth = attemptMonth;
        this.attemptDay = attemptDay;
        this.skillId = skillId;
    }
}

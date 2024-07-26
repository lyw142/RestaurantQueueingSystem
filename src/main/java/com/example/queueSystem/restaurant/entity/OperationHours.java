package com.example.queueSystem.restaurant.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class OperationHours {
    private DayOfWeek day;
    private LocalTime openingTime;
    private LocalTime closingTime;

    public OperationHours(DayOfWeek day, LocalTime openingTime, LocalTime closingTime) {
        this.day = day;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }
}

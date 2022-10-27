package com.parsh.calenderapplication;

public class EmployeeInfo {
    private String date,slot;


    public EmployeeInfo() {
    }

    public EmployeeInfo(String Date,String slot) {
        this.date = Date;
        this.slot=slot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }
}

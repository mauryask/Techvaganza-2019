/*
 *@Author Shubham Maurya
 *@Date April 20, 2019
 *@Company  pnstech Inc.*/

package com.pnstech.finalactivity;

public class WinnerProfile {

    private String  name,year_sem,winnerEvent;

    public WinnerProfile() {
    }

    public WinnerProfile(String name, String year_sem, String winnerEvent) {
        this.name = name;
        this.year_sem = year_sem;
        this.winnerEvent=winnerEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear_sem() {
        return year_sem;
    }

    public void setYear_sem(String year_sem) {
        this.year_sem = year_sem;
    }

    public String getWinnerEvent() {
        return winnerEvent;
    }

    public void setWinnerEvent(String winnerEvent) {
        this.winnerEvent = winnerEvent;
    }
}

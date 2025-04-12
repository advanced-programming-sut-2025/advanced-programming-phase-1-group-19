package Modules;

import Modules.Enums.Season;

public class Time {
    private int hour;
    private int day;
    private Season season;

    public Time() {
//        TODO: set initial values
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public Season getSeason() {
        return season;
    }

    public String calculateWeekDay(){}

    public void nextSeason(){
        season = season.getNext();
    }

    public void nextDay() {
        if(day == 28) {
            hour = 1;
            nextSeason();
        }
        else {
            day++;
        }
    }

    public void nextHour() {
        if(hour == 22) {
            hour = 9;
            nextDay();
        }
        else {
            hour++;
        }

    }

    public int calDaysDifference(Time time){

    }

    public int calHoursDifference(Time time){

    }
}

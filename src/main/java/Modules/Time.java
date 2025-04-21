package Modules;

import Modules.Enums.Season;
import Modules.Interactions.Messages.GameMessage;

public class Time {
    private int hour;
    private int day;
    private Season season;

    public Time() {
        hour = 9;
        day = 1;
        season = Season.Spring;
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

    public String calculateWeekDay(){
        switch (getDay() % 7){
            case 0: {
                return "saturday";
            }
            case 1: {
                return "sunday";
            }
            case 2: {
                return "monday";
            }
            case 3: {
                return "tuesday";
            }
            case 4: {
                return "wednesday";
            }
            case 5: {
                return "thursday";
            }
            case 6: {
                return "friday";
            }
            default: {
                return "error";
            }
        }
    }

    public void nextSeason(){
        season = season.getNext();
    }

    public void nextDay() {
        if(day == 28) {
            day = 1;
        }
        else {
            day++;
        }
    }

    public void nextHour() {
        if(hour == 22) {
            hour = 9;
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

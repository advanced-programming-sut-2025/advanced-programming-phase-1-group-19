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
        season = Season.spring;
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
        Time time1 = Time.minimum(this, time);
        Time time2 = Time.maximum(this, time);

        int seasonDiff = time2.getSeason().ordinal() - time1.getSeason().ordinal();
        int dayDiff = time2.getDay() - time1.getDay();

        return seasonDiff * 28 + dayDiff;
    }

    public int calHoursDifference(Time time){
        int dayDiff = calDaysDifference(time);
        int hourDiff = Time.maximum(this, time).getHour() - Time.minimum(this, time).getHour();

        return dayDiff * 24 + hourDiff;
    }

    @Override
    public String toString() {
        return season.name() + " / " + day + "th day / " + hour;
    }

    public static Time minimum(Time time1, Time time2){
        if(time1.getSeason().ordinal() != time2.getSeason().ordinal()){
            if(time1.getSeason().ordinal() < time2.getSeason().ordinal()){
                return time1;
            }
            return time2;
        }
        else if(time1.getDay() != time2.getDay()){
            if(time1.getDay() < time2.getDay()){
                return time1;
            }
            return time2;
        }
        else{
            if(time1.getHour() < time2.getHour()){
                return time1;
            }
            return time2;
        }
    }

    public static Time maximum(Time time1, Time time2){
        Time temp = minimum(time1, time2);
        if(time1.equals(temp)){
            return time2;
        }
        return time1;
    }
}

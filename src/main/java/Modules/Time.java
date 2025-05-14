package Modules;

import Modules.Enums.Season;
import Modules.Interactions.Messages.GameMessage;

import java.io.Serializable;

public class Time implements Serializable {
    private int hour;
    private int day;
    private Season season;

    public Time() {
        hour = 9;
        day = 1;
        season = Season.spring;
    }
    public Time (Time time) {
        hour = time.hour;
        day = time.day;
        season = time.season;
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
    public static int getDayDifference(Time time1, Time time2){
        int seasonDiff = time2.getSeason().ordinal() - time1.getSeason().ordinal();
        int dayDiff = time2.getDay() - time1.getDay();
        return seasonDiff * 28 + dayDiff;
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

    public static Time addHour(Time time, int hour){
        Time newTime = new Time();
        newTime.hour = time.getHour()+hour;
        newTime.day = time.getDay();
        if(newTime.hour > 22){
            newTime.hour -= 22;
            newTime.hour += 9;
            newTime.day ++;
        }
        newTime.season = time.getSeason();
        return newTime;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Time){
            Time time = (Time)obj;
            boolean f1 = getHour() == time.getHour();
            boolean f2 = getDay() == time.getDay();
            boolean f3 = getSeason() == time.getSeason();
            return f1 && f2 && f3;
        }
        return false;
    }
}

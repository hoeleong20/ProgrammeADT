/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 * @author Hafiz Chew Hoe Leong
 */
public class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Date otherDate) {
        int[] dateArray1 = {year, month, day};
        int[] dateArray2 = {otherDate.getYear(), otherDate.getMonth(), otherDate.getDay()};

        int result=0;//if below is not working ,default value will be 0 and indicate same also

        int index = 0;

        while (index < 3 && (result = compareDigit(dateArray1[index], dateArray2[index])) == 0) {
            index++;
        }

        return result;
    }

    public int compareDigit(int digit1, int digit2) {
        int result;
        if (digit1 == digit2) {
            result = 0;
        } else if (digit1 < digit2) {
            result = -1;
        } else {
            result = 1;
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", day, month, year);
    }

}

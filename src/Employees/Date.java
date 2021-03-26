package Employees;

// clasa care modeleaza o data
public class Date implements Comparable<Date> {
    private int year;
    private int day;
    private int month;

    // constructorul
    public Date(int year, int month, int day) {
        this.year = year;
        this.day = day;
        this.month = month;
    }

    // gettere si settere
    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    // metoda compareTo pentru a realiza sortarea
    @Override
    public int compareTo(Date o) {
        if (getYear() > o.getYear())
            return 0;
        else if (getYear() == o.getYear()) {
            if (getMonth() > o.getMonth())
                return 0;
            else if (getMonth() == o.getMonth())
                if (getDay() > o.getDay())
                    return 0;
        }
        return 1;
    }

    // metoda toString uzitata pentru a efectua printarea datei
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}

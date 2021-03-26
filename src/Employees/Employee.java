package Employees;

import app.AutoService;

import java.util.Calendar;

public abstract class Employee{
    protected int ID;
    protected String lastname;
    protected String firstname;
    protected Date birth_date;
    protected Date hiring_date;
    protected double coefficient;

    public Employee(String lastname, String firstname, Date birth_date, Date hiring_date) {
        // auto-increment pentru ID
        this.ID = AutoService.getInstance().getEmployees().size();
        // celelalte campuri sunt setate cu datele de la tastatura
        this.lastname = lastname;
        this.firstname = firstname;
        this.birth_date = birth_date;
        this.hiring_date = hiring_date;
    }

    public double calculateSalary() {
        int years_old = getYearsInCompany();
        return years_old * coefficient * 1000;
    }

    // pentru anii in companie voi face aproximare prin adaos
    public int getYearsInCompany() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Date currentDate = new Date(currentYear, currentMonth, currentDay);
        Date comparisonDate = new Date(hiring_date.getYear(), currentMonth, currentDay);
        int number_years = 0;

        number_years = currentYear - hiring_date.getYear();
        if (comparisonDate.compareTo(currentDate) > 0)
            number_years++;
        return number_years;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getHiring_date() {
        return hiring_date;
    }

}

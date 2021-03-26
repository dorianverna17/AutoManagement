package Employees;

import java.util.Calendar;

public class Assistant extends Employee {
    public Assistant(String lastname, String firstname, Date birth_date, Date hiring_date) {
        super(lastname, firstname, birth_date, hiring_date);
        this.coefficient = 1;
    }

    public String toString() {
        return "ID: " + ID + " Assistant: " + firstname + " " + lastname;
    }
}

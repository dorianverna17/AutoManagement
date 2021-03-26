package Employees;

import java.util.ArrayList;

public class Mechanic extends Employee {
    public Mechanic(String lastname, String firstname, Date birth_date, Date hiring_date) {
        super(lastname, firstname, birth_date, hiring_date);
        this.coefficient = 1.5;
    }

    public String toString() {
        return "ID: " + ID + " Mechanic: " + firstname + " " + lastname;
    }
}

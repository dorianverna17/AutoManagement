package Employees;

public class Director extends Employee {
    public Director(String lastname, String firstname, Date birth_date, Date hiring_date) {
        super(lastname, firstname, birth_date, hiring_date);
        this.coefficient = 2;
    }

    public String toString() {
        return "ID: " + ID + " Director: " + firstname + " " + lastname;
    }
}

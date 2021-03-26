import Employees.*;
import app.AutoService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("./Employees.txt");
            Scanner scanner = new Scanner(file);
            String[] employee_details, birth_details, hire_details;
            Employee employee;
            Date birth, hire;
            while (scanner.hasNext()) {
                employee_details = scanner.nextLine().split(" ", -1);
                birth_details = employee_details[3].split("/");
                hire_details = employee_details[4].split("/");
                birth = new Date(Integer.parseInt(birth_details[0]),
                        Integer.parseInt(birth_details[1]),
                        Integer.parseInt(birth_details[2]));
                hire = new Date(Integer.parseInt(hire_details[0]),
                        Integer.parseInt(hire_details[1]),
                        Integer.parseInt(hire_details[2]));
                if (employee_details[0].equals("Director"))
                    employee = new Director(employee_details[1], employee_details[2], birth, hire);
                else if (employee_details[0].equals("Mechanic"))
                    employee = new Mechanic(employee_details[1], employee_details[2], birth, hire);
                else
                    employee = new Assistant(employee_details[1], employee_details[2], birth, hire);
                AutoService.getInstance().addEmployee(employee);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AutoService.getInstance().showMenu();
    }
}

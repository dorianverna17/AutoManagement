import Cars.Bus;
import Cars.Car;
import Cars.Truck;
import Cars.Vehicle;
import Employees.*;
import app.AutoService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void readEmployeesFromFile(String filename) {
        try {
            File file = new File(filename);
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
    }

    public static void readVehiclesFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            int id, year, nr_km;
            boolean diesel;
            String[] details;
            Vehicle vehicle = null;
            while (scanner.hasNext()) {
                details = scanner.nextLine().split(" ", -1);
                id = Integer.parseInt(details[0]);
                year = Integer.parseInt(details[1]);
                nr_km = Integer.parseInt(details[2]);
                if (details[3].equals("Da"))
                    diesel = true;
                else
                    diesel = false;
                if (filename.contains("Cars"))
                    vehicle = new Car(id, nr_km, year, diesel, details[4]);
                else if (filename.contains("Bus")) {
                    vehicle = new Bus(id, nr_km, year, diesel, Integer.parseInt(details[4]));
                } else if (filename.contains("Truck.txt")) {
                    vehicle = new Truck(id, nr_km, year, diesel, Double.parseDouble(details[4]));
                }
                AutoService.getInstance().putToTheWaitingQueueIfNoOneFree(vehicle);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readEmployeesFromFile("./Employees.txt");
        readVehiclesFromFile("./Cars.txt");
        readVehiclesFromFile("./Bus.txt");
        readVehiclesFromFile("./Truck.txt");
        AutoService.getInstance().showMenu();
    }
}

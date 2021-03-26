package app;

import Cars.Bus;
import Cars.Car;
import Cars.Truck;
import Cars.Vehicle;
import Employees.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class AutoService {
    private static AutoService app;
    private ArrayList<Employee> employees;

    // constructorul pentru singleton
    private AutoService() {
        employees = new ArrayList<Employee>();
    }

    // metoda getInstance pentru Singleton
    public static synchronized AutoService getInstance() {
        if (app == null)
            app = new AutoService();
        return app;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            if (validate(employee))
                employees.add(employee);
        } else {
            System.out.println("Acest angajat este deja in companie");
        }
    }

    private boolean validate(Employee employee) {
        boolean ok = true;
        if (employee.getLastname() == null) {
            System.out.println("Nu se poate adauga angajatul: Numele nu poate fi null");
            ok = false;
        } else if (employee.getLastname().length() > 30) {
            System.out.println("Nu se poate adauga angajatul: Numele nu trebuie sa depaseasca 30 de caractere");
            ok = false;
        }
        if (employee.getFirstname() == null) {
            System.out.println("Nu se poate adauga angajatul: Numele nu poate fi null");
            ok = false;
        } else if (employee.getFirstname().length() > 30) {
            System.out.println("Nu se poate adauga angajatul: Prenumele nu trebuie sa depaseasca 30 de caractere");
            ok = false;
        }
        // obtinerea datei curente
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Date birthDateComparison = new Date(currentYear - 18, currentMonth, currentDay);
        if (employee.getBirth_date().compareTo(birthDateComparison) == 0) {
            System.out.println("Nu se poate adauga angajatul: Trebuie sa aiba cel putin 18 ani impliniti");
            ok = false;
        }
        Date currentDate = new Date(currentYear, currentMonth, currentDay);
        if (employee.getHiring_date().compareTo(currentDate) == 0) {
            System.out.println("Nu se poate adauga angajatul: Data angajarii nu poate fi o data din viitor");
            ok = false;
        }
        return ok;
    }

    public void showEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public void removeEmployee(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getID() == id) {
                employees.remove(i);
                break;
            }
        }
        for (Employee employee : employees) {
            if (employee.getID() > id)
                employee.setID(employee.getID() - 1);
        }
    }

    public String getSalary(int id) {
        Double salary = null;
        for (Employee employee : employees) {
            if (employee.getID() == id) {
                salary = employee.calculateSalary();
                break;
            }
        }
        if (salary != null)
            return "Salariul angajatului cu ID-ul " + id + " este " + salary;
        return "Nu exista angajatul cu acest ID";
    }

    public void showDetails(int id) {
        Employee emp = null;
        for (Employee employee : employees) {
            if (employee.getID() == id) {
                emp = employee;
                break;
            }
        }
        if (emp != null) {
            System.out.println(emp.getFirstname() + " " + emp.getLastname());
            System.out.println("Data nasterii: " + emp.getBirth_date());
            System.out.println("Data angajarii: " + emp.getHiring_date());
            if (emp instanceof Director)
                System.out.println("Pozitia: Director");
            else if (emp instanceof Mechanic)
                System.out.println("Pozitia: Mecanic");
            else
                System.out.println("Pozitia: Asistent");
            System.out.println("Ani vechime: " + emp.getYearsInCompany());
        } else
            System.out.println("Nu a fost gasit acest angajat");
    }

    public void editEmployee(int id) {
        Employee emp = null;
        for (Employee employee : employees) {
            if (employee.getID() == id) {
                emp = employee;
                break;
            }
        }
        if (emp == null)
            System.out.println("Angajatul nu a fost gasit");
        else {
            // TODO check if details are valid
            Scanner sc = new Scanner(System.in);
            System.out.println("Alegeti din optiunile urmatoare: ");
            System.out.println("1) Schimbati Numele");
            System.out.println("2) Schimbati Prenumele");
            System.out.println("3) Schimbati Data Nasterii");
            System.out.println("4) Schimbati Positia");
            int aux = Integer.parseInt(sc.nextLine());
            if (aux == 1) {
                System.out.println("Tastati noul nume: ");
                emp.setLastname(sc.nextLine());
            } else if (aux == 2) {
                System.out.println("Tastati noul prenume: ");
                emp.setFirstname(sc.nextLine());
            } else if (aux == 3) {
                System.out.println("Tastati noua data a nasterii: ");
                String[] birth_details = sc.nextLine().split("/");
                emp.setBirth_date(new Date(Integer.parseInt(birth_details[0]),
                        Integer.parseInt(birth_details[1]),
                        Integer.parseInt(birth_details[2])));
            }
        }
    }

    public void showMenu() {
        if (AutoService.getInstance().getEmployees().size() == 0) {
            System.out.println("Atelierul Auto nu este deschis - nu exista angajati");
            return;
        } else
            System.out.println("Bine ati venit la atelierul nostru auto");
        Scanner scanner = new Scanner(System.in);
        String aux, firstname, lastname, position;
        int id, year, day, month;
        Date date;
        label:
        while (true) {
            if (AutoService.getInstance().getEmployees().size() == 0) {
                System.out.println("Atelierul a fost inchis - nu mai sunt angajati");
                break;
            }
            System.out.println("Alegeti una din optiunile de mai jos:");
            System.out.println("1) Afiseaza toti angajatii");
            System.out.println("2) Sterge un angajat");
            System.out.println("3) Adaugati un angajat");
            System.out.println("4) Calculati salariul unui angajat");
            System.out.println("5) Afisati detalii despre un angajat");
            System.out.println("6) Editati un angajat");
            System.out.println("7) Adaugati o noua masina in service");
            System.out.println("10) Inchide aplicatia");
            aux = scanner.nextLine();
            switch (aux) {
                case "10":
                    break label;
                case "1":
                    AutoService.getInstance().showEmployees();
                    break;
                case "2":
                    System.out.println("Dati ID-ul angajatului pe care vreti sa il stergeti:");
                    aux = scanner.nextLine();
                    id = Integer.parseInt(aux);
                    AutoService.getInstance().removeEmployee(id);
                    break;
                case "3":
                    Date currentDate = new Date(Calendar.getInstance().get(Calendar.YEAR),
                            Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    System.out.println("Numele angajatului: ");
                    firstname = scanner.nextLine();
                    System.out.println("Prenumele angajatului: ");
                    lastname = scanner.nextLine();
                    System.out.println("Anul nasterii: ");
                    year = Integer.parseInt(scanner.nextLine());
                    System.out.println("Luna: ");
                    month = Integer.parseInt(scanner.nextLine());
                    System.out.println("Ziua: ");
                    day = Integer.parseInt(scanner.nextLine());
                    date = new Date(year, month, day);
                    System.out.println("Pozitia: ");
                    position = scanner.nextLine();
                    if (position.equals("Director"))
                        AutoService.getInstance().addEmployee(new Director(lastname, firstname, date, currentDate));
                    else if (position.equals("Mechanic"))
                        AutoService.getInstance().addEmployee(new Mechanic(lastname, firstname, date, currentDate));
                    else if (position.equals("Assistant"))
                        AutoService.getInstance().addEmployee(new Assistant(lastname, firstname, date, currentDate));
                    else
                        System.out.println("Pozitia aleasa este invalida");
                    break;
                case "4":
                    System.out.println("Dati ID-ul angajatului caruia vreti sa ii calculati salariul:");
                    aux = scanner.nextLine();
                    id = Integer.parseInt(aux);
                    System.out.println(AutoService.getInstance().getSalary(id));
                    break;
                case "5":
                    System.out.println("Dati ID-ul angajatului:");
                    aux = scanner.nextLine();
                    id = Integer.parseInt(aux);
                    AutoService.getInstance().showDetails(id);
                    break;
                case "6":
                    System.out.println("Dati ID-ul angajatului pe care doriti sa il editati:");
                    aux = scanner.nextLine();
                    id = Integer.parseInt(aux);
                    AutoService.getInstance().editEmployee(id);
                    break;
                case "7":
                    AutoService.getInstance().addNewVehicle();
                    break;
                default:
                    System.out.println("Alegerea voastra este invalida");
                    break;
            }
            System.out.println();
            System.out.println();
        }
    }

    private void addNewVehicle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select what type of vehicle you want to add: ");
        System.out.println("1) Car");
        System.out.println("2) Bus");
        System.out.println("3) Truck");
        System.out.println("4) Back to Main Screen");
        String aux = sc.nextLine();
        if (!aux.equals("4")) {
            Vehicle vehicle = null;
            System.out.println("ID-ul vehiculului: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.println("Nr. de km: ");
            int nr_kilometres = Integer.parseInt(sc.nextLine());
            System.out.println("Anul fabricatiei: ");
            int year = Integer.parseInt(sc.nextLine());
            System.out.println("Motor Diesel (Da/Nu): ");
            String diesel = sc.nextLine();
            boolean flag_diesel = diesel.equals("Da");
            switch (aux) {
                case "1":
                    System.out.println("Tipul transmisiei: ");
                    String transmission = sc.nextLine();
                    if (transmission.equals("Automat")) {
                        vehicle = new Car(id, nr_kilometres, year, flag_diesel, "Automat");
                    } else {
                        vehicle = new Car(id, nr_kilometres, year, flag_diesel, "Manual");
                    }
                    break;
                case "2":
                    System.out.println("Numarul de locuri: ");
                    int seats = Integer.parseInt(sc.nextLine());
                    vehicle = new Bus(id, nr_kilometres, year, flag_diesel, seats);
                    break;
                case "3":
                    System.out.println("Tonajul camionului: ");
                    double tonnage = Double.parseDouble(sc.nextLine());
                    vehicle = new Truck(id, nr_kilometres, year, flag_diesel, tonnage);
                    break;
                default:
                    System.out.println("Optiunea aleasa este invalida");
            }
            if (vehicle != null)
                putVehicleInSystem(vehicle);
        }
    }

    private void putVehicleInSystem(Vehicle vehicle) {
        System.out.println();
        System.out.println("Alegeti din optiunile urmatoare: ");
        System.out.println("1) Dati vehiculul primului angajat disponibil");
        System.out.println("2) Alegeti un anumit angajat care sa se ocupe de vehicul");
        Scanner sc = new Scanner(System.in);
        String aux = sc.nextLine();
        if (aux.equals("1")) {
            putToTheFirstFreeEmployee(vehicle);
        } else if (aux.equals("2")) {
            putToTheChosenEmployee(vehicle);
        }
    }

    private void putToTheChosenEmployee(Vehicle vehicle) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Give me the ID of the Employee");
        int id = Integer.parseInt(sc.nextLine());
        if (id > employees.size())
            System.out.println("Nu exista acest angajat");
        else {
            if (vehicle instanceof Car) {
                if (!employees.get(id).addToCarList((Car) vehicle)) {
                    putToTheFirstFreeEmployee(vehicle);
                }
            } else if (vehicle instanceof Bus) {
                if (!employees.get(id).addBus((Bus) vehicle)) {
                    putToTheFirstFreeEmployee(vehicle);
                }
            } else {
                if (!employees.get(id).addTruck((Truck) vehicle)) {
                    putToTheFirstFreeEmployee(vehicle);
                }
            }
        }
    }

    private void putToTheFirstFreeEmployee(Vehicle vehicle) {
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        // Daca pot sa adaug in lista unui angajat atunci adaug
        for (int i = 0; i < employees.size(); i++) {
            if (vehicle instanceof Car) {
                if (employees.get(i).addToCarList((Car) vehicle)) {
                    flag = 1;
                    break;
                }
            } else if (vehicle instanceof Bus) {
                if (employees.get(i).addBus((Bus) vehicle)) {
                    flag = 1;
                    break;
                }
            } else {
                if (employees.get(i).addTruck((Truck) vehicle)) {
                    flag = 1;
                    break;
                }
            }
        }
        // Daca nu este disponibil niciun angajat, atunci pun
        // masina in asteptare la cel care are timpul de asteptare cel mai mic
        // sau aleg sa plec
        if (flag == 0) {
            System.out.println();
            System.out.println("1) Ramai la coada");
            System.out.println("2) Parasesti atelierul auto");
            if (sc.nextLine().equals("1"))
                putVehicleToTheSmallerQueue(vehicle);
        }
    }

    private void putVehicleToTheSmallerQueue(Vehicle vehicle) {
        Employee employee = null;
        int min = Integer.MAX_VALUE;
        if (vehicle instanceof Car) {
            for (int i = 0; i < employees.size(); i++) {
                if (min > employees.get(i).getTime_to_wait_cars()) {
                    employee = employees.get(i);
                }
            }
            if (employee != null)
                employee.addCarToQueue((Car)vehicle);
        } else if (vehicle instanceof Bus) {
            for (int i = 0; i < employees.size(); i++) {
                if (min > employees.get(i).getTime_to_wait_bus()) {
                    employee = employees.get(i);
                }
            }
            if (employee != null)
                employee.addBusToQueue((Bus)vehicle);
        } else {
            for (int i = 0; i < employees.size(); i++) {
                if (min > employees.get(i).getTime_to_wait_truck()) {
                    employee = employees.get(i);
                }
            }
            if (employee != null)
                employee.addTruckToQueue((Truck)vehicle);
        }
    }
}

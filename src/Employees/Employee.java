package Employees;

import Cars.Bus;
import Cars.Car;
import Cars.Truck;
import Cars.Vehicle;
import app.AutoService;

import java.util.ArrayList;
import java.util.Calendar;

// Voi considera ca orice angajat se poate ocupa de masini
// (fie el asistent, mecanic sau director - avand in vedere ca
// se precizeaza ca angajatii iau in primire masini)
public abstract class Employee{
    protected int ID;
    protected String lastname;
    protected String firstname;
    protected Date birth_date;
    protected Date hiring_date;
    protected double coefficient;

    // Aici retin vehiculele de care se ocupa in prezent angajatul
    protected ArrayList<Pair<Car, Integer>> car_list;
    protected Pair<Bus, Integer> bus;
    protected Pair<Truck, Integer> truck;

    // Voi modela cozile utilizand ArrayList-uri
    protected ArrayList<Pair<Car, Integer>> car_queue;
    protected ArrayList<Pair<Bus, Integer>> bus_queue;
    protected ArrayList<Pair<Truck, Integer>> truck_queue;

    // timp de asteptare - de ajutor in cazul in care toti angajatii sunt ocupati
    protected int time_to_wait_cars;
    protected int time_to_wait_bus;
    protected int time_to_wait_truck;

    public Employee(String lastname, String firstname, Date birth_date, Date hiring_date) {
        // auto-increment pentru ID
        this.ID = AutoService.getInstance().getEmployees().size();
        // celelalte campuri sunt setate cu datele de la tastatura
        this.lastname = lastname;
        this.firstname = firstname;
        this.birth_date = birth_date;
        this.hiring_date = hiring_date;
        car_queue = new ArrayList<>();
        bus = null;
        truck = null;
        time_to_wait_cars = 0;
        time_to_wait_bus = 0;
        time_to_wait_truck = 0;
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

    // voi considera acel timp estimativ ca fiind % 3 ore din numarul de km
    // pe care ii are la bord vehiculul
    public void addCarToQueue(Car car) {
        car_queue.add(car_queue.size() - 1, new Pair<>(car, car.getNr_kilometres() % 3 + 1));
        time_to_wait_cars += car.getNr_kilometres() % 3 + 1;
    }

    public void addBusToQueue(Bus bus) {
        bus_queue.add(car_queue.size() - 1, new Pair<>(bus, bus.getNr_kilometres() % 3 + 1));
        time_to_wait_bus += bus.getNr_kilometres() % 3 + 1;
    }

    public void addTruckToQueue(Truck truck) {
        truck_queue.add(truck_queue.size() - 1, new Pair<>(truck, truck.getNr_kilometres() % 3 + 1));
        time_to_wait_truck += truck.getNr_kilometres() % 3 + 1;
    }

    // metode pe care o apelez atunci angajatul finalizeaza masina, iar clientul o preia
    public void removeCar(int id) {
        int aux = -1;
        for (int i = 0; i < car_list.size(); i++) {
            if (car_list.get(i).getValue1().getID() == id)
                aux = i;
        }
        if (aux == -1) {
            System.out.println("De masina nu s-a ocupat acest angajat");
        } else {
            car_list.remove(aux);
            if (car_queue.size() != 0) {
                car_list.add(car_queue.get(0));
                car_queue.remove(0);
            }
        }
    }

    public void removeBus() {
        if (this.bus == null) {
            System.out.println("Acest angajat nu s-a ocupat de niciun autobuz");
        } else {
            if (bus_queue.size() != 0)
                this.bus = bus_queue.get(0);
        }
    }

    public void removeTruck() {
        if (this.truck == null) {
            System.out.println("Acest angajat nu s-a ocupat de niciun camion");
        } else {
            if (truck_queue.size() != 0)
                this.truck = truck_queue.get(0);
        }
    }

    public boolean addToCarList(Car car) {
        if (car_list.size() == 3)
            return false;
        else {
            car_list.add(new Pair<>(car, car.getNr_kilometres() % 3 + 1));
            if (car_list.size() == 3) {
                time_to_wait_cars += car.getNr_kilometres() % 3 + 1;
            }
        }
        return true;
    }

    public boolean addBus(Bus bus) {
        if (this.bus != null)
            return false;
        else {
            this.bus = new Pair<>(bus, bus.getNr_kilometres() % 3 + 1);
            time_to_wait_bus = bus.getNr_kilometres() % 3 + 1;
        }
        return true;
    }

    public boolean addTruck(Truck truck) {
        if (this.truck != null)
            return false;
        else {
            this.truck = new Pair<>(truck, truck.getNr_kilometres() % 3 + 1);
            time_to_wait_truck += truck.getNr_kilometres() % 3 + 1;
        }
        return true;
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

    public int getTime_to_wait_cars() {
        return time_to_wait_cars;
    }

    public int getTime_to_wait_bus() {
        return time_to_wait_bus;
    }

    public int getTime_to_wait_truck() {
        return time_to_wait_truck;
    }
}

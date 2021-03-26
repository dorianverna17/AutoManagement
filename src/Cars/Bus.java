package Cars;

import java.util.Calendar;

public class Bus extends Vehicle {
    private int nr_places;

    public Bus(int ID, int nr_kilometres, int fabrication_year, boolean type, int nr_places) {
        super(ID, nr_kilometres, fabrication_year, type);
        this.nr_places = nr_places;
    }

    @Override
    public double getInsurancePolicy() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int seniority = currentYear - fabrication_year;
        double result = seniority * 200;
        if (type)
            result += 1000;
        if (nr_kilometres > 200000)
            result += 1000;
        else if (nr_kilometres > 100000)
            result += 500;
        return result;
    }

    @Override
    public double getInsurancePolicyDiscount() {
        return (double) 10 / 100 * getInsurancePolicy();
    }

    public String toString() {
        return "ID: " + ID + "Year: " + fabrication_year + "Km: " +
                nr_kilometres + "Type: " + type + "Seats: " + nr_places;
    }
}

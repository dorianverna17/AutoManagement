package Cars;

import java.util.Calendar;

public class Truck extends Vehicle {
    private double tonnage;

    public Truck(int ID, int nr_kilometres, int fabrication_year, boolean type, double tonnage) {
        super(ID, nr_kilometres, fabrication_year, type);
        this.tonnage = tonnage;
    }

    @Override
    public double getInsurancePolicy() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int seniority = currentYear - fabrication_year;
        double result = seniority * 300;
        if (type)
            result += 1000;
        if (nr_kilometres > 800000)
            result += 700;
        return result;
    }

    @Override
    public double getInsurancePolicyDiscount() {
        return 0.15 * getInsurancePolicy();
    }

    public String toString() {
        String motor;
        if (type)
            motor = "Diesel";
        else
            motor = "Benzina";
        return "ID: " + ID + " An: " + fabrication_year + " Km: " +
                nr_kilometres + " Tip: " + motor + " Tonaj: " + tonnage;
    }
}

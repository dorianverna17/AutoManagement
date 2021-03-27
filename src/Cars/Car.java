package Cars;

import java.util.Calendar;

public class Car extends Vehicle {
    private String transmission;

    public Car(int ID, int nr_kilometres, int fabrication_year, boolean type, String transmission) {
        super(ID, nr_kilometres, fabrication_year, type);
        this.transmission = transmission;
    }

    @Override
    public double getInsurancePolicy() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int seniority = currentYear - fabrication_year;
        double result = seniority * 100;
        if (type)
            result += 500;
        if (nr_kilometres > 200000)
            result += 500;
        return result;
    }

    @Override
    public double getInsurancePolicyDiscount() {
        return 0.05 * getInsurancePolicy();
    }

    public String toString() {
        String motor;
        if (type)
            motor = "Diesel";
        else
            motor = "Benzina";
        return "ID: " + ID + " An: " + fabrication_year + " Km: " +
                nr_kilometres + " Tip: " + motor + " Transmisie: " + transmission;
    }
}

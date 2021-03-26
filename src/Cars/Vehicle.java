package Cars;

public abstract class Vehicle {
    protected int ID;
    protected int nr_kilometres;
    protected int fabrication_year;
    // true if diesel, false if another
    protected boolean type;

    public Vehicle(int ID, int nr_kilometres, int fabrication_year, boolean type) {
        this.ID = ID;
        this.nr_kilometres = nr_kilometres;
        this.fabrication_year = fabrication_year;
        this.type = type;
    }

    // metoda care realizeaza formula in forma standard
    public abstract double getInsurancePolicy();
    // metoda care realizeaza formula in forma DISCOUNT
    public abstract double getInsurancePolicyDiscount();

    public String toString() {
        return ID + " " + fabrication_year + " " + nr_kilometres + " " + type;
    }

    public int getNr_kilometres() {
        return nr_kilometres;
    }

    public int getID() {
        return ID;
    }
}

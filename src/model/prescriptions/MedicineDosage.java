package model.prescriptions;

import java.io.Serializable;

import model.enums.DosageUnit;

public class MedicineDosage implements Serializable {
    private final int quantity;
    private final DosageUnit unit;

    public MedicineDosage(int quantity, DosageUnit unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public DosageUnit getUnit() {
        return unit;
    }
}

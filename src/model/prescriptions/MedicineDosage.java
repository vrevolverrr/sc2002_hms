package model.prescriptions;

import model.SerializableCopyable;
import model.enums.DosageUnit;

public class MedicineDosage implements SerializableCopyable {
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

    @Override
    public String toString() {
        return quantity + " " + unit;
    }

    @Override
    public MedicineDosage copy() {
        return new MedicineDosage(getQuantity(), getUnit());
    }
}

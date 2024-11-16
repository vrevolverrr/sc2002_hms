package utils.enums;

import model.BaseModel;
import model.inventory.InventoryItem;
import model.users.Admin;
import model.users.Doctor;
import model.users.Patient;
import model.users.Pharmacist;

/**
 * An enum that represents the types of data that can be loaded from a file.
 */
public enum LoadableType {
    INVALID(null, null),
    PATIENT(Patient.class, new String[] {"name", "age", "password", "gender", "dob", "weight", "height", "phoneNumber", "emailAddress", "bloodType"}),
    DOCTOR(Doctor.class, new String[] {"name", "age", "password", "gender", "dob", "phoneNumber", "emailAddress", "specialisation"}),
    ADMIN(Admin.class, new String[] {"name", "age", "password", "gender", "dob", "phoneNumber", "emailAddress"}),
    PHARMACIST(Pharmacist.class, new String[] {"name", "age", "password", "gender", "dob", "phoneNumber", "emailAddress"}),
    INVENTORY(InventoryItem.class, new String[] {"itemName", "stock", "stockLevelAlert"});

    /**
     * The class of the model that can be loaded.
     */
    private final Class<? extends BaseModel> modelClass;
    
    /**
     * The headers of the CSV file for the model.
     */
    private final String[] headers;

    private LoadableType(Class<? extends BaseModel> modelClass, String[] headers) {
        this.modelClass = modelClass;
        this.headers = headers;
    }

    public Class<? extends BaseModel> getModelClass() {
        return modelClass;
    }

    public String[] getHeaders() {
        return headers;
    }
}

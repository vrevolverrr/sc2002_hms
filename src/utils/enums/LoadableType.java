package utils.enums;

import model.BaseModel;
import model.inventory.InventoryItem;
import model.users.Admin;
import model.users.Doctor;
import model.users.Patient;
import model.users.Pharmacist;

/**
 * An enum that represents the types of data that can be loaded from a file.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public enum LoadableType {
    /**
     * Represents an invalid type.
     */
    INVALID(null, null),
    /**
     * Represents a patient.
     */
    PATIENT(Patient.class, new String[] {"name", "age", "password", "gender", "dob", "weight", "height", "phoneNumber", "emailAddress", "bloodType"}),
    /**
     * Represents a doctor.
     */
    DOCTOR(Doctor.class, new String[] {"name", "age", "password", "gender", "dob", "phoneNumber", "emailAddress", "specialisation"}),
    /**
     * Represents an admin.
     */
    ADMIN(Admin.class, new String[] {"name", "age", "password", "gender", "dob", "phoneNumber", "emailAddress"}),
    /**
     * Represents a pharmacist.
     */
    PHARMACIST(Pharmacist.class, new String[] {"name", "age", "password", "gender", "dob", "phoneNumber", "emailAddress"}),
    /**
     * Represents an inventory item.
     */
    INVENTORY(InventoryItem.class, new String[] {"itemName", "stock", "stockLevelAlert"});

    /**
     * The class of the model that can be loaded.
     */
    private final Class<? extends BaseModel> modelClass;
    
    /**
     * The headers of the CSV file for the model.
     */
    private final String[] headers;

    /**
     * Constructor for the {@link LoadableType} class.
     * @param modelClass the class of the model that can be loaded.
     * @param headers the headers of the CSV file for the model.
     */
    private LoadableType(Class<? extends BaseModel> modelClass, String[] headers) {
        this.modelClass = modelClass;
        this.headers = headers;
    }

    /**
     * Gets the class of the model that can be loaded.
     * @return the class of the model that can be loaded.
     */
    public Class<? extends BaseModel> getModelClass() {
        return modelClass;
    }

    /**
     * Gets the headers of the CSV file for the model.
     * @return the headers of the CSV file for the model.
     */
    public String[] getHeaders() {
        return headers;
    }
}

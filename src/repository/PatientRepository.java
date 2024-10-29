package repository;

import java.util.*;

import model.Patient;
import model.User;
import model.enums.UserRole;

/**
 * A wrapper repository that wraps over an existing instance of {@link UserRepository} to provide
 * {@link Patient} specific functionality.
 */
public class PatientRepository {    
    private final Map<String, Patient> items = new HashMap<String, Patient>();
    
    public PatientRepository(UserRepository repository) {
        for (User user : repository.getItems().values()) {
            if (user.getRole() != UserRole.PATIENT) {
                continue;
            }

            items.put(user.getId(), (Patient) user);
        }   
    }
}

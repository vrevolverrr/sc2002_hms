package repository;

import java.util.*;
import java.util.stream.*;
import java.util.function.Predicate;

import model.enums.UserRole;
import model.users.Patient;
import model.users.User;
import repository.interfaces.IPatientRepository;
import repository.interfaces.IUserRepository;

/**
 * An implementation of {@link IPatientRepository} that operates on {@link Patient} data models.
 * It extends the functionality of a {@link UserRepository} through dependency injection to focus on
 * {@link User}s that are specifically {@link Patient}s.
 * 
 * @see <a href="https://www.geeksforgeeks.org/dependency-injection-di-design-pattern/">Dependency Injection</a>
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-10-29
 */
public class PatientRepository implements IPatientRepository {    
    /**
     * The {@link UserRepository} dependency for managing user data.
     */
    private final IUserRepository repository;
    
    /**
     * Constructs a new {@link PatientRepository} instance.
     *
     * @param repository an implementation of {@link IUserRepository} to delegate underlying operations.
     */
    public PatientRepository(IUserRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all {@link Patient} entries from the repository.
     *
     * @return a map of patient IDs to {@link Patient} objects.
     */
    @Override
    public Map<String, Patient> getItems() {
        return this.repository.getItems().entrySet().stream().filter(
            entry -> entry.getValue().getRole() == UserRole.PATIENT).collect(
                Collectors.toMap(e -> (String) e.getKey(), e -> (Patient) e.getValue()));
    }

    /**
     * Retrieves all {@link Patient} entries as a list.
     *
     * @return a list of all {@link Patient} objects in the repository.
     */
    @Override
    public List<Patient> findAll() {
        return this.getItems().values().stream().collect(Collectors.toList());
    }

    /**
     * Counts the total number of {@link Patient} entries in the repository.
     *
     * @return the total number of patients.
     */
    @Override
    public int count() {
        return this.getItems().size();
    }

    /**
     * Generates a unique ID for a new {@link Patient}.
     *
     * @return the generated patient ID.
     */
    @SuppressWarnings("unused")
	@Override
    public String generateId() {
        return "P" + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }

    /**
     * Finds a {@link Patient} by their unique ID.
     *
     * @param patientId the ID of the patient.
     * @return the {@link Patient} object matching the ID, or {@code null} if not found.
     */
    @Override
    public Patient findById(String patientId) {
        User user =  repository.findById(patientId);

        if (user.getRole() != UserRole.PATIENT) {
            return null;
        }

        return (Patient) user;
    }

    /**
     * Finds all {@link Patient} entries that match a given predicate.
     *
     * @param predicate the condition to filter patients.
     * @return a list of patients matching the predicate.
     */
    @Override
    public List<Patient> findBy(Predicate<Patient> predicate) {
        return repository.findBy(
            // Filter out all patients
            (User user) -> user.getRole() == UserRole.PATIENT).stream().map((User user) -> (Patient) user)
            // Then filter by the predicate
            .filter(predicate).toList();
    }

    /**
     * Checks if a {@link Patient} with the given ID exists.
     *
     * @param patientId the ID of the patient.
     * @return {@code true} if a patient with the ID exists, {@code false} otherwise.
     */
    @Override
    public boolean exists(String patientId) {
        return this.findById(patientId) != null;
    }

    /**
     * Checks if a {@link Patient} with the given ID exists.
     *
     * @param patientId the ID of the patient.
     * @return {@code true} if a patient with the ID exists, {@code false} otherwise.
     */
    @Override
    public boolean exists(Predicate<Patient> predicate) {
        return this.findBy(predicate).size() > 0;
    }

   /**
     * Saves a {@link Patient} to the repository.
     *
     * @param item the {@link Patient} to save.
     * @return the saved {@link Patient}.
     */
    @Override
    public Patient save(Patient item) {
        if (item.getId() == null || item.getId().isBlank()) {
            item.setId(generateId());
        }

        repository.save(item);
        return item;
    }

    /**
     * Saves a collection of {@link Patient} objects to the repository.
     *
     * @param collection the list of patients to save.
     * @return the same list of saved patients.
     */
    @Override
    public List<Patient> save(List<Patient> collection) {
        // Casts each Patient as a User then save to the underlying UserRepositoru.
        repository.save(collection.stream().map((Patient patient) -> (User) patient).toList());
        return collection;
    }

    /**
     * Deletes a {@link Patient} by their unique ID.
     *
     * @param patientId the ID of the patient to delete.
     * @return the deleted {@link Patient}, or {@code null} if not found.
     */
    @Override
    public Patient deleteById(String patientId) {
        if (exists(patientId)) {
            return (Patient) repository.deleteById(patientId);
        }
        
        return null;
    }

    /**
     * Removes all {@link Patient} entries from the repository.
     */
    @Override
    public void clear() {
        repository.findBy(
            // Filter out all patients
            (User user) -> user.getRole() == UserRole.PATIENT).stream().forEach((User user) -> {
                repository.deleteById(user.getId());
            });
    }
}

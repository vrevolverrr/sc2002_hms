/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-29
 */

package repository;

import java.util.*;
import java.util.stream.*;
import java.util.function.Predicate;

import model.enums.UserRole;
import model.users.Patient;
import model.users.User;

/**
 * An implementation of {@link Repository} that on {@link Patient} data models. It extends
 * the functionality of a {@link UserRepository} by dependency injection to work on 
 * {@code Users} that are actually {@code Patients}.
 * @see https://www.geeksforgeeks.org/dependency-injection-di-design-pattern/
 */
public class PatientRepository implements Repository<Patient> {    
    /**
     * The {@link UserRepository} dependency to extend.
     */
    private final UserRepository repository;
    
    /**
     * The constructor of {@link PatientRepository}. 
     * @param repository an instance of a {@link UserRepository}.
     */
    public PatientRepository(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets all the {@code Patients} stored in the repository.
     * @return the the entries of {@link Patient} stored.
     */
    @Override
    public Map<String, Patient> getItems() {
        return this.repository.getItems().entrySet().stream().filter(
            entry -> entry.getValue().getRole() == UserRole.PATIENT).collect(
                Collectors.toMap(e -> (String) e.getKey(), e -> (Patient) e.getValue()));
    }

    /**
     * Gets the total number of {@code Patients} stored in the repository.
     * @return the number of entries of {@link Patient} stored.
     */
    @Override
    public int count() {
        return this.getItems().size();
    }

    /**
     * Finds the {@link Patient} matching the patient ID.
     * @param patientId the ID of the patient.
     * @return the {@link Patient} matching the ID, or null if no such patient exists.
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
     * Finds all the {@link Patient} that satisfy the given predicate (condition).
     * @param predicate the predicate to match against
     * @return the list of {@link Patient} matching the predicate
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
     * Checks whether a {@link Patient} with the given {@code patiendId} exists.
     * @param patientId the ID of the patient.
     * @return whether the {@link Patient} matching the ID exists.
     */
    @Override
    public boolean exists(String patientId) {
        return this.findById(patientId) != null;
    }

    /**
     * Checks whether a {@link Patient} satisfying a given {@code predicate} exists.
     * @param predicate the predicate (condition) to match against.
     * @return whether such {@link Patient} exists.
     */
    @Override
    public boolean exists(Predicate<Patient> predicate) {
        return this.findBy(predicate).size() > 0;
    }

    /**
     * Saves a {@link Patient} to the underlying repository.
     * @param item the {@link Patient} to be saved.
     * @return the same reference to the {@link Patient}.
     */
    @Override
    public Patient save(Patient item) {
        repository.save(item);
        return item;
    }

    /**
     * Saves all the {@link Patients} to the underlying repository.
     * @param collection the list of {@link Patient} to be saved.
     * @return the same reference to the collection of {@link Patient}.
     */
    @Override
    public List<Patient> save(List<Patient> collection) {
        // Casts each Patient as a User then save to the underlying UserRepositoru.
        repository.save(collection.stream().map((Patient patient) -> (User) patient).toList());
        return collection;
    }

    /**
     * Deletes the {@link Patient} matching the given ID.
     * @param patientId the ID of the patient.
     * @return the {@link Patient} that was deleted, or null if no such patient exists.
     */
    @Override
    public Patient deleteById(String patientId) {
        if (exists(patientId)) {
            return (Patient) repository.deleteById(patientId);
        }
        
        return null;
    }

    /**
     * Clears all the {@link Patient} stored in the repository.
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

package repository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.enums.UserRole;
import model.users.Doctor;
import model.users.User;
import repository.interfaces.IDoctorRepository;
import repository.interfaces.IUserRepository;

/**
 * An implementation of {@link IDoctorRepository} that on {@link Doctor} data models. It extends
 * the functionality of a {@link UserRepository} by dependency injection to work on 
 * {@code Users} that are actually {@code Doctor}.
 * @see https://www.geeksforgeeks.org/dependency-injection-di-design-pattern/
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorRepository implements IDoctorRepository {
    /**
     * The {@link IUserRepository} dependency to extend.
     */
    private final IUserRepository repository;
    
    /**
     * The constructor of {@link DoctorRepository}. 
     * @param repository the instance of an implementation of {@link IUserRepository}.
     */
    public DoctorRepository(IUserRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Gets all the {@code Doctors} stored in the repository.
     * @return the the entries of {@link Doctor} stored.
     */
    @Override
    public Map<String, Doctor> getItems() {
        return this.repository.getItems().entrySet().stream()
            .filter(entry -> entry.getValue().getRole() == UserRole.DOCTOR)
            .collect(Collectors.toMap(Map.Entry::getKey, e -> (Doctor) e.getValue()));
    }

    /**
     * Gets all the {@link Doctor} stored in the repository.
     * @return the list of all the {@link Doctor} stored.
     */
    @Override
    public List<Doctor> findAll() {
        return this.getItems().values().stream().collect(Collectors.toList());
    }

    /**
     * Generates a new ID for a {@link Doctor}.
     * @return the new ID for a {@link Doctor}.
     */
    @SuppressWarnings("unused")
	@Override
    public String generateId() {
        return "D" + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }


    /**
     * Gets the total number of {@link Doctor} stored in the repository.
     * @return the number of entries of {@link Doctor} stored.
     */
    @Override
    public int count() {
        return this.getItems().size();
    }

    /**
     * Finds the {@link Doctor} matching the doctor ID.
     * @param doctorId the ID of the doctor.
     * @return the {@link Doctor} matching the ID, or null if no such doctor exists.
     */
    @Override
    public Doctor findById(String doctorId) {
        User user = repository.findById(doctorId);
        if (user == null || user.getRole() != UserRole.DOCTOR) {
            return null; // Return null if no user or user is not a doctor
        }
        return (Doctor) user;
    }

    /**
     * Finds all the {@link Doctor} that satisfy the given predicate (condition).
     * @param predicate the predicate to match against
     * @return the list of {@link Doctor} matching the predicate
     */
    @Override
    public List<Doctor> findBy(Predicate<Doctor> predicate) {
        return repository.findBy(user -> user.getRole() == UserRole.DOCTOR).stream()
            .map(user -> (Doctor) user) // Cast User to Doctor
            .filter(predicate)          // Apply the given predicate
            .collect(Collectors.toList()); // Collect results as a List
    }

    /**
     * Checks whether a {@link Doctor} that matches a given ID exists.
     * @param doctorId the ID to match against.
     * @return whether the doctor exists.
     */
    @Override
    public boolean exists(String doctorId) {
        return this.findById(doctorId) != null; // Return true if doctor exists
    }

    /**
     * Checks whether a {@link Doctor} that matches a given predicate exists.
     * @param predicate the predicate to match against.
     * @return whether the doctor exists.
     */
    @Override
    public boolean exists(Predicate<Doctor> predicate) {
        return !this.findBy(predicate).isEmpty(); // Return true if any doctor matches the predicate
    }

    /**
     * Saves a {@link Doctor} to the repository.
     * @param item the doctor to save.
     * @return the saved doctor.
     */
    @Override
    public Doctor save(Doctor item) {
        if (item.getId() == null || item.getId().isBlank()) {
            item.setId(generateId());
        }

        repository.save(item);
        return item;
    }

    /**
     * Saves a collection of {@link Doctor} to the repository.
     * @param collection the list of doctors to save.
     * @return the saved collection of doctors.
     */
    @Override
    public List<Doctor> save(List<Doctor> collection) {
        repository.save(collection.stream().map(doctor -> (User) doctor).collect(Collectors.toList()));
        return collection; // Return the saved collection of Doctors
    }

    /**
     * Removes a {@link Doctor} from the repository.
     * @param doctorId the ID of the doctor to remove.
     * @return the removed doctor, or null if no such doctor exists.
     */
    @Override
    public Doctor deleteById(String doctorId) {
        if (exists(doctorId)) { // Check if doctor exists before deletion
            return (Doctor) repository.deleteById(doctorId); // Delete and return the removed doctor
        }
        return null;
    }

    /**
     * Removes all the {@link Doctor} from the repository.
     */
    @Override
    public void clear() {
        repository.findBy(user -> user.getRole() == UserRole.DOCTOR) // Find all users with DOCTOR role
                  .forEach(user -> repository.deleteById(user.getId())); // Delete each doctor by ID
    }
}
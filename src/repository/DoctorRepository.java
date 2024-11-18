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
 * An implementation of {@link IDoctorRepository} that operates on {@link Doctor} data models.
 * It extends the functionality of a {@link UserRepository} through dependency injection to focus on
 * {@link User}s that are specifically {@link Doctor}s.
 * 
 * @see <a href="https://www.geeksforgeeks.org/dependency-injection-di-design-pattern/">Dependency Injection</a>
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorRepository implements IDoctorRepository {
    /**
     * The {@link IUserRepository} dependency used to manage user data.
     */
    private final IUserRepository repository;
    
    /**
     * Constructs a {@link DoctorRepository} with the specified {@link IUserRepository} instance.
     * 
     * @param repository the instance of an implementation of {@link IUserRepository}.
     */
    public DoctorRepository(IUserRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Retrieves all the {@link Doctor}s stored in the repository.
     * 
     * @return a {@link Map} of all {@link Doctor}s, with their IDs as keys.
     */
    @Override
    public Map<String, Doctor> getItems() {
        return this.repository.getItems().entrySet().stream()
            .filter(entry -> entry.getValue().getRole() == UserRole.DOCTOR)
            .collect(Collectors.toMap(Map.Entry::getKey, e -> (Doctor) e.getValue()));
    }

    /**
     * Retrieves a list of all {@link Doctor}s stored in the repository.
     * 
     * @return a {@link List} of all {@link Doctor}s.
     */
    @Override
    public List<Doctor> findAll() {
        return this.getItems().values().stream().collect(Collectors.toList());
    }

    /**
     * Generates a new unique ID for a {@link Doctor}.
     * 
     * @return the generated ID.
     */
    @SuppressWarnings("unused")
	@Override
    public String generateId() {
        return "D" + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }


    /**
     * Counts the total number of {@link Doctor}s stored in the repository.
     * 
     * @return the number of {@link Doctor}s.
     */
    @Override
    public int count() {
        return this.getItems().size();
    }

    /**
     * Finds a {@link Doctor} by their unique ID.
     * 
     * @param doctorId the ID of the {@link Doctor}.
     * @return the {@link Doctor} matching the ID, or {@code null} if not found.
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
     * Finds all {@link Doctor}s that satisfy the specified predicate.
     * 
     * @param predicate the condition to filter {@link Doctor}s.
     * @return a {@link List} of {@link Doctor}s that match the predicate.
     */
    @Override
    public List<Doctor> findBy(Predicate<Doctor> predicate) {
        return repository.findBy(user -> user.getRole() == UserRole.DOCTOR).stream()
            .map(user -> (Doctor) user) // Cast User to Doctor
            .filter(predicate)          // Apply the given predicate
            .collect(Collectors.toList()); // Collect results as a List
    }

    /**
     * Checks if a {@link Doctor} with the specified ID exists.
     * 
     * @param doctorId the ID to check.
     * @return {@code true} if a matching {@link Doctor} exists, {@code false} otherwise.
     */
    @Override
    public boolean exists(String doctorId) {
        return this.findById(doctorId) != null; // Return true if doctor exists
    }

    /**
     * Checks if any {@link Doctor} matches the specified predicate.
     * 
     * @param predicate the condition to check.
     * @return {@code true} if a matching {@link Doctor} exists, {@code false} otherwise.
     */
    @Override
    public boolean exists(Predicate<Doctor> predicate) {
        return !this.findBy(predicate).isEmpty(); // Return true if any doctor matches the predicate
    }

    /**
     * Saves a {@link Doctor} to the repository.
     * 
     * @param item the {@link Doctor} to save.
     * @return the saved {@link Doctor}.
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
     * Saves a collection of {@link Doctor}s to the repository.
     * 
     * @param collection the {@link List} of {@link Doctor}s to save.
     * @return the saved {@link List} of {@link Doctor}s.
     */
    @Override
    public List<Doctor> save(List<Doctor> collection) {
        repository.save(collection.stream().map(doctor -> (User) doctor).collect(Collectors.toList()));
        return collection; // Return the saved collection of Doctors
    }

    /**
     * Deletes a {@link Doctor} by their unique ID.
     * 
     * @param doctorId the ID of the {@link Doctor} to delete.
     * @return the deleted {@link Doctor}, or {@code null} if no such doctor exists.
     */
    @Override
    public Doctor deleteById(String doctorId) {
        if (exists(doctorId)) { // Check if doctor exists before deletion
            return (Doctor) repository.deleteById(doctorId); // Delete and return the removed doctor
        }
        return null;
    }

    /**
     * Deletes all {@link Doctor}s from the repository.
     */
    @Override
    public void clear() {
        repository.findBy(user -> user.getRole() == UserRole.DOCTOR) // Find all users with DOCTOR role
                  .forEach(user -> repository.deleteById(user.getId())); // Delete each doctor by ID
    }
}

 package repository;

 import java.util.*;
 import java.util.stream.*;
 import java.util.function.Predicate;

import model.enums.UserRole;
import model.users.Admin;
import model.users.User;
import repository.interfaces.IAdminRepository;
import repository.interfaces.IRepository;
import repository.interfaces.IUserRepository;
 
 /**
  * An implementation of {@link IRepository} that on {@link Admin} data models. It extends
  * the functionality of a {@link UserRepository} by dependency injection to work on 
  * {@link User} that are actually {@link Admin}.
  * @see https://www.geeksforgeeks.org/dependency-injection-di-design-pattern/
  *
  * @author Bryan Soong, Joyce Lee
  * @version 1.0
  * @since 2024-11-17
  */
 public class AdminRepository implements IAdminRepository {    
    /**
     * The {@link IUserRepository} dependency to extend.
    */
     private final IUserRepository repository;

    /**
     * The prefix for the ID of an {@link Admin}.
    */
    public static final String ID_PREFIX = "A"; 
     
    /**
     * The constructor of {@link PatientRepository}. 
    * @param repository an instance of an implementation of {@link IUserRepository}.
    */
    public AdminRepository(IUserRepository repository) {
        this.repository = repository;
    }
 
    /**
     * Gets all the {@link Admin} stored in the repository.
    * @return the the entries of {@link Admin} stored.
    */
    @Override
    public Map<String, Admin> getItems() {
        return this.repository.getItems().entrySet().stream().filter(
            entry -> entry.getValue().getRole() == UserRole.ADMIN).collect(
                Collectors.toMap(e -> (String) e.getKey(), e -> (Admin) e.getValue()));
    }

    /**
    * Gets all the {@link Admin} stored in the repository.
    * @return the {@link List} of all the {@link Admin} stored.
    */
    @Override
    public List<Admin> findAll() {
        return this.getItems().values().stream().collect(Collectors.toList());
    }
 
     /**
     * Generates a new unique ID for an {@link Admin}.
     * 
     * @return a {@link String} representing the new unique ID.
     */
    @SuppressWarnings("unused")
	@Override
    public String generateId() {
        return ID_PREFIX + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }

    /**
     * Counts the total number of {@link Admin} in the repository.
     * 
     * @return the total count of {@link Admin}.
     */
    @Override
    public int count() {
        return this.getItems().size();
    }

    /**
     * Finds an {@link Admin} by its unique ID.
     * 
     * @param adminId the ID of the {@link Admin}.
     * @return the {@link Admin} matching the ID, or {@code null} if no such {@link Admin} exists.
     */
    @Override
    public Admin findById(String adminId) {
        User user =  repository.findById(adminId);

        if (user.getRole() != UserRole.ADMIN) {
            return null;
        }

        return (Admin) user;
    }
 
    /**
     * Finds all the {@link Admin} that satisfy the given predicate (condition).
    * @param predicate the predicate to match against
    * @return the {@link List} of {@link Admin} matching the predicate
    */
    @Override
    public List<Admin> findBy(Predicate<Admin> predicate) {
        return repository.findBy(
            // Filter out all admins
            (User user) -> user.getRole() == UserRole.ADMIN).stream().map((User user) -> (Admin) user)
            // Then filter by the predicate
            .filter(predicate).toList();
    }

    /**
     * Checks if an {@link Admin} with the specified ID exists.
     * 
     * @param adminId the ID of the {@link Admin}.
     * @return {@code true} if the {@link Admin} exists; otherwise, {@code false}.
     */
    @Override
    public boolean exists(String adminId) {
        return this.findById(adminId) != null;
    }

    /**
     * Checks if any {@link Admin} satisfies the given predicate.
     * 
     * @param predicate the condition to match against.
     * @return {@code true} if at least one {@link Admin} satisfies the predicate; otherwise, {@code false}.
     */
    @Override
    public boolean exists(Predicate<Admin> predicate) {
        return this.findBy(predicate).size() > 0;
    }

    /**
     * Saves an {@link Admin} to the repository.
     * 
     * @param item the {@link Admin} to save.
     * @return the saved {@link Admin}.
     */
    @Override
    public Admin save(Admin item) {
    if (item.getId() == null || item.getId().isBlank()) { // If the doctor does not have an ID
        item.setId(generateId()); // Generate a new ID for the doctor
    }

        repository.save(item);
        return item;
    }

    /**
     * Saves a collection of {@link Admin}s to the repository.
     * 
     * @param collection the {@link List} of {@link Admin}s to save.
     * @return the saved collection of {@link Admin}s.
     */
    @Override
    public List<Admin> save(List<Admin> collection) {
        // Casts each Admin as a User then save to the underlying UserRepositoru.
        repository.save(collection.stream().map((Admin admin) -> (User) admin).toList());
        return collection;
    }

    /**
     * Deletes the {@link Admin} matching the given ID.
    * @param adminId the ID of the admin.
    * @return the {@link Admin} that was deleted, or null if no such admin exists.
    */
    @Override
    public Admin deleteById(String adminId) {
        if (exists(adminId)) {
            return (Admin) repository.deleteById(adminId);
        }
        
        return null;
    }

    /**
    * Clears all the {@link Admin} stored in the repository.
    */
    @Override
    public void clear() {
        repository.findBy(
            // Filter out all patients
            (User user) -> user.getRole() == UserRole.ADMIN).stream().forEach((User user) -> {
                repository.deleteById(user.getId());
            });
    }
}
 
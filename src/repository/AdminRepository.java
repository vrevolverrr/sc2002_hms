
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
  * {@code Users} that are actually {@code Admins}.
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
     * Gets all the {@code Admins} stored in the repository.
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
    * @return the list of all the {@link Admin} stored.
    */
    @Override
    public List<Admin> findAll() {
        return this.getItems().values().stream().collect(Collectors.toList());
    }
 
     /**
      * Generates a new ID for a {@link Admin}.
      */
    @SuppressWarnings("unused")
    @Override
    public String generateId() {
        return ID_PREFIX + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }

    /**
     * Gets the total number of {@code Admin} stored in the repository.
    * @return the number of entries of {@link Admin} stored.
    */
    @Override
    public int count() {
        return this.getItems().size();
    }

    /**
     * Finds the {@link Admin} matching the admin ID.
    * @param adminId the ID of the admin.
    * @return the {@link Admin} matching the ID, or null if no such admin exists.
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
    * @return the list of {@link Admin} matching the predicate
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
     * Checks whether a {@link Admin} with the given {@code adminId} exists.
    * @param adminId the ID of the admin.
    * @return whether the {@link Admin} matching the ID exists.
    */
    @Override
    public boolean exists(String adminId) {
        return this.findById(adminId) != null;
    }

    /**
     * Checks whether a {@link Admin} satisfying a given {@code predicate} exists.
    * @param predicate the predicate (condition) to match against.
    * @return whether such {@link Admin} exists.
    */
    @Override
    public boolean exists(Predicate<Admin> predicate) {
        return this.findBy(predicate).size() > 0;
    }

    /**
     * Saves a {@link Admin} to the underlying repository.
    * @param item the {@link Admin} to be saved.
    * @return the same reference to the {@link Admin}.
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
     * Saves all the {@link Admin} to the underlying repository.
    * @param collection the list of {@link Admin} to be saved.
    * @return the same reference to the collection of {@link Admin}.
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
 
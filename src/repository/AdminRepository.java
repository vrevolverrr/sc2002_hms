
 package repository;

 import java.util.*;
 import java.util.stream.*;
 import java.util.function.Predicate;
 
 import model.Admin;
 import model.User;
 import model.enums.UserRole;
 
 /**
  * An implementation of {@link Repository} that on {@link Admin} data models. It extends
  * the functionality of a {@link UserRepository} by dependency injection to work on 
  * {@code Users} that are actually {@code Admins}.
  * @see https://www.geeksforgeeks.org/dependency-injection-di-design-pattern/
  */
 public class AdminRepository implements Repository<Admin> {    
     /**
      * The {@link UserRepository} dependency to extend.
      */
     private final UserRepository repository;
     
     /**
      * The constructor of {@link PatientRepository}. 
      * @param repository an instance of a {@link UserRepository}.
      */
     public AdminRepository(UserRepository repository) {
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
 
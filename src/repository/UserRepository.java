/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-29
 */

package repository;

import java.util.*;

import model.enums.UserRole;
import model.users.User;
import repository.interfaces.IRepository;
import repository.interfaces.IUserRepository;

/**
 * A {@link IRepository} for {@link User} with specific functionality.
 */
public class UserRepository extends BaseRepository<User> implements IUserRepository {
    /**
     * The filename of the data file to load.
     */
    final static String FILENAME = "users.dat";

    /**
     * The prefix for the ID of a {@link User}, though it is not used since each specific
     * user role has its own ID prefix.
     */
    final static String ID_PREFIX = "U";
    
    /**
     * The constructor of a {@link UserRepository}.
     */
    public UserRepository() {
        super(FILENAME);
    }

    /**
     * Generates a new ID for a {@link User}.
     */
    @SuppressWarnings("unused")
    @Override
    public String generateId() {
        return "U" + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }

    public String generateUserId(User user) {
        if (user.getRole() == UserRole.ADMIN) {
            return "A" + generateId().substring(1);
        } else if (user.getRole() == UserRole.DOCTOR) {
            return "D" + generateId().substring(1);
        } else if (user.getRole() == UserRole.PATIENT) {
            return "P" + generateId().substring(1);
        } else if (user.getRole() == UserRole.PHARMACIST) {
            return "F" + generateId().substring(1);
        }

        return generateId();
    }

    /**
     * Finds all the {@code Users} that matches the given name.
     * @param name the name to match against.
     * @return a list of {@link User} with the same name.
     */
    public List<User> findByName(String name) {
        return findBy(user -> user.getName().equals(name));
    }

    /**
     * Checks whether a {@link User} that matches a given name exists.
     * @param name the name to match against.
     * @return whether the user exists.
     */
    public boolean nameExists(String name) {
        return findByName(name).size() > 0;
    }

    /**
     * Saves a single {@link User} object. If the user does not have an ID, a new ID is generated 
     * and assigned to the user before saving it.
     * @param item the {@link User} object to be saved.
     * @return the saved {@link User} object, potentially with an auto-generated ID if one was not provided.
     */
    @Override
    public User save(User item) {
        if (item.getId() == null || item.getId().isBlank()) {
            item.setId(generateUserId(item));
        }

        return super.save(item);
    }

    /**
     * Saves a collection of {@link User} objects. For each user in the collection, if a user does 
     * not have an ID, a new ID is generated and assigned before saving the entire collection.
     * <p>
     * @param collection the list of {@link User} objects to be saved.
     * @return the list of saved {@link User} objects, with auto-generated IDs assigned to any new users.
     */
    @Override
    public List<User> save(List<User> collection) {
        collection.forEach(user -> {
            if (user.getId() == null) {
                user.setId(generateUserId(user));
            }
        });
        
        return super.save(collection);
    }
}

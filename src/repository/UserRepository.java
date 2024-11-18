package repository;

import java.util.*;

import model.enums.UserRole;
import model.users.User;
import repository.interfaces.IUserRepository;

/**
 * A repository implementation for managing {@link User} data models.
 * This class extends {@link BaseRepository} to provide additional functionality specific to 
 * {@link User} objects, including ID generation based on user roles and searching by name.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-29
 */
public class UserRepository extends BaseRepository<User> implements IUserRepository {
    /**
     * The filename of the data file used to persist {@link User} information.
     */
    final static String FILENAME = "users.dat";

     /**
     * The prefix used for generating generic {@link User} IDs.
     * Note: This is typically overridden by specific prefixes for user roles.
     */
    final static String ID_PREFIX = "U";
    
    /**
     * Constructs a new {@link UserRepository} instance.
     */
    public UserRepository() {
        super(FILENAME);
    }

    /**
     * Generates a new unique ID for a generic {@link User}.
     * 
     * @return the generated user ID in the format "U####", where #### is a 4-digit number.
     */
    @SuppressWarnings("unused")
	@Override
    public String generateId() {
        return "U" + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }

    /**
     * Generates a new unique ID for a {@link User} based on their {@link UserRole}.
     * 
     * @param user the {@link User} object for which the ID is being generated.
     * @return the generated ID prefixed with a letter corresponding to the user's role:
     *         <ul>
     *         <li>"A" for {@link UserRole#ADMIN}</li>
     *         <li>"D" for {@link UserRole#DOCTOR}</li>
     *         <li>"P" for {@link UserRole#PATIENT}</li>
     *         <li>"F" for {@link UserRole#PHARMACIST}</li>
     *         <li>"U" for other roles</li>
     *         </ul>
     */
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
     * Finds all {@link User} objects that match a given name.
     * 
     * @param name the name to match against.
     * @return a list of {@link User} objects whose names match the given name.
     */
    public List<User> findByName(String name) {
        return findBy(user -> user.getName().equals(name));
    }

    /**
     * Checks if any {@link User} with a given name exists in the repository.
     * 
     * @param name the name to check for.
     * @return {@code true} if a user with the given name exists, {@code false} otherwise.
     */
    public boolean nameExists(String name) {
        return findByName(name).size() > 0;
    }

    /**
     * Saves a single {@link User} to the repository.
     * If the user does not have an ID, a new ID is generated and assigned based on their role.
     * 
     * @param item the {@link User} to save.
     * @return the saved {@link User} object, potentially with an auto-generated ID.
     */
    @Override
    public User save(User item) {
        if (item.getId() == null || item.getId().isBlank()) {
            item.setId(generateUserId(item));
        }

        return super.save(item);
    }

    /**
     * Saves a collection of {@link User} objects to the repository.
     * For each user in the collection, if the user does not have an ID, a new ID is generated 
     * based on their role before saving.
     * 
     * @param collection the list of {@link User} objects to save.
     * @return the saved list of {@link User} objects, with IDs assigned where needed.
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

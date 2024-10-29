package repository;

import java.util.*;

import model.User;

/**
 * A {@link Repository} for {@link User} with specific functionality.
 */
public class UserRepository extends BaseRepository<User> {
    /**
     * The filename of the data file to load.
     */
    final static String FILENAME = "users.dat";

    /**
     *
     */
    public UserRepository() {
        super(FILENAME);
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
}

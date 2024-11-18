package repository.interfaces;

import java.util.List;

import model.users.User;

/**
 * The interface that defines the contract for a user repository.
 * This interface extends the {@link IRepository} interface.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IUserRepository extends IRepository<User> {
    /**
     * Finds all the {@code Users} that matches the given name.
     * @param name the name to match against.
     * @return a list of {@link User} with the same name.
     */
    public List<User> findByName(String name);

    /**
     * Checks whether a {@link User} that matches a given name exists.
     * @param name the name to match against.
     * @return whether the user exists.
     */
    public boolean nameExists(String name);
}
package controller;

import controller.interfaces.IUserManager;
import model.users.User;
import repository.interfaces.IUserRepository;

/**
 * Manages operations related to users.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class UserManager implements IUserManager {
    /**
     * Repository for accessing user data.
     */
    private final IUserRepository repository;

    /**
     * The currently active user.
     */
    private User activeUser;

    /**
     * Creates a new {@link UserManager}.
     * @param repository the repository for accessing user data.
     */
    public UserManager(IUserRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves the currently active user.
     * @return the currently active {@link User}.
     */
    public User getActiveUser() {
        return activeUser;
    }

    /**
     * Retrieves a user by their ID.
     * @param userId the ID of the user.
     * @return the {@link User} with the specified ID.
     */
    public User getUser(String userId) {
        return repository.findById(userId);
    }

    /**
     * Authenticates a user.
     * @param userId the ID of the user.
     * @param password the password of the user.
     * @return the authenticated {@link User}.
     */
    public User authenticate(String userId, String password) {
        User user = repository.findById(userId);

        // If the user ID does not exist, authentication fails.
        if (user == null) {
            return null;
        }

        // Attempt to authenticate the user.
        if (user.validatePassword(password)) {
            activeUser = user;
            return activeUser;
        }

        return null;
    }

    /**
     * Updates the password of the user.
     * @param user the user to update the password for.
     * @param password the new password.
     */
    public void updatePassword(User user, String password) {
        user.setPassword(password);
        
        // Sets active user to null to force re-authentication.
        activeUser = null;
        
        repository.save(user);
    }
}

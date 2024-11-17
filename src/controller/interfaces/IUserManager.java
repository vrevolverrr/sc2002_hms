package controller.interfaces;

import model.users.User;

/**
 * This interface provides methods to manage users in the system.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IUserManager {
    /**
     * Retrieves the active user.
     * 
     * @return the active {@link User}.
     */
    public User getActiveUser();

    /**
     * Retrieves a user by their ID.
     * 
     * @param userId the ID of the {@link User}.
     * @return the {@link User} with the specified ID.
     */
    public User getUser(String userId);

    /**
     * Authenticates a user.
     * 
     * @param userId the ID of the user.
     * @param password the password of the user.
     * @return the authenticated {@link User}.
     */
    public User authenticate(String userId, String password);
    
    /**
     * Updates the password of a user.
     * 
     * @param user the {@link User} to update the password for.
     * @param password the new password.
     */
    public void updatePassword(User user, String password);
}

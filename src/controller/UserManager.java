package controller;
import model.users.User;
import repository.UserRepository;

public class UserManager extends Manager<UserManager> {
    private final UserRepository repository = UserRepository.getInstance();

    private User activeUser;

    protected UserManager() {}

    public User getActiveUser() {
        return activeUser;
    }

    public User getUser(String userId) {
        return repository.findById(userId);
    }

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

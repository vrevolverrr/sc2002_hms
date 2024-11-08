package controller;
import model.User;
import repository.UserRepository;

public class UserManager extends Manager<UserManager> {
    private final UserRepository repository = new UserRepository();

    private User activeUser;

    protected UserManager() {}

    public User getActiveUser() {
        return activeUser;
    }

    public User authenticate(String userId, String password) {
        User user = repository.findById(userId);

        // If the user ID does not exist, authentication fails.
        if (user == null) {
            return null;
        }

        // Attempt to authenticate the user.
        if (user.getPassword().equals(password)) {
            activeUser = user;
            return activeUser;
        }

        return null;
    }
}

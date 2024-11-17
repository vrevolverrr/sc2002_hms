package controller.interfaces;

import model.users.User;

public interface IUserManager {
    public User getActiveUser();
    public User getUser(String userId);
    public User authenticate(String userId, String password);
    public void updatePassword(User user, String password);
}

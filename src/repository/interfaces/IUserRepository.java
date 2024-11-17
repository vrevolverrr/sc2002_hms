package repository.interfaces;

import java.util.List;

import model.users.User;

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
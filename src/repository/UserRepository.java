package repository;

import java.util.*;

import model.User;

public class UserRepository extends BaseRepository<User> {
    final static String FILENAME = "users.dat";

    public UserRepository() {
        super(FILENAME);
    }

    public List<User> findByName(String name) {
        return findBy(user -> user.getName().equals(name));
    }

    public boolean nameExists(String name) {
        return findByName(name).size() > 0;
    }
}

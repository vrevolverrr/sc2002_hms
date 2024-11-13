package repository;

import java.util.*;
import java.util.stream.*;
import java.util.function.Predicate;

import model.enums.UserRole;
import model.users.Pharmacist;
import model.users.User;

/**
 * An implementation of {@link Repository} that operates on {@link Pharmacist} data models. It extends
 * the functionality of a {@link UserRepository} by dependency injection to work on 
 * {@code Users} that are actually {@code Pharmacists}.
 */
public class PharmacistRepository implements Repository<Pharmacist> {    
    /**
     * The {@link UserRepository} dependency to extend.
     */
    private final UserRepository repository;

    /**
     * The prefix for the ID of a {@link Pharmacist}.
     */
    public final static String ID_PREFIX = "F";
    
    /**
     * The constructor of {@link PharmacistRepository}.
     * @param repository an instance of a {@link UserRepository}.
     */
    public PharmacistRepository(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets all the {@code Pharmacists} stored in the repository.
     * @return the entries of {@link Pharmacist} stored.
     */
    @Override
    public Map<String, Pharmacist> getItems() {
        return this.repository.getItems().entrySet().stream().filter(
            entry -> entry.getValue().getRole() == UserRole.PHARMACIST).collect(
                Collectors.toMap(e -> (String) e.getKey(), e -> (Pharmacist) e.getValue()));
    }

    /**
     * Gets the total number of {@code Pharmacist} stored in the repository.
     * @return the number of entries of {@link Pharmacist} stored.
     */
    @Override
    public int count() {
        return this.getItems().size();
    }

    /**
     * Generates a new ID for a {@link Pharmacist}. 
     */
    @SuppressWarnings("unused")
    @Override
    public String generateId() {
        return "F" + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }


    /**
     * Finds the {@link Pharmacist} matching the pharmacist ID.
     * @param pharmacistId the ID of the pharmacist.
     * @return the {@link Pharmacist} matching the ID, or null if no such pharmacist exists.
     */
    @Override
    public Pharmacist findById(String pharmacistId) {
        User user = repository.findById(pharmacistId);

        if (user.getRole() != UserRole.PHARMACIST) {
            return null;
        }

        return (Pharmacist) user;
    }

    /**
     * Finds all the {@link Pharmacist} that satisfy the given predicate (condition).
     * @param predicate the predicate to match against.
     * @return the list of {@link Pharmacist} matching the predicate.
     */
    @Override
    public List<Pharmacist> findBy(Predicate<Pharmacist> predicate) {
        return repository.findBy(
            // Filter out all pharmacists
            (User user) -> user.getRole() == UserRole.PHARMACIST).stream().map((User user) -> (Pharmacist) user)
            // Then filter by the predicate
            .filter(predicate).toList();
    }

    /**
     * Checks whether a {@link Pharmacist} with the given {@code pharmacistId} exists.
     * @param pharmacistId the ID of the pharmacist.
     * @return whether the {@link Pharmacist} matching the ID exists.
     */
    @Override
    public boolean exists(String pharmacistId) {
        return this.findById(pharmacistId) != null;
    }

    /**
     * Checks whether a {@link Pharmacist} satisfying a given {@code predicate} exists.
     * @param predicate the predicate (condition) to match against.
     * @return whether such {@link Pharmacist} exists.
     */
    @Override
    public boolean exists(Predicate<Pharmacist> predicate) {
        return this.findBy(predicate).size() > 0;
    }

    /**
     * Saves a {@link Pharmacist} to the underlying repository.
     * @param item the {@link Pharmacist} to be saved.
     * @return the same reference to the {@link Pharmacist}.
     */
    @Override
    public Pharmacist save(Pharmacist item) {
        if (item.getId() == null || item.getId().isBlank()) {
            item.setId(generateId());
        }

        repository.save(item);
        return item;
    }

    /**
     * Saves all the {@link Pharmacist} to the underlying repository.
     * @param collection the list of {@link Pharmacist} to be saved.
     * @return the same reference to the collection of {@link Pharmacist}.
     */
    @Override
    public List<Pharmacist> save(List<Pharmacist> collection) {
        // Casts each Pharmacist as a User then save to the underlying UserRepository.
        repository.save(collection.stream().map((Pharmacist pharmacist) -> (User) pharmacist).toList());
        return collection;
    }

    /**
     * Deletes the {@link Pharmacist} matching the given ID.
     * @param pharmacistId the ID of the pharmacist.
     * @return the {@link Pharmacist} that was deleted, or null if no such pharmacist exists.
     */
    @Override
    public Pharmacist deleteById(String pharmacistId) {
        if (exists(pharmacistId)) {
            return (Pharmacist) repository.deleteById(pharmacistId);
        }
        
        return null;
    }

    @Override
    public void clear() {
        repository.findBy(
            (User user) -> user.getRole() == UserRole.PHARMACIST).stream().forEach((User user) -> {
                repository.deleteById(user.getId());
            });
    }
}


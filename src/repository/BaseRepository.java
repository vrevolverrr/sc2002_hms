package repository;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.BaseModel;
import repository.interfaces.IRepository;

/**
 * Base repository class that provides basic CRUD operations for managing items in a repository.
 * This class is abstract and intended to be extended by concrete repository classes.
 *
 * @param <T> the type of the model that the repository holds, which extends {@link BaseModel}.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */

public abstract class BaseRepository<T extends BaseModel> implements IRepository<T> {
    /**
     * The base path for the data files, constructed using the current working directory.
     */
    private static final String BASE_PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator;

    /**
     * The internal store of the maps of each of the items.
     */
    private Map<String, T> items = null;

    /**
     * The name of the file where the repository's data is stored and loaded from.
     */
    private final String filename;

    /**
     * Constructs a {@link BaseRepository} with the specified filename for data persistence.
     *
     * @param filename the name of the file where the repository's data is stored and loaded from.
     */
    public BaseRepository(String filename) {
        this.filename = filename;
        initialize();
    }

    /**
     * Returns an unmodifiable map of all items in the repository.
     *
     * @return an unmodifiable map of items in the repository.
     */
    final public Map<String, T> getItems() {
        return Collections.unmodifiableMap(items);
    }

     /**
     * Initializes the repository by loading data from the serialized file and parsing the content.
     */
    public void initialize() {
        // To add additional logic/cleanup here
        readFromSerialized();
    }

    /**
     * Retrieves all items in the repository as a list of copies.
     *
     * @return a list of copies of all items in the repository.
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return items.values().stream()
                    .map(item -> (T) item.copy())
                    .collect(Collectors.toList());
    }

    /**
     * Finds an item by its ID.
     *
     * @param id the ID of the item to find.
     * @return a copy of the item with the given ID, or {@code null} if no item matches.
     */
    @SuppressWarnings("unchecked")
    // Type cast is always valid since implementations of copy() does a covariant return.
    final public T findById(String id) {
        T item = items.get(id);

        if (item == null) {
            return null;
        }

        return (T) item.copy();
    }

    /**
     * Finds all items that match a given predicate.
     *
     * @param predicate the condition to check each item.
     * @return a list of copies of the items that match the predicate.
     */
    @SuppressWarnings("unchecked") 
    // Type cast is always valid since implementations of copy() does a covariant return.
    final public List<T> findBy(Predicate<T> predicate) {
        return items.values().stream().filter(predicate).map((T item) -> (T) item.copy()).collect(Collectors.toList());
    }

    /**
     * Removes an item from the repository by its ID and persists the changes.
     *
     * @param id the ID of the item to remove.
     * @return the removed item, or {@code null} if no item matches the given ID.
     */
    final public T deleteById(String id) {
        T item = items.remove(id);
        writeToSerialized();

        return item;
    }

    /**
     * Saves an item to the repository. If the item does not have an ID, a new one is generated.
     * The item is persisted to the file.
     *
     * @param item the item to save.
     * @return the saved item, with its ID updated if necessary.
     */
    @SuppressWarnings("unchecked")
    // Type cast is always valid since implementations of copy() does a covariant return.
    public T save(T item) {
        if (item == null) return null;
        
        if (item.getId() == null || item.getId().isBlank()) {
            item.setId(generateId());
        }

        items.put(item.getId(), (T) item.copy());
        writeToSerialized();

        return item;
    }

    /**
     * Saves a collection of items to the repository. Items without IDs are assigned new IDs.
     * The items are persisted to the file.
     *
     * @param collection the collection of items to save.
     * @return the saved collection, with IDs updated if necessary.
     */
    public List<T> save(List<T> collection) {
        if (collection.size() <= 0) return null;

        for (T item : collection) {
            if (item.getId() == null || item.getId().isBlank()) {
                item.setId(generateId());
            }
            
            save(item);
        }

        writeToSerialized();
        return collection;
    }

     /**
     * Clears all items in the repository and persists the changes.
     */
    public void clear() {
        items.clear();
        writeToSerialized();
    }

    /**
     * Checks if an item with the given ID exists in the repository.
     *
     * @param id the ID of the item to check.
     * @return {@code true} if an item with the given ID exists, {@code false} otherwise.
     */
    public boolean exists(String id) {
        return findById(id) != null;
    }

    /**
     * Checks if an item matching the given predicate exists in the repository.
     * 
     * @param predicate the predicate to check each item.
     * @return {@code true} if at least one item matches the predicate, {@code false} otherwise.
     */
    public boolean exists(Predicate<T> predicate) {
        return findBy(predicate).size() > 0;
    }

    /**
     * Gets the total number of items in the repository.
     *
     * @return the number of items in the repository.
     */
    public int count() {
        return items.size();
    }

    /**
     * Reads the serialized data from the file into the repository's items.
     */
    @SuppressWarnings("unchecked")
    protected void readFromSerialized() {
        File file = new File(BASE_PATH + filename);

        if (!file.exists()) {
            items = new HashMap<String, T>();
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream(BASE_PATH + filename)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            
            items = (Map<String, T>) objectInputStream.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();

            items = new HashMap<String, T>();
            return;
        }
    }

    /**
     * Writes the serialized data from the repository's items to the file.
     * 
     * @return {@code true} if the write succeeded, {@code false} otherwise.
     */
    protected boolean writeToSerialized() {
        File file = new File(BASE_PATH + filename);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(items);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

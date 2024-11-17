/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-27
 */

package repository;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.BaseModel;
import repository.interfaces.IRepository;

/**
 * The base implementation of a {@link Repository} that provides most basic functionality.
 * This class can be extended to provide more specific functionality such as a {@link UserRepository}.
 */
public abstract class BaseRepository<T extends BaseModel> implements IRepository<T> {
    private static final String BASE_PATH = "./data/";

    private Map<String, T> items = null;
    private final String filename;

    public BaseRepository(String filename) {
        this.filename = filename;
        initialize();
    }

    /**
     * Returns an {@link Collections#unmodifiableMap} from the {@link BaseRepository#items}.
     */
    final public Map<String, T> getItems() {
        return Collections.unmodifiableMap(items);
    }

    /**
     * Initializes the data entires of the repository by calling {@link BaseRepository#readFromSerialized()}
     * and parsing the loaded data.
     */
    public void initialize() {
        // To add additional logic/cleanup here
        readFromSerialized();
    }

    /**
    * Returns a list of all items in the repository.
    * @return a list of copies of all items.
    */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return items.values().stream()
                    .map(item -> (T) item.copy())
                    .collect(Collectors.toList());
    }

    /**
     * Finds the item that matches the given ID.
     * @param id the ID of the item.
     * @return a copy of the item matching the ID, or null if such item does not exist.
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
	 * Finds all the items that matches a given predicate (condition).
	 * <pre>
	 * List<User> maleUsers = userRepository.findBy((User user) -> user.getGender() == Gender.MALE);
	 * </pre>
	 * @param predicate the condition to check whether an item should be included.
	 * @return a list of copies of the items satisfying the predicate.
	 */
    @SuppressWarnings("unchecked") 
    // Type cast is always valid since implementations of copy() does a covariant return.
    final public List<T> findBy(Predicate<T> predicate) {
        return items.values().stream().filter(predicate).map((T item) -> (T) item.copy()).collect(Collectors.toList());
    }

    /**
     * Removes the item that matches the given ID and persist the changes.
     * @param id the ID of the item.
     * @return the item that was removed.
     */
    final public T deleteById(String id) {
        T item = items.remove(id);
        writeToSerialized();

        return item;
    }

    /**
     * Updates the {@link BaseRepository#items} with a copy of the new item and 
     * persists the changes to the file.
     * @param item the item with changes to be saved.
     * @return the same reference to the item.
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
     * Updates the {@link BaseRepository#items} with a copy of each of the new items and 
     * persists the changes to the file.
     * @param collection the collection of items with changes to be saved.
     * @return the same reference to the collection.
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
     * Clears all the items in the {@link BaseRepository#items} and persists the changes.
     */
    public void clear() {
        items.clear();
        writeToSerialized();
    }

    /**
     * Checks whether an item matching the given ID exists in the repository.
     * @param id the ID of the item.
     * @return whether or not the item with such ID exists.
    */
    public boolean exists(String id) {
        return findById(id) != null;
    }

    /**
     * Checks whether an item matching the given predicate (condition) exists in the repository.
     * <pre>
     * boolean hasUserBob = userRepository.exists((User user) -> user.getName().equals("Bob"));
     * </pre>
     * 
     * @param predicate the predicate (condition) to check against.
     * @return whether or not the item matching the predicate exists.
     * 
    */
    public boolean exists(Predicate<T> predicate) {
        return findBy(predicate).size() > 0;
    }

    /**
     * Gets the total number of items in {@link BaseRepository#items}.
     * @return The number of items held by the repository.
     */
    public int count() {
        return items.size();
    }

    /**
     * Reads the serialised data from the provided file path into the {@link BaseRepository#items}.
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
     * Writes the serealised data from the {@link BaseRepository#items} to the provided file path.
     * @return whether the write succeeded
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

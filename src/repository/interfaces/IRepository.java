/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-29
 */

package repository.interfaces;

import java.util.*;
import java.util.function.*;

import model.BaseModel;

/**
 * The interface that defines the behavaiour of a data repository of a {@link BaseModel}.
 * Each implementation of repositories of models must implement this interface.
 */
public interface IRepository<T extends BaseModel> {
    /**
     * Gets the items that are being stored by the {@link IRepository} where each entry of items is
     * key-value pair, in which the key corresponds to the ID of the item, see {@link BaseModel#getId()}.
     * @return The items being held by the repository.
     */
    public Map<String, T> getItems();

    /**
     * Gets all the items stored by the {@link IRepository}.
     * @return a list of all the items.
     */
    public List<T> findAll();
    
    /**
     * Gets the total number of items stored by the {@link IRepository}.
     * @return The number of items held by the repository.
     */
    public int count();
    
    /**
     * Finds the item that matches the given ID.
     * @param id the ID of the item.
     * @return the item matching the ID.
     */
    public T findById(String id);

    /**
     * Finds all the items that matches a given predicate (condition).
     * @param predicate the condition to check whether an item should be included.
     * @return a list of the items satisfying the predicate.
     */
    public List<T> findBy(Predicate<T> predicate);
    
    /**
     * Checks whether an item matching the given ID exists in the repository.
     * @param id the ID of the item.
     * @return whether or not the item with such ID exists.
     */
    public boolean exists(String id);

    /**
     * Checks whether an item matching the given predicate (condition) exists in the repository.
     * @param predicate the predicate (condition) to check against.
     * @return whether or not the item matching the predicate exists.
     */
    public boolean exists(Predicate<T> predicate);

    /**
     * Saves the given item to the repository.
     * @param item the item with changes to be saved.
     * @return the same reference to the item.
     */
    public T save(T item);

    /**
     * Saves a collection of items to the repository.
     * @param collection
     * @return the same reference to the items.
     */
    public List<T> save(List<T> collection);
    
    /**
     * Removes the item that matches the given ID and persist the changes.
     * @param id the ID of the item.
     * @return the item that was removed.
     */
    public T deleteById(String id);

    /**
     * Removes all the items in the repository and persist the changes.
     */
    public void clear();

    /**
     * Generates a unique ID for the item to be stored in the repository.
     * @return the unique ID.
     */
    public String generateId();
}

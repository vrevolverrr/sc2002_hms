package repository;

import java.util.*;
import java.util.function.*;

public interface Repository<T> {
    public Map<String, T> getItems();
    
    public int count();
    
    public T findById(String id);
    public List<T> findBy(Predicate<T> predicate);
    
    public boolean exists(String id);
    public boolean exists(Predicate<T> predicate);

    public T save(T item);
    public List<T> save(List<T> collection);
    
    public T deleteById(String id);
}

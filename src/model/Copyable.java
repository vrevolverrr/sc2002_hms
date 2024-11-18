package model;

/**
 * An interface that allows for objects to be copied.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-10-26
 */
public interface Copyable {

    /**
     * Creates a copy of the object.
     * 
     * @return a copy of the object.
     */
    public Copyable copy();
}

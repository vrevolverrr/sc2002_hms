package model;

import java.io.Serializable;

/**
 * An interface that combines the {@link Serializable} and {@link Copyable} interfaces
 * to allow for objects to be both serialized and copied.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-10-26
 */
public interface SerializableCopyable extends Serializable, Copyable {}

package model;

import java.io.Serializable;

/**
 * An interface that combines the {@link Serializable} and {@link Copyable} interfaces
 * to allow for objects to be both serialized and copied.
 */
public interface SerializableCopyable extends Serializable, Copyable {}

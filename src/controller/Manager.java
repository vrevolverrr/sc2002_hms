package controller;

/**
 * 
 */
public abstract class Manager<T> {
    private static Manager<?> instance;

    protected Manager() {}

    public static <T extends Manager<T>> T getInstance(Class<T> clazz) {
        if (instance == null) {
            try {
                instance = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Could not create manager instance", e);
            }
        }
        return clazz.cast(instance);
    }
}
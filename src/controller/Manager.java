package controller;

/**
 * The base class to be inherited by all manager (controller) classes. The {@link Manager} class enforces
 * a singleton pattern on all of its subclasses. This is to prevent the manager classes from being
 * instantiated more than once.
 */
public abstract class Manager<T> {
    /**
     * The internal store of a {@link Manager} instance, if instantiated.
     */
    private static Manager<?> instance;

    protected Manager() {}

    /**
     * Gets an instance of a {@link Manager} subclass. Lazily instantiate an instance if called for the first time.
     * <pre>
     * UserManager userManager = UserManager.getIntance(UserManager.class);
     * </pre>
     * @param <T> the subtype of {@link Manager} whose instance is being retrieved.
     * @param clazz the actual {@code class} of the corresponding subtype of {@link Manager}
     * @return the instance of the {@link Manager} subclass.
     */
    public static <T extends Manager<T>> T getInstance(Class<T> clazz) {
        // If no instance has been instantiated before
        if (instance == null) {
            try {
                // Attempt to instantiate a new instance of the class needed.
                instance = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Could not create manager instance", e);
            }
        }
        
        // Cast the instance to the corresponding Manager subtype.
        return clazz.cast(instance);
    }
}
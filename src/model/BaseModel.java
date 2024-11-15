/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-26
 */

package model;

 /**
  * The base class for all data models with support for serialisation.
*/
public abstract class BaseModel implements SerializableCopyable {
    /**
     * The serializable class version number to verify whether the serialized object have loaded classes 
     * for that object that are compatible with respect to serialization. 
     * @see https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html.
     */
    static final long serialVersionUID = 42L;

    /**
     * The unique ID of the data object.
     */
    private String id;

    /**
     * The constructor of Model.
     * @param id
     */
    public BaseModel(String id) {
        this.id = id;
    }

    /**
     * Gets the unique ID of the object.
     * @return unique ID string
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the unique ID of the object.
     * @param id the unique ID string
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Creates and returns a copy of the {@link BaseModel} instance.
     * @return a copy of the instance
     */
    @Override
    public abstract BaseModel copy();
}

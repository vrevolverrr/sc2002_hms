package utils;

import lib.uilib.framework.TableRow;

/**
 * A utility class that represents a field in a table that can be updated.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public final class UpdatableField {
    /**
     * The record row in the table for this field.
     */
    private final TableRow record;

    /**
     * The update action to be performed when the field is updated.
     */
    private final Runnable update;

    /**
     * Constructs a new {@link UpdatableField} with the given record and update action.
     * @param record the record row in the table for this field.
     * @param update the update action to be performed when the field is updated.
     */
    public UpdatableField(TableRow record, Runnable update) {
        this.record = record;
        this.update = update;
    }

    /**
     * Gets the record row in the table for this field.
     * @return the record row in the table for this field.
     */
    public TableRow getRecord() {
        return record;
    }

    /**
     * Updates the field.
     * @return the update action to be performed when the field is updated.
     * @see Runnable
     */
    public void update() {
        update.run();
    }
}
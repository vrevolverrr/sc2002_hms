package utils;

import lib.uilib.framework.TableRow;

public final class UpdatableField {
    private final TableRow record;
    private final Runnable update;

    public UpdatableField(TableRow record, Runnable update) {
        this.record = record;
        this.update = update;
    }

    public TableRow getRecord() {
        return record;
    }

    public void update() {
        update.run();
    }
}
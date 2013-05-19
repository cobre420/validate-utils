package org.gv.validation;

/**
 * Validate item types enumeration. Each type has priority. Most important is ERROR, the WARNING and less
 * important is INFO.
 */
public enum ItemType implements Comparable<ItemType> {

    /**
     * Error type - priority 1.
     */
    ERROR(1),

    /**
     * Warning type - priority 2.
     */
    WARNING(2),

    /**
     * Info type - priority 3.
     */
    INFO(3);

    private final Integer priority;

    private ItemType(int priority) {
        this.priority = priority;
    }

    /**
     * Compares with another ItemType by priority.
     *
     * @param obj
     *         ItemType
     */
    public int comareTo(ItemType obj) {
        return this.priority.compareTo(obj.priority);
    }
}

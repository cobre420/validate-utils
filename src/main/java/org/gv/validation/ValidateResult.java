package org.gv.validation;

public interface ValidateResult {
    /**
     * Adds error item.
     *
     * @param caption
     *         Item caption
     * @return new {@link ValidateResultItem}
     */
    ValidateResultItem addError(String caption);

    /**
     * Adds error item with detailed description.
     *
     * @param caption
     *         Item caption
     * @param description
     *         Item description
     * @return new {@link ValidateResultItem}
     */
    ValidateResultItem addError(String caption, String description);

    /**
     * Adds warning item.
     *
     * @param caption
     *         Item caption
     * @return new {@link ValidateResultItem}
     */
    ValidateResultItem addWarning(String caption);

    /**
     * Adds warning item with detailed description.
     *
     * @param caption
     *         Item caption
     * @param description
     *         Item description
     * @return new {@link ValidateResultItem}
     */
    ValidateResultItem addWarning(String caption, String description);

    /**
     * Adds info item.
     *
     * @param caption
     *         Item caption
     * @return new {@link ValidateResultItem}
     */
    ValidateResultItem addInfo(String caption);

    /**
     * Adds info item with detailed description.
     *
     * @param caption
     *         Item caption
     * @param description
     *         Item description
     * @return new {@link ValidateResultItem}
     */
    ValidateResultItem addInfo(String caption, String description);

}

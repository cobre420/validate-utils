package org.gv.validation;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "validateResult")
public final class ValidateResultImpl implements ValidateResult {

    @XmlElement(name = "itemList")
    private List<ValidateResultItem> items = Collections.synchronizedList(new LinkedList<ValidateResultItem>());

    /**
     * Implicit constructor for serialization support.
     */
    public ValidateResultImpl() {
    }

    /**
     * Returns <code>false</code>, if contains at least one item of error type.
     */
    public boolean isValid() {
        boolean result = true;

        for (ValidateResultItem vri : items) {
            result = vri.isValid();
            if (!result) {
                break;
            }
        }

        return result;
    }

    private ValidateResultItem addItem(ValidateResultItem item) {
        items.add(item);
        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidateResultItem addError(String caption) {
        return addItem(ValidateResultItem.createError(caption, ""));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidateResultItem addError(String caption, String description) {
        return addItem(ValidateResultItem.createError(caption, description));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidateResultItem addWarning(String caption) {
        return addItem(ValidateResultItem.createWarning(caption, ""));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidateResultItem addWarning(String caption, String description) {
        return addItem(ValidateResultItem.createWarning(caption, description));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidateResultItem addInfo(String caption) {
        return addItem(ValidateResultItem.createInfo(caption, ""));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidateResultItem addInfo(String caption, String description) {
        return addItem(ValidateResultItem.createInfo(caption, description));
    }

    public void clear() {
        items.clear();
    }

    /**
     * {@inheritDoc}
     */
    public int itemCount() {
        return items.size();
    }

    /**
     * Vraci priznak zda obsahuje nejake polozky
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public boolean merge(ValidateResultImpl vr) {
        return this.items.addAll(vr.items);
    }

    /**
     * {@inheritDoc}
     */
    public boolean merge(List<ValidateResultItem> vr) {
        return this.items.addAll(vr);
    }

    /**
     * Zapise polozky do logru
     *
     * @param logger
     *         Logovac do kteryho vypropaguje svuj obsah
     */
    public void writeToLogger(Logger logger) {
        for (ValidateResultItem item : items) {
            if (item.getItemType() == ItemType.ERROR) {
                logger.error(item.toStringMessage());
            } else if (item.getItemType() == ItemType.WARNING) {
                logger.warn(item.toStringMessage());
            } else if (item.getItemType() == ItemType.INFO) {
                logger.info(item.toStringMessage());
            } else {
                logger.info(item.toStringMessage());
            }
        }
    }

//    /**
//     * Zapise polozky do logru
//     *
//     * @param logger
//     *         Logovac do kteryho vypropaguje svuj obsah
//     */
//    public void writeToLogger(Log logger) {
//        for (ValidateResultItem item : clonedItems()) {
//            if (item.getItemType() == ItemType.ERROR) {
//                if (logger.isErrorEnabled()) {
//                    logger.error(item.toStringMessage());
//                }
//            } else if (item.getItemType() == ItemType.WARNING) {
//                if (logger.isWarnEnabled()) {
//                    logger.warn(item.toStringMessage());
//                }
//            } else if (item.getItemType() == ItemType.INFO) {
//                if (logger.isInfoEnabled()) {
//                    logger.info(item.toStringMessage());
//                }
//            } else {
//                if (logger.isInfoEnabled()) {
//                    logger.info(item.toStringMessage());
//                }
//            }
//        }
//    }

    /**
     * {@inheritDoc}
     */
    public List<ValidateResultItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Vrati obsah validateItems jako formatovany retezec v poradi v jakem byly polozky pridavany
     */
    public String toStringMessage() {
        StringBuilder result = new StringBuilder();

        for (ValidateResultItem vri : items) {
            appendDelimited(result, vri.toStringMessage(), "\n");
        }

        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String toStringErrorMessage() {
        StringBuilder result = new StringBuilder();

        for (ValidateResultItem vri : items) {
            if (!vri.isValid()) {
                appendDelimited(result, vri.toStringMessage(), "\n");
            }
        }

        return result.toString();
    }

    private static void appendDelimited(StringBuilder stringBuilder, String value, String delimiter) {
        if (!StringUtils.isBlank(value)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(delimiter).append(value);
            } else {
                stringBuilder.append(value);
            }
        }
    }

//    /**
//     * {@inheritDoc}
//     */
//    public void sort() {
//        Collections.sort(items, new ValidateResultItemCoparator());
//    }

    public final int errorCount() {
        int result = 0;
        for (ValidateResultItem i : items) {
            if (i.getItemType() == ItemType.ERROR) {
                result++;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public final int warningCount() {
        int result = 0;
        for (ValidateResultItem i : items) {
            if (i.getItemType() == ItemType.WARNING) {
                result++;
            }
        }
        return result;
    }

    /**
     * @see ValidateResultImpl#toStringMessage()
     */
    @Override
    public String toString() {
        return toStringMessage();
    }
}

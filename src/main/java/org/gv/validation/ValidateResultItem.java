package org.gv.validation;

import org.apache.commons.lang.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@XmlType(name = "validateResultItem")
public final class ValidateResultItem implements ValidateResult {
    
    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "caption")
    private String caption;

    @XmlElement(name = "children")
    private List<ValidateResultItem> childs = Collections.synchronizedList(new LinkedList<ValidateResultItem>());

    @XmlElement(name = "itemType")
    private ItemType type = ItemType.INFO;

    private Integer itemId;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    private final Long since = System.currentTimeMillis(); //cas(systemovy v milisekundach), kdy item vznikl

    public ValidateResultItem() {/*XML serializace*/}

    private ValidateResultItem(ItemType type, String caption, String description) {
        super();
        this.caption = caption;
        this.description = description;
        this.type = type;
    }

	private List<ValidateResultItem> clonedChilds() {
		return new ArrayList<ValidateResultItem>(childs);
	}

    public static ValidateResultItem createError(String caption, String description) {
        return new ValidateResultItem(ItemType.ERROR, caption, description);
    }

    public static ValidateResultItem createWarning(String caption, String description) {
        return new ValidateResultItem(ItemType.WARNING, caption, description);
    }

    public static ValidateResultItem createInfo(String caption, String description) {
        return new ValidateResultItem(ItemType.INFO, caption, description);
    }

    /**
     * Adds error item.
     */
    @Override
    public ValidateResultItem addError(String caption) {
        return addItem(ValidateResultItem.createError(caption, ""));
    }

    /**
     * Adds error with detail message.
     */
    @Override
    public ValidateResultItem addError(String caption, String description) {
        return addItem(ValidateResultItem.createError(caption, description));
    }

    /**
     * Adds warning item.
     */
    @Override
    public ValidateResultItem addWarning(String caption) {
        return addItem(ValidateResultItem.createWarning(caption, ""));
    }

    /**
     * Adds warning item with detail message.
     */
    @Override
    public ValidateResultItem addWarning(String caption, String description) {
        return addItem(ValidateResultItem.createWarning(caption, description));
    }

    /**
     * Adds info item.
     */
    @Override
    public ValidateResultItem addInfo(String caption) {
        return addItem(ValidateResultItem.createInfo(caption, ""));
    }

    /**
     * Adds info item with detail message.
     */
    @Override
    public ValidateResultItem addInfo(String caption, String description) {
        return addItem(ValidateResultItem.createInfo(caption, description));
    }

    /**
     * Removes all child items.
     */
    public void removeChilds() {
        childs.clear();
    }

    private ValidateResultItem addItem(ValidateResultItem item) {
        childs.add(item);
        return item;
    }

    /**
     * Adds all items from given validate result.
     */
    public boolean mergeChilds(ValidateResultImpl vr) {
        return childs.addAll(vr.getItems());
    }

    /**
     * Returns all child items.
     */
    public List<ValidateResultItem> getChilds() {
        return Collections.unmodifiableList(childs);
    }

    /**
     * Adds all items from given collection.
     */
    public boolean mergeChilds(Collection<ValidateResultItem> items) {
        return childs.addAll(items);
    }

    /**
     * Returns child items count.
     */
    public int childCount() {
        return childs.size();
    }

    /**
     * Returns detailed description for this item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns item caption.
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Returns timestamp of origin of this item.
     */
    public Long getSince() {
        return since;
    }

    /**
     * Returns <code>true</code>, if this item is valid, <code>false</code> otherwise.
     */
    public boolean isValid() {
        return !isError();
    }

    protected final boolean isError() {
        return (ItemType.ERROR == type || errorChilds());
    }

    /**
     * Return <code>true</code> if this item is WARN type or if contains at least one WARN child item.
     */
    protected final boolean isWarn() {
        return (ItemType.WARNING == type || warnChilds());
    }

    private boolean warnChilds() {
        boolean result = false;
        for (ValidateResultItem child : clonedChilds()) {
            result = child.warnChilds();
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean errorChilds() {
        boolean result = false;
        for (ValidateResultItem child : clonedChilds()) {
            result = !child.isValid();
            if (result) {
                break;
            }
        }
        return result;
    }

    /**
     * Returns unmodifiable collection of child items.
     */
    Collection<ValidateResultItem> childs() {
        return Collections.unmodifiableList(childs);
    }

    private String getLevelDescription(int level) {
        String result = renderLevelSeparator(level, "- ") + caption;
        if (!StringUtils.isBlank(description)) {
            result += (": " + description);
        }

        if (childs.size() > 0) {
            for (ValidateResultItem child : clonedChilds()) {
                result += ("\n" + child.getLevelDescription(level + 1));
            }
        }

        return result;
    }

    private String renderLevelSeparator(int level, String suffix) {
        String result = "";
        for (int i = 0; i <= level; i++) {
            result += "\t";
        }
        return result + suffix;
    }

    /**
     * Return string representation of item in format: <code>caption\ndescription</code>
     */
    public String toStringMessage() {
        String result = caption;
        if (!StringUtils.isBlank(description)) {
            result += (": " + description);
        }

        if (childs.size() > 0) {
            for (ValidateResultItem child : clonedChilds()) {
                result += ("\n" + child.getLevelDescription(0));
            }
        }
        return result;
    }

    /**
     * Returns item type. The type is calclated from child items.
     * @see #isValid()
     * @see #isWarn()
     */
    public final ItemType getItemType() {
        final ItemType result;

        if (ItemType.ERROR == type || !isValid()) {
            result = ItemType.ERROR;
        } else if (ItemType.WARNING == type || isWarn()) {
            result = ItemType.WARNING;
        } else {
            result = type;
        }

        return result;
    }
}

package org.gv.validation;

import java.util.Comparator;


/**
 * Sorts the validate result items by priority (error - warning - info). If the priority equals, then it sorts them
 * by the timestamps (creation time of the item).
 */
class ValidateResultItemCoparator implements Comparator<ValidateResultItem> {

    @Override
    public int compare(ValidateResultItem o1, ValidateResultItem o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        } else if (o1.getItemType() == o2.getItemType()) {
            return o1.getSince().compareTo(o2.getSince());
        } else {
            return o1.getItemType().comareTo(o2.getItemType());
        }
    }

}

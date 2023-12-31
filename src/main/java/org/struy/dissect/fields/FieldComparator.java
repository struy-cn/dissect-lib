package org.struy.dissect.fields;

import java.io.Serializable;
import java.util.Comparator;

public final class FieldComparator implements Comparator<Field>, Serializable {
    @Override
    public int compare(final Field o1, final Field o2) {
        return Integer.compare(o1.ordinal(), o2.ordinal());
    }
}

package org.struy.dissect.fields;


import org.struy.dissect.Delimiter;
import org.struy.dissect.ValueResolver;

import java.util.Map;

public final class NormalField extends AbstractField {
    public NormalField(final String name, final String suffix, final int ordinal) {
        super(name, suffix, ordinal);
    }

    private NormalField(final int id, final String name, final String suffix, final Delimiter previous, final Delimiter next) {
        super(id, name, suffix, NORMAL_ORDINAL_LOWER, previous, next);
    }

    public static Field create(final int id, final String name, final String suffix, final Delimiter previous, final Delimiter next) {
        return new NormalField(id, name, suffix, previous, next);
    }

    @Override
    public boolean saveable() {
        return true;
    }

    @Override
    public void append(final Map<String, Object> keyValueMap, final ValueResolver values) {
        keyValueMap.put(this.name(), values.get(this.id()));
    }


    @Override
    public String toString() {
        return buildToString(this.getClass().getName());
    }
}

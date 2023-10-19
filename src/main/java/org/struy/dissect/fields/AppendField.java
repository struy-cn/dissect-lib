package org.struy.dissect.fields;

import org.struy.dissect.Delimiter;
import org.struy.dissect.ValueResolver;

import java.util.Map;
import java.util.regex.Matcher;

public final class AppendField extends AbstractField {

    private AppendField(final int id, final String name, final String suffix, final int ord, final Delimiter previous, final Delimiter next) {
        super(id, name, suffix, ord, previous, next);
    }

    public static Field create(final int id, final String name, final String suffix, final Delimiter previous, final Delimiter next) {
        final Matcher m = ORDINAL_REGEX.matcher(suffix);
        if (m.find()) {
            return new AppendField(id, name, suffix, APPEND_ORDINAL_BASE + Integer.parseInt(m.group()), previous, next);
        } else {
            return new AppendField(id, name, suffix, APPEND_ORDINAL_BASE, previous, next);
        }
    }

    @Override
    public boolean saveable() {
        return true;
    }

    @Override
    public void append(final Map<String, Object> keyValueMap, final ValueResolver values) {
        if (keyValueMap.containsKey(this.name())) {
            final Object old = keyValueMap.get(this.name());
            keyValueMap.put(this.name(), old.toString() + joinString() + values.get(this.id()));
        } else {
            keyValueMap.put(this.name(), values.get(this.id()));
        }
    }

    @Override
    public String toString() {
        return buildToString(this.getClass().getName());
    }
}

package org.struy.dissect.fields;


import org.struy.dissect.Delimiter;
import org.struy.dissect.ValueResolver;

import java.util.Map;

public final class IndirectField extends AbstractField {

    private IndirectField(final int id, final String name, final String suffix, final Delimiter previous, final Delimiter next) {
        super(id, name, suffix, INDIRECT_ORDINAL_HIGHER, previous, next);
    }

    public static Field create(final int id, final String name, final String suffix, final Delimiter previous, final Delimiter next) {
        return new IndirectField(id, name, suffix, previous, next);
    }

    @Override
    public boolean saveable() {
        return true;
    }

    @Override
    public void append(final Map<String, Object> keyValueMap, final ValueResolver values) {
        final String indirectName = anyValue(name(), keyValueMap, values);
        if (!indirectName.isEmpty()) {
            keyValueMap.put(indirectName, values.get(this.id()));
        }
    }

    private String anyValue(final String key, final Map<String, Object> map, final ValueResolver values) {
        if (map.containsKey(key)) {
            final Object val = map.get(key);
            return String.valueOf(val);
        }
        return values.getOtherByName(key, this.id());
    }

    @Override
    public String toString() {
        return buildToString(this.getClass().getName());
    }
}

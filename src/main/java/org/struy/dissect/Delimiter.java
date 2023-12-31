package org.struy.dissect;

import org.struy.dissect.search.*;

public final class Delimiter {
    private final byte[] needle;
    private final String delimiter;
    private final DelimiterLocator strategy;
    private boolean greedy;

    private Delimiter(final String delimiter, final byte[] bytes, final DelimiterLocator strategy) {
        this.delimiter = delimiter;
        this.needle = bytes;
        this.strategy = strategy;
        greedy = false;
    }

    public static Delimiter create(final String delim) {
        final byte[] bytes = delim.getBytes();
        switch (bytes.length) {
            case 0:
                return new Delimiter(delim, bytes, ZeroByteLocator.INSTANCE);
            case 1:
                return new Delimiter(delim, bytes, SingleByteLocator.INSTANCE);
            case 2:
                return new Delimiter(delim, bytes, DoubleByteLocator.INSTANCE);
            default:
                return new Delimiter(delim, bytes, MultiByteLocator.INSTANCE);
        }
    }

    public int indexOf(final byte[] haystack, final int offset) {
        return strategy.indexOf(this.needle, haystack, offset);
    }

    public int size() {
        return this.needle.length;
    }

    public String getDelimiter() {
        return this.delimiter;
    }


    @Override
    public String toString() {
        return "Delimiter{" + "delimiter='" + delimiter +
                "', size=" + size() +
                '}';
    }

    public boolean isGreedy() {
        return greedy;
    }

    public void setGreedy(final boolean greedy) {
        this.greedy = greedy;
    }
}

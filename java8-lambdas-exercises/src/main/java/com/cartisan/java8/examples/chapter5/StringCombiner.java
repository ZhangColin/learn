package com.cartisan.java8.examples.chapter5;

public class StringCombiner {
    private final String delim;
    private final String prefix;
    private final String suffix;
    private final StringBuilder builder;

    public StringCombiner(String delim, String prefix, String suffix) {
        this.delim = delim;
        this.prefix = prefix;
        this.suffix = suffix;
        builder = new StringBuilder();
    }

    public StringCombiner add(String word) {
        if (!this.areAtStart()) {
            builder.append(delim);
        }
        builder.append(word);

        return this;
    }

    public StringCombiner merge(StringCombiner other) {
        if (!other.equals(this)) {
            if (!other.areAtStart() && !this.areAtStart()) {
                other.builder.insert(0, delim);
            }

            this.builder.append(other.builder);
        }

        return this;
    }

    @Override
    public String toString() {
        return prefix+builder.toString()+suffix;
    }

    private boolean areAtStart() {
        return builder.length()==0;
    }
}

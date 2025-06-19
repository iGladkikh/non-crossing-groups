package com.igladkikh.model;

public class Fragment {
    private final String value;

    public Fragment(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fragment fragment = (Fragment) o;
        return value.equals(fragment.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "Fragment{" +
                "value='" + value + '\'' +
                '}';
    }
}

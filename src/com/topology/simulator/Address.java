package com.topology.simulator;

public class Address {
    private int identifier;
    private Address(int identifier) {
        this.identifier = identifier;
    }

    public final int getIdentifier() {
        return identifier;
    }

    public static Address create(int identifier) {
        return new Address(identifier);
    }

    @Override
    public int hashCode() {
        return identifier;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Address)) {
            return false;
        }
        return identifier == ((Address) object).identifier;
    }
}

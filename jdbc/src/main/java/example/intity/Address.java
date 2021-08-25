package example.intity;

import java.util.Objects;

public class Address {
    private Integer index;
    private String name;

    public Address(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

    public Address() {
    }

    public Integer getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return index.equals(address.index) &&
                name.equals(address.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, name);
    }

    @Override
    public String toString() {
        return "Address{" +
                "index=" + index +
                ", name='" + name + '\'' +
                '}';
    }
}

package hibernate_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "countries")
public class Country extends Address {
    @Id
    @Column
    int index;

    @Column
    String name;

    public Country() {
    }

    public Country(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return getIndex() == country.getIndex() &&
                getName().equals(country.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), getName());
    }

    @Override
    public String toString() {
        return "Country{" +
                "index=" + index +
                ", name='" + name + '\'' +
                '}';
    }
}

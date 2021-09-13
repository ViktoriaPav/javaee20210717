package hibernate_project.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="cities")
public class City extends Address{
    @Id
    @Column
    private int index;

    @Column
    String name;


    public City() {
    }

    public City(int index, String name) {
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
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getIndex() == city.getIndex() &&
                getName().equals(city.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), getName());
    }

    @Override
    public String toString() {
        return "City{" +
                "index=" + index +
                ", name='" + name + '\'' +
                '}';
    }
}

package main.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "degree")
    private Set<Lector> lectorSet;

    private String degreeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Lector> getLectorSet() {
        return lectorSet;
    }

    public void setLectorSet(Set<Lector> lectorSet) {
        this.lectorSet = lectorSet;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Degree)) return false;

        Degree degree = (Degree) o;

        if (lectorSet != null ? !lectorSet.equals(degree.lectorSet) : degree.lectorSet != null) return false;
        return degreeName != null ? degreeName.equals(degree.degreeName) : degree.degreeName == null;
    }

    @Override
    public int hashCode() {
        int result = lectorSet != null ? lectorSet.hashCode() : 0;
        result = 31 * result + (degreeName != null ? degreeName.hashCode() : 0);
        return result;
    }
}

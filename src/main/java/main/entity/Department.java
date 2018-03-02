package main.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String departmentName;

    @OneToOne(fetch = FetchType.LAZY , mappedBy = "headOfDepartments")
    private Lector lector;

    @ManyToMany
    @JoinTable(name = "Lector_Department", joinColumns =
    @JoinColumn(name = "fk_Department"), inverseJoinColumns =
    @JoinColumn(name = "fk_Lector"))
    private Set<Lector> lectorSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Set<Lector> getLectorSet() {
        return lectorSet;
    }

    public void setLectorSet(Set<Lector> lectorSet) {
        this.lectorSet = lectorSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        if (departmentName != null ? !departmentName.equals(that.departmentName) : that.departmentName != null)
            return false;
        if (lector != null ? !lector.equals(that.lector) : that.lector != null) return false;
        return lectorSet != null ? lectorSet.equals(that.lectorSet) : that.lectorSet == null;
    }

    @Override
    public int hashCode() {
        int result = departmentName != null ? departmentName.hashCode() : 0;
        result = 31 * result + (lector != null ? lector.hashCode() : 0);
        result = 31 * result + (lectorSet != null ? lectorSet.hashCode() : 0);
        return result;
    }
}

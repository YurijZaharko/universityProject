package main.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(name = "Lector_Department", joinColumns =
    @JoinColumn(name = "fk_Lector"), inverseJoinColumns =
    @JoinColumn(name = "fk_Department"))
    private Set<Department> workInDepartments;

    @OneToOne(fetch = FetchType.LAZY)
    private Department headOfDepartments;

    @ManyToOne(fetch = FetchType.LAZY)
    private Degree degree;

    private String nameSurname;

    private int salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Department> getWorkInDepartments() {
        return workInDepartments;
    }

    public void setWorkInDepartments(Set<Department> workInDepartments) {
        this.workInDepartments = workInDepartments;
    }

    public Department getHeadOfDepartments() {
        return headOfDepartments;
    }

    public void setHeadOfDepartments(Department headOfDepartments) {
        this.headOfDepartments = headOfDepartments;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameAndSurname) {
        this.nameSurname = nameAndSurname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lector)) return false;

        Lector lector = (Lector) o;

        if (salary != lector.salary) return false;
        return nameSurname != null ? nameSurname.equals(lector.nameSurname) : lector.nameSurname == null;
    }

    @Override
    public int hashCode() {
        int result = nameSurname != null ? nameSurname.hashCode() : 0;
        result = 31 * result + salary;
        return result;
    }
}

package main.repository;

import main.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Integer> {

    @Query("SELECT AVG(lector.salary) FROM Lector lector LEFT JOIN lector.workInDepartments department WHERE department.id = :id")
    Double findAverageSalaryInDepartment(@Param("id") int id);

    @Query("SELECT COUNT(lectors) FROM Lector lectors LEFT JOIN lectors.workInDepartments dep WHERE dep.id = :id")
    Integer countLectorByDepartmentId(@Param("id") int id);


    List<Lector> findByNameSurnameStartingWith(String partOfName);

    List<Lector> findByNameSurnameContaining(String partOfName);

    List<Lector> findByNameSurnameEndingWith(String partOfName);
}

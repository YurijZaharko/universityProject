package main.repository;

import main.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("SELECT department FROM Department department LEFT JOIN FETCH department.lector WHERE department.departmentName= :departmentName")
    Department findByDepartmentNameFetchLector(@Param("departmentName") String departmentName);

    Department findByDepartmentName(String answer);

}

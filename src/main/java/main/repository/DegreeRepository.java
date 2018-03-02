package main.repository;

import main.entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DegreeRepository extends JpaRepository<Degree, Integer> {

    @Query("SELECT DISTINCT COUNT (lectors) FROM Lector lectors LEFT JOIN lectors.degree degree LEFT JOIN lectors.workInDepartments depart WHERE depart.id = :departmentId " +
            "AND degree.id = :degreeId")
    Long countLectorsGroupByDegreeName(@Param("departmentId") Integer departmentId, @Param("degreeId") Integer degreeId);
}

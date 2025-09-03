package com.invex.employee.repository;

import com.invex.employee.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.id = :id")
    Optional<Employee> findById(Long id);

    @Modifying
    @Query("DELETE FROM Employee e WHERE e.id = :id")
    void deleteById(Long id);

    @Query("SELECT e FROM Employee e "
            + "WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) "
            + "OR LOWER(e.middleName) LIKE LOWER(CONCAT('%', :name, '%')) "
            + "OR LOWER(CONCAT(e.firstName, ' ', e.middleName)) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employee> findByName(String name);

}

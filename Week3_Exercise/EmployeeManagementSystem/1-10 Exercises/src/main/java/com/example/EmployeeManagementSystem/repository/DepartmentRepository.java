//package com.example.EmployeeManagementSystem.repository;
//
//import com.example.EmployeeManagementSystem.model.Department;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface DepartmentRepository extends JpaRepository<Department, Long> {
//
//    // Derived query method to find a department by name
//    Department findByName(String name);
//}
package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.model.Department;
import com.example.EmployeeManagementSystem.projection.DepartmentDTO;
import com.example.EmployeeManagementSystem.projection.DepartmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Derived query method to find departments by name
    List<Department> findByName(String name);

    // Custom JPQL query to find departments with a specific name
    @Query("SELECT d FROM Department d WHERE d.name = :name")
    List<Department> findDepartmentsByName(@Param("name") String name);

////Interface-based projection method
//    List<DepartmentProjection> findByNameProjection(String name);
    // Class-based projection with constructor expression
    @Query("SELECT new com.example.EmployeeManagementSystem.projection.DepartmentDTO(d.id, d.name) " +
            "FROM Department d")
    List<DepartmentDTO> findAllDepartmentDTOs();
}


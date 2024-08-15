//package com.example.EmployeeManagementSystem.repository;
//
//import com.example.EmployeeManagementSystem.model.Employee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.List;
//
//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//
//    // Derived query method to find employees by department name
//    List<Employee> findByDepartmentName(String departmentName);
//}
package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.projection.EmployeeDTO;
import com.example.EmployeeManagementSystem.projection.EmployeeProjection;
import com.example.EmployeeManagementSystem.projection.EmployeeValueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Pagination and sorting with method name
    Page<Employee> findByEmail(String email, Pageable pageable);

    // Custom JPQL query with pagination and sorting
    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
    Page<Employee> findEmployeesByDepartmentId(@Param("departmentId") Long departmentId, Pageable pageable);

    // Custom native SQL query with pagination and sorting
    @Query(value = "SELECT * FROM employees e JOIN departments d ON e.department_id = d.id WHERE d.name = :departmentName", nativeQuery = true)
    Page<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName, Pageable pageable);

    // Interface-based projection with a unique method name
    List<EmployeeProjection> findProjectionsByDepartmentName(String departmentName);

    // Class-based projection with constructor expression and unique method name
    @Query("SELECT new com.example.EmployeeManagementSystem.projection.EmployeeDTO(e.id, e.name, e.email) " +
            "FROM Employee e WHERE e.department.name = :departmentName")
    List<EmployeeDTO> findDTOsByDepartmentName(@Param("departmentName") String departmentName);

    // Projection with @Value and unique method name
    List<EmployeeValueProjection> findValueProjectionsByDepartmentName(String departmentName);
}





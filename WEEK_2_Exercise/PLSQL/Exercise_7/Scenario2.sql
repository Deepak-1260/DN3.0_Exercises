CREATE OR REPLACE PACKAGE EmployeeManagement AS
  PROCEDURE HireEmployee(
    employee_id IN NUMBER,
    name IN VARCHAR2,
    position IN VARCHAR2,
    salary IN NUMBER,
    department IN VARCHAR2,
    hire_date IN DATE
  );

  PROCEDURE UpdateEmployeeDetails(
    employee_id IN NUMBER,
    name IN VARCHAR2,
    position IN VARCHAR2,
    salary IN NUMBER,
    department IN VARCHAR2
  );

  FUNCTION CalculateAnnualSalary(
    employee_id IN NUMBER
  ) RETURN NUMBER;
END EmployeeManagement;
/
CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

  PROCEDURE HireEmployee(
    employee_id IN NUMBER,
    name IN VARCHAR2,
    position IN VARCHAR2,
    salary IN NUMBER,
    department IN VARCHAR2,
    hire_date IN DATE
  ) IS
  BEGIN
    INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
    VALUES (employee_id, name, position, salary, department, hire_date);

    DBMS_OUTPUT.PUT_LINE('Employee hired successfully: ' || employee_id);
  END HireEmployee;

  PROCEDURE UpdateEmployeeDetails(
    employee_id IN NUMBER,
    name IN VARCHAR2,
    position IN VARCHAR2,
    salary IN NUMBER,
    department IN VARCHAR2
  ) IS
  BEGIN
    UPDATE Employees
    SET Name = name,
        Position = position,
        Salary = salary,
        Department = department
    WHERE EmployeeID = employee_id;

    DBMS_OUTPUT.PUT_LINE('Employee details updated successfully: ' || employee_id);
  END UpdateEmployeeDetails;

  FUNCTION CalculateAnnualSalary(
    employee_id IN NUMBER
  ) RETURN NUMBER IS
    emp_salary NUMBER;
  BEGIN
    SELECT Salary
    INTO emp_salary
    FROM Employees
    WHERE EmployeeID = employee_id;

    RETURN emp_salary * 12;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20001, 'Error calculating annual salary.');
  END CalculateAnnualSalary;

END EmployeeManagement;
/
BEGIN
  -- Test HireEmployee
  EmployeeManagement.HireEmployee(
    employee_id => 3, 
    name => 'Sarah Connor', 
    position => 'Analyst', 
    salary => 5000, 
    department => 'Finance', 
    hire_date => TO_DATE('2023-01-15', 'YYYY-MM-DD')
  );

  -- Test UpdateEmployeeDetails
  EmployeeManagement.UpdateEmployeeDetails(
    employee_id => 1, 
    name => 'Alice Johnson', 
    position => 'Senior Manager', 
    salary => 80000, 
    department => 'HR'
  );

  -- Test CalculateAnnualSalary
  DECLARE
    annual_salary NUMBER;
  BEGIN
    annual_salary := EmployeeManagement.CalculateAnnualSalary(1);
    DBMS_OUTPUT.PUT_LINE('Annual salary: ' || annual_salary);
  END;
END;
/

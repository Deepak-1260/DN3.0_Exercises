CREATE or replace PROCEDURE updateSalary(
empid Employees.EmployeeID%TYPE,
percentage Number
)
IS
 InvalidEmp EXCEPTION;
 empCount Number;
 updateSal Number;
 
BEGIN
  select count(*) into empCount from Employees where Employees.EmployeeID=empid;
  IF(empCount<1) THEN
     Raise InvalidEmp;
  END IF;
  
  update Employees
  set Salary = Salary+Salary*(0.01*percentage)
  where Employees.EmployeeID=empid;
  
  select Salary into updateSal from Employees where Employees.EmployeeID=empid;
  DBMS_OUTPUT.PUT_LINE('Successfully Updated Salary to '||updateSal);
  
  EXCEPTION
     when InvalidEmp THEN
        DBMS_OUTPUT.PUT_LINE('Employee not exsist');
     
END;
/
  

  BEGIN
    updateSalary(empid=>5,percentage=>5);
  END;
  /
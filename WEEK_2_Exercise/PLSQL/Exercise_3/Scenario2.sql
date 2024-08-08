
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    bonus IN INT,
    dept IN VARCHAR2
) 
AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * (bonus / 100))
    WHERE Department = dept;

    COMMIT;
END;
/

BEGIN
    UpdateEmployeeBonus(10, 'IT');
END;
/
DECLARE
    CURSOR emp_cursor IS
        SELECT EmployeeID, Name, Position, Salary, Department, HireDate
        FROM Employees
        WHERE Department = 'IT'; -- Replace 'IT' with the department you want to check

    emp_rec emp_cursor%ROWTYPE;
BEGIN
    -- Call the procedure
    UpdateEmployeeBonus(10, 'IT');

    -- Open the cursor
    OPEN emp_cursor;
    
    -- Loop through the cursor and display the updated data
    LOOP
        FETCH emp_cursor INTO emp_rec;
        EXIT WHEN emp_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('EmployeeID: ' || emp_rec.EmployeeID || 
                             ', Name: ' || emp_rec.Name || 
                             ', Position: ' || emp_rec.Position || 
                             ', Salary: ' || emp_rec.Salary || 
                             ', Department: ' || emp_rec.Department || 
                             ', HireDate: ' || emp_rec.HireDate);
    END LOOP;
    
    -- Close the cursor
    CLOSE emp_cursor;
END;
/
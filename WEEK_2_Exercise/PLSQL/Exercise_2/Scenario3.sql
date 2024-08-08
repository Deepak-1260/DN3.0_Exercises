CREATE OR REPLACE PROCEDURE AddNewCustomer(
    newCustomerID NUMBER,
    newName VARCHAR2,
    newDOB DATE,
    newBalance NUMBER,
    newLastModified DATE
)
IS
  ExistCust EXCEPTION;
  CusCount NUMBER;
BEGIN
  -- Check if the customer already exists
  SELECT COUNT(*) INTO CusCount FROM Customers WHERE CustomerID = newCustomerID;
  
  IF CusCount > 0 THEN
     RAISE ExistCust;
  END IF;
  
  -- Insert the new customer
  INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
  VALUES (newCustomerID, newName, newDOB, newBalance, newLastModified);
  
  DBMS_OUTPUT.PUT_LINE('Added a new User');
  
EXCEPTION
  WHEN ExistCust THEN
    DBMS_OUTPUT.PUT_LINE('Customer Already Exists');
END;
/


BEGIN
  AddNewCustomer(
    newCustomerID =>60,
    newName => 'John Doe',
    newDOB => TO_DATE('1947-05-15', 'YYYY-MM-DD'),
    newBalance => 1000,
    newLastModified => SYSDATE
  );
END;
/
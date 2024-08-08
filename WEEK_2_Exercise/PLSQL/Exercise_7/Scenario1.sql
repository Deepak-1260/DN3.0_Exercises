CREATE OR REPLACE PACKAGE CustomerManagement AS
  PROCEDURE AddCustomer(
    customer_id IN NUMBER,
    name IN VARCHAR2,
    dob IN DATE,
    balance IN NUMBER
  );

  PROCEDURE UpdateCustomerDetails(
    customer_id IN NUMBER,
    name IN VARCHAR2,
    dob IN DATE
  );

  FUNCTION GetCustomerBalance(
    customer_id IN NUMBER
  ) RETURN NUMBER;
END CustomerManagement;
/
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

  PROCEDURE AddCustomer(
    customer_id IN NUMBER,
    name IN VARCHAR2,
    dob IN DATE,
    balance IN NUMBER
  ) IS
  BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (customer_id, name, dob, balance, SYSDATE);

    DBMS_OUTPUT.PUT_LINE('Customer added successfully: ' || customer_id);
  END AddCustomer;

  PROCEDURE UpdateCustomerDetails(
    customer_id IN NUMBER,
    name IN VARCHAR2,
    dob IN DATE
  ) IS
  BEGIN
    UPDATE Customers
    SET Name = name,
        DOB = dob,
        LastModified = SYSDATE
    WHERE CustomerID = customer_id;

    DBMS_OUTPUT.PUT_LINE('Customer details updated successfully: ' || customer_id);
  END UpdateCustomerDetails;

  FUNCTION GetCustomerBalance(
    customer_id IN NUMBER
  ) RETURN NUMBER IS
    balance NUMBER;
  BEGIN
    SELECT Balance
    INTO balance
    FROM Customers
    WHERE CustomerID = customer_id;

    RETURN balance;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20001, 'Error retrieving customer balance.');
  END GetCustomerBalance;

END CustomerManagement;
/
BEGIN
  CustomerManagement.AddCustomer(3, 'Michael Johnson', TO_DATE('1978-11-25', 'YYYY-MM-DD'), 2000);
  CustomerManagement.UpdateCustomerDetails(1, 'John Doe Jr.', TO_DATE('1985-05-15', 'YYYY-MM-DD'));
  DECLARE
    customer_balance NUMBER;
  BEGIN
    customer_balance := CustomerManagement.GetCustomerBalance(1);
    DBMS_OUTPUT.PUT_LINE('Customer balance: ' || customer_balance);
  END;
END;
/

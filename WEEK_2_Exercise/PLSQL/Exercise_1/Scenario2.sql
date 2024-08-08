
 ALTER TABLE Customers ADD IsVIP BOOLEAN;
DECLARE
  CURSOR customer_cursor IS
    SELECT CustomerID
    FROM Customers
    WHERE Balance > 10000;
  
  customer_ID Customers.CustomerID%TYPE;
BEGIN
  OPEN customer_cursor;
  
  LOOP
    FETCH customer_cursor INTO customer_ID;
    EXIT WHEN customer_cursor%NOTFOUND;
    
    UPDATE Customers
    SET IsVIP = TRUE
    WHERE CustomerID = customer_ID;
  END LOOP;
  
  CLOSE customer_cursor;
END;
/

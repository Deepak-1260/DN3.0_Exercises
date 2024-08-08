
DECLARE
    CURSOR customer_cursor IS
        SELECT CustomerID, Name, DOB, Balance, LastModified
        FROM Customers;

    customer_rec customer_cursor%ROWTYPE;
BEGIN
    OPEN customer_cursor;
    
    LOOP
        FETCH customer_cursor INTO customer_rec;
        EXIT WHEN customer_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('CustomerID: ' || customer_rec.CustomerID || 
                             ', Name: ' || customer_rec.Name || 
                             ', DOB: ' || customer_rec.DOB || 
                             ', Balance: ' || customer_rec.Balance || 
                             ', LastModified: ' || customer_rec.LastModified);
    END LOOP;
    
    CLOSE customer_cursor;
END;
/

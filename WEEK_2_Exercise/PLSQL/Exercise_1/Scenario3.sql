BEGIN
  FOR rec IN (
    Select c.CustomerID As custID,l.EndDate,c.Name from Customers c join Loans l on l.CustomerID=c.CustomerID
  ) LOOP
    if rec.EndDate BETWEEN SYSDATE and SYSDATE+30 THEN
        DBMS_OUTPUT.PUT_LINE('Have to Pay Loan in next 30 dyas '||rec.custID||' '||rec.Name);
    END IF;
   
  END LOOP;
END;
/
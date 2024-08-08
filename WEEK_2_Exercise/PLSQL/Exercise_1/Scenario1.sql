DECLARE
  v_customer_age NUMBER;
  v_new_interest_rate Loans.InterestRate%type;
BEGIN
  FOR r_customer_loan IN (
    SELECT c.CustomerID AS Customer_ID, c.DOB, l.LoanID, l.InterestRate
    FROM Customers c
    JOIN Loans l ON c.CustomerID = l.CustomerID
  ) LOOP
    v_customer_age := FLOOR(MONTHS_BETWEEN(SYSDATE, r_customer_loan.DOB) / 12);
    IF v_customer_age > 60 THEN
      UPDATE Loans
      SET InterestRate = InterestRate - 1
      WHERE LoanID = r_customer_loan.LoanID;

      SELECT InterestRate INTO v_new_interest_rate
      FROM Loans l
      WHERE l.CustomerID = r_customer_loan.Customer_ID;

      DBMS_OUTPUT.PUT_LINE('Applied 1% discount to Customer ID: ' || r_customer_loan.Customer_ID || ', New Interest Rate: ' || (r_customer_loan.InterestRate) || ' NEW Interest ' || v_new_interest_rate);
    END IF;
  END LOOP;
END;
/
DECLARE
  -- Define a record type to hold the transaction details
  TYPE TransactionRecType IS RECORD (
    CustomerID Customers.CustomerID%TYPE,
    CustomerName Customers.Name%TYPE,
    AccountID Accounts.AccountID%TYPE,
    TransactionDate Transactions.TransactionDate%TYPE,
    Amount Transactions.Amount%TYPE,
    TransactionType Transactions.TransactionType%TYPE
  );
  CURSOR GenerateMonthlyStatements IS
    SELECT c.CustomerID, c.Name, a.AccountID, t.TransactionDate, t.Amount, t.TransactionType
    FROM Customers c
    JOIN Accounts a ON c.CustomerID = a.CustomerID
    JOIN Transactions t ON a.AccountID = t.AccountID
    WHERE EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE)
      AND EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
    ORDER BY c.CustomerID, t.TransactionDate;
  TransactionRec TransactionRecType;

BEGIN
  OPEN GenerateMonthlyStatements;
  LOOP
    FETCH GenerateMonthlyStatements INTO TransactionRec;
    EXIT WHEN GenerateMonthlyStatements%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('Customer ID: ' || TransactionRec.CustomerID || ', Name: ' || TransactionRec.CustomerName);
    DBMS_OUTPUT.PUT_LINE('Account ID: ' || TransactionRec.AccountID);
    DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || TO_CHAR(TransactionRec.TransactionDate, 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('Amount: ' || TransactionRec.Amount || ', Type: ' || TransactionRec.TransactionType);
    DBMS_OUTPUT.PUT_LINE('----------------------------------------');
  END LOOP;
  CLOSE GenerateMonthlyStatements;
END;
/
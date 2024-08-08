CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
  CURSOR savings_accounts_cursor IS
    SELECT AccountID, CustomerID, Balance
    FROM Accounts
    WHERE AccountType = 'Savings'
      AND (SYSDATE - LastModified) >= 30
      FOR UPDATE;
  
  current_account_id Accounts.AccountID%TYPE;
  current_customer_id Accounts.CustomerID%TYPE;
  current_balance Accounts.Balance%TYPE;
BEGIN
  OPEN savings_accounts_cursor;
  
  LOOP
    FETCH savings_accounts_cursor INTO current_account_id, current_customer_id, current_balance;
    EXIT WHEN savings_accounts_cursor%NOTFOUND;
    
    UPDATE Accounts
    SET Balance = current_balance * 1.01,
        LastModified = SYSDATE
    WHERE AccountID = current_account_id;
    
    DBMS_OUTPUT.PUT_LINE('AccountID: ' || current_account_id || 
                         ', CustomerID: ' || current_customer_id || 
                         ', New Balance: ' || current_balance * 1.01);
  END LOOP;
  
  CLOSE savings_accounts_cursor;
  
  COMMIT;
  
  DBMS_OUTPUT.PUT_LINE('Monthly interest of 1% applied to all eligible savings accounts.');
END;
/
BEGIN
  ProcessMonthlyInterest;
END;
/

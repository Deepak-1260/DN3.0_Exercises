CREATE OR REPLACE PACKAGE AccountOperations AS
  PROCEDURE OpenAccount(
    account_id IN NUMBER,
    customer_id IN NUMBER,
    account_type IN VARCHAR2,
    initial_balance IN NUMBER
  );

  PROCEDURE CloseAccount(
    account_id IN NUMBER
  );

  FUNCTION GetTotalBalance(
    customer_id IN NUMBER
  ) RETURN NUMBER;
END AccountOperations;
/
CREATE OR REPLACE PACKAGE BODY AccountOperations AS

  PROCEDURE OpenAccount(
    account_id IN NUMBER,
    customer_id IN NUMBER,
    account_type IN VARCHAR2,
    initial_balance IN NUMBER
  ) IS
  BEGIN
    INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
    VALUES (account_id, customer_id, account_type, initial_balance, SYSDATE);

    DBMS_OUTPUT.PUT_LINE('Account opened successfully: ' || account_id);
  END OpenAccount;

  PROCEDURE CloseAccount(
    account_id IN NUMBER
  ) IS
  BEGIN
    DELETE FROM Transactions
    WHERE AccountID = account_id;

    DELETE FROM Accounts
    WHERE AccountID = account_id;

    DBMS_OUTPUT.PUT_LINE('Account closed successfully: ' || account_id);
  END CloseAccount;

  FUNCTION GetTotalBalance(
    customer_id IN NUMBER
  ) RETURN NUMBER IS
    total_balance NUMBER;
  BEGIN
    SELECT SUM(Balance)
    INTO total_balance
    FROM Accounts
    WHERE CustomerID = customer_id;

    RETURN total_balance;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN 0;
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20001, 'Error calculating total balance.');
  END GetTotalBalance;

END AccountOperations;
/
BEGIN
  -- Test OpenAccount
  AccountOperations.OpenAccount(
    account_id => 3, 
    customer_id => 1, 
    account_type => 'Checking', 
    initial_balance => 2000
  );

  -- Test CloseAccount
  AccountOperations.CloseAccount(account_id => 2);

  -- Test GetTotalBalance
  DECLARE
    total_balance NUMBER;
  BEGIN
    total_balance := AccountOperations.GetTotalBalance(1);
    DBMS_OUTPUT.PUT_LINE('Total balance for customer 1: ' || total_balance);
  END;
END;
/

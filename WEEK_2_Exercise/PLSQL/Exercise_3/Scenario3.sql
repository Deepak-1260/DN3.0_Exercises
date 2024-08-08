CREATE OR REPLACE PROCEDURE TransferFunds(
    source_account_id IN Accounts.AccountID%TYPE,
    target_account_id IN Accounts.AccountID%TYPE,
    transfer_amount IN NUMBER
) IS
    source_balance Accounts.Balance%TYPE;
BEGIN
    SELECT Balance
    INTO source_balance
    FROM Accounts
    WHERE AccountID = source_account_id
    FOR UPDATE;

    IF source_balance < transfer_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance in source account.');
    END IF;

    UPDATE Accounts
    SET Balance = Balance - transfer_amount
    WHERE AccountID = source_account_id;

    UPDATE Accounts
    SET Balance = Balance + transfer_amount
    WHERE AccountID = target_account_id;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Funds transferred successfully: ' ||
                         'From AccountID ' || source_account_id || 
                         ' To AccountID ' || target_account_id ||
                         ' Amount ' || transfer_amount);
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('One or both account IDs do not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
BEGIN
  TransferFunds(source_account_id => 1, 
                target_account_id => 2, 
                transfer_amount => 500);
END;
/

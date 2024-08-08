CREATE OR REPLACE FUNCTION HasSufficientBalance (
  AccountID NUMBER,
  Balance NUMBER
) RETURN BOOLEAN
IS
  accBal NUMBER;
BEGIN
 
  SELECT Balance INTO accBal 
  FROM Accounts 
  WHERE AccountID = AccountID
  AND ROWNUM = 1;

  IF accBal >= Balance THEN
    RETURN TRUE;
  ELSE
    RETURN FALSE;
  END IF;

END HasSufficientBalance;
/

BEGIN
   if HasSufficientBalance(AccountID=> 1, Balance=>100)  THEN
    DBMS_OUTPUT.PUT_LINE('Funds are sufficent to Transfer');
   else
     DBMS_OUTPUT.PUT_LINE('Funds are Insufficent');
   END IF;
   
END;
/
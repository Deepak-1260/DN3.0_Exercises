CREATE or REPLACE PROCEDURE SafeTransferFunds(
 account1 Accounts.AccountID%TYPE,
 account2 Accounts.AccountID%TYPE,
 funds NUMBER
 )
 IS
 InsufficientFunds EXCEPTION;
 accountBalance NUMBER;
 BEGIN
   SELECT Balance INTO accountBalance FROM Accounts WHERE AccountID = account1;

   
   IF(accountBalance <funds) THEN
      RAISE  InsufficientFunds;
   END IF;
   DBMS_OUTPUT.PUT_LINE('Funds are Transfered Succesfully :)');
   EXCEPTION
     when InsufficientFunds THEN
        DBMS_OUTPUT.PUT_LINE('Funds are Insufficent in the Account');
    
  END;
  /
  
  BEGIN
    SafeTransferFunds(account1 =>1,account2 =>2,funds=>1000);
  END;
  /
DECLARE 
  
  CURSOR ApplyAnnualFee is select AccountID, Balance from Accounts;
  
  accId Accounts.AccountID%type;
  currBal Accounts.Balance%type;
  
BEGIN
  
  open ApplyAnnualFee;
  
  LOOP
      FETCH ApplyAnnualFee into accId,currBal;
      
      EXIT when ApplyAnnualFee%NOTFOUND;
      
      UPDATE Accounts
      set Balance = currBal-250 
      where AccountID=accId;
      
      DBMS_OUTPUT.PUT_LINE('ACCOUNT ID: '||accId ||' || Balance: '||(currBal-250));
      
  END LOOP;
  
  CLOSE ApplyAnnualFee;

END;
/
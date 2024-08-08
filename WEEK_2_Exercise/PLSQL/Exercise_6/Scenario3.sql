DECLARE
  
  CURSOR UpdateLoanInterestRates is select LoanID, InterestRate from Loans;
  
  LID Loans.LoanID%type;
  IR  Loans.InterestRate%type;
  
BEGIN
  
  open UpdateLoanInterestRates;
  
  LOOP
      
      FETCH UpdateLoanInterestRates into LID, IR;
      
      EXIT when UpdateLoanInterestRates%NOTFOUND;
      
      Update Loans
      set InterestRate=IR-2
      where LoanID=LoanID;
      
      DBMS_OUTPUT.PUT_LINE('LoanID: '||LID||' AND new InterestRate: '||IR);
  
  END LOOP;
  
  CLOSE UpdateLoanInterestRates;

END;
/
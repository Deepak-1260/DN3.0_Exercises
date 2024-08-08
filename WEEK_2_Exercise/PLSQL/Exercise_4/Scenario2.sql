CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
  LoanAmount IN NUMBER,
  InterestRate IN NUMBER,
  LoanDuration IN NUMBER
)
RETURN NUMBER
IS 
  r NUMBER;  
  n NUMBER;  
  EMI NUMBER;  
BEGIN
  r := (InterestRate / 100) / 12;
  n := LoanDuration * 12;
  EMI := (LoanAmount * r * POWER(1 + r, n)) / (POWER(1 + r, n) - 1); 
  RETURN EMI;
END;
/

BEGIN
  DBMS_OUTPUT.PUT_LINE(CalculateMonthlyInstallment(LoanAmount=>100000, InterestRate=>2, LoanDuration=>2));
END;
/
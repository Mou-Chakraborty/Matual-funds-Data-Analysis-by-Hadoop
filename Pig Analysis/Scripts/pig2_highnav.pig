funds = LOAD '/mutual_funds/Mutual_Funds.csv'
USING PigStorage(',')
AS (
    Fund_House:chararray,
    Scheme_Type:chararray,
    Scheme_Category:chararray,
    Scheme_Code:int,
    Scheme_Name:chararray,
    Date:chararray,
    NAV:double
);

high_nav = FILTER funds BY NAV > 1000;

result = LIMIT high_nav 20;

DUMP result;
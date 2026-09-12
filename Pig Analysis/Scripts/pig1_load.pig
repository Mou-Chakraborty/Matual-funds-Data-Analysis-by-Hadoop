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
sample_data = LIMIT funds 10;

DUMP sample_data;
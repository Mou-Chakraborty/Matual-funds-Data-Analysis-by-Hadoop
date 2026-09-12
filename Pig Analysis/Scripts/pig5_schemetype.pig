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

grouped = GROUP funds BY Scheme_Type;

result = FOREACH grouped
GENERATE
    group AS Scheme_Type,
    COUNT(funds) AS Total_Records;

DUMP result;
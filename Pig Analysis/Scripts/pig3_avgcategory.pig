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

grouped = GROUP funds BY Scheme_Category;

result = FOREACH grouped
GENERATE
    group AS Scheme_Category,
    AVG(funds.NAV) AS Average_NAV;

DUMP result;
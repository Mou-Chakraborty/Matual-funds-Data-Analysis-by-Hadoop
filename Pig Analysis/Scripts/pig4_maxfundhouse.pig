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

grouped = GROUP funds BY Fund_House;

result = FOREACH grouped
GENERATE
    group AS Fund_House,
    MAX(funds.NAV) AS Maximum_NAV;

DUMP result;
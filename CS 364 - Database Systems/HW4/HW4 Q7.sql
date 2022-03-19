CREATE TABLE TopKCustomer_T (
    CustomerID NUMBER(11,0) NOT NULL,
    CustomerName VARCHAR2(25) NOT NULL,
    CustomerPostalCode VARCHAR2(10),
    CRank NUMBER,
    OrderFrequency NUMBER,
    RankGenerateDate DATE
);
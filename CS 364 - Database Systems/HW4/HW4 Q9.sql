CREATE TABLE AuditProduct_T
(   ProductID number(11),
    ProductLineId number(11),
    ProductDescription varchar2(50),
    ProductFinish varchar2(20),
    productStandardPrice number(6,2),
    ChangeDate date,
    ChangeStatus varchar2(10) Constraint Status_C
    CHECK (ChangeStatus IN ('Insert','Update','Delete'))
);
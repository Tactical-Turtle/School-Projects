CREATE OR REPLACE TRIGGER AduitProductTrig
AFTER INSERT --or UPDATE or DELETE  
ON PRODUCT_T
     FOR EACH ROW  
     
BEGIN
    CASE
        WHEN INSERTING THEN
        INSERT INTO AuditProduct_T 
        (
            ProductID
           ,ProductLineId
           ,ProductDescription
           ,ProductFinish
           ,productStandardPrice
           ,ChangeDate
           ,ChangeStatus
        )
        
        VALUES
        (
            :new.ProductID
           ,:new.ProductLineId
           ,:new.ProductDescription
           ,:new.ProductFinish
           ,:new.productStandardPrice
           ,SYSDATE
           ,'Insert'
        );
        
        WHEN UPDATING THEN
        INSERT INTO AuditProduct_T 
        (
            ProductID
           ,ProductLineId
           ,ProductDescription
           ,ProductFinish
           ,productStandardPrice
           ,ChangeDate
           ,ChangeStatus
        )
        
        VALUES
        (
            :new.ProductID
           ,:new.ProductLineId
           ,:new.ProductDescription
           ,:new.ProductFinish
           ,:new.productStandardPrice
           ,SYSDATE
           ,'Update'
        );
        
        WHEN DELETING THEN
        INSERT INTO AuditProduct_T 
        (
            ProductID
           ,ProductLineId
           ,ProductDescription
           ,ProductFinish
           ,productStandardPrice
           ,ChangeDate
           ,ChangeStatus
        )
        
        VALUES
        (
            :old.ProductID
           ,:old.ProductLineId
           ,:old.ProductDescription
           ,:old.ProductFinish
           ,:old.productStandardPrice
           ,SYSDATE
           ,'Delete'
        );   
        
    END CASE;

END;



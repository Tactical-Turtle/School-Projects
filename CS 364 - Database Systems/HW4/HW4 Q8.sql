CREATE OR REPLACE PROCEDURE topkcustomer(k IN number) AS
BEGIN

   INSERT INTO TopKCustomer_T
   (
      customerid,
      customername,
      customerpostalcode,
      orderfrequency,
      crank,
      rankgeneratedate
   )
   WITH
   full_set AS
   (
      SELECT
         c.customerid,
         c.customername,
         c.customerpostalcode,
         COUNT(*) AS OrderFrequency,
         DENSE_RANK() OVER
         (
            ORDER BY COUNT(*) DESC
         ) 
         rank_id,
         SYSDATE

      FROM
         CUSTOMER_T C
         JOIN ORDER_T O ON O.CUSTOMERID = C.CUSTOMERID
      GROUP BY
         C.customerid,
         C.customername,
         C.customerpostalcode
   )
   SELECT
      customerid,
      customername,
      customerpostalcode,
      OrderFrequency,
      rank_id,
      SYSDATE
   FROM full_set
   WHERE rank_id <= k;
END;

DELETE FROM TopKCustomer_T;
EXECUTE TopKCustomer(3);

--SELECT CRANK, ORDERFREQUENCY, CUSTOMERID,
--CUSTOMERNAME, CUSTOMERPOSTALCODE, RANKGENERATEDATE
--FROM TopKCustomer_T;
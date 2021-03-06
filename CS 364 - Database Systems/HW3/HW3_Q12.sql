SELECT DISTINCT O.CUSTOMERID, C.CUSTOMERNAME 
    FROM ORDER_T O JOIN
       ORDERLINE_T   OL ON O.ORDERID=OL.ORDERID       JOIN
         PRODUCT_T     P  ON OL.PRODUCTID = P.PRODUCTID JOIN
         PRODUCTLINE_T PL ON P.PRODUCTLINEID = PL.PRODUCTLINEID JOIN
         CUSTOMER_T    C  ON O.CUSTOMERID = C.CUSTOMERID
    WHERE O.ORDERDATE BETWEEN '01-MAR-15' AND '31-MAR-15' AND (PL.PRODUCTLINENAME = 'Basic')
 ORDER BY O.CUSTOMERID

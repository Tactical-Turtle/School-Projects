SELECT O.CUSTOMERID, C.CUSTOMERNAME, C.CUSTOMERPOSTALCODE,
       COUNT(O.CUSTOMERID) AS OrderFrequency
FROM ORDER_T O JOIN 
     CUSTOMER_T C ON C.CUSTOMERID = O.CUSTOMERID
WHERE O.CUSTOMERID = C.CUSTOMERID
GROUP BY O.CUSTOMERID, C.CUSTOMERNAME, C.CUSTOMERPOSTALCODE
ORDER BY OrderFrequency DESC;
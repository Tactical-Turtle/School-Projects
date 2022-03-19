CREATE VIEW EmployeeIN 
AS
SELECT employeeId, employeeName, EmployeeCity, EmployeeState
FROM EMPLOYEE_T
WHERE EmployeeState = 'IN'
WITH CHECK OPTION;
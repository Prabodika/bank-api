#Bank-api

Instructions

1. Get the project
2. Install Java 8 and Maven 3.0
3. Build the project (mvn clean package)
4  Go to the target folder and run bank-application-1.0.0.jar(java -jar bank-application-1.0.0.jar)
5. Go to the browser and navigate to http://localhost:8080/h2-console
6. login to the h2 database (user=sa password=password)
7. Go to src->main->resource folder
8. Get the schema.sql and run those commands in h2 database console(to create tables)
9. Then run the data.sql related commands
10. Now you are ready to test the application

Requests for sample bank account

1.To get the account balance use following get route
http://localhost:8080/v1/bank-api/account/1234/balance

2.To get the account balance use following get route
http://localhost:8080/v1/bank-api/account/1234/statement

3.To transfer money use following post route
http://localhost:8080/v1/bank-api/account/transaction
Request Body
{
   "accountNumber":"1234",
   "amount": 100.00,
   "description":"money transfer",
   "beneficiaryAccountName":"tom",
   "beneficiaryAccountNumber":"4321"


}

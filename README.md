# abcrewards
Calculate Reward points for ABC.com

Build Tool: Maven

It is implemented in Spring Boot application

It uses H2 database and inserts few transactions for 2 customer ids (A1234 and A2345) during the startup.

H2 DB Console URL: http://localhost:8081/abcdotcom/h2-console/ (JDBC URL: jdbc:h2:mem:bootapp, User Name: sa, Password: (empty))

http://localhost:8081/abcdotcom/api/rewards/get will give all the custoomer's reward points in JSON as below. It gives Monthly Reward points as well as Total.
Sample output:
[{"customerId":"A2345","rewardPoints":{"Total":783,"Feb-2022":0,"Mar-2022":715,"Jan-2022":68}},{"customerId":"A1234","rewardPoints":{"Total":343,"Feb-2022":41,"Mar-2022":294,"Jan-2022":8}}]

http://localhost:8081/abcdotcom/api/rewards/get/A2345 will give reward points for any specific customer id

http://localhost:8081/abcdotcom/api/transactions/add can be used to add any transaction and payload is as below.
        {
        "customerId": "A3456",
        "transactionDate": "2022-03-13",
        "transactionAmount": 145.78
        }
http://localhost:8081/abcdotcom/api/transactions/get will give all the transactions from DB

http://localhost:8081/abcdotcom/api/transactions/get/A1234 will all the transactions for the given customerid from DB

Run Instructions:

====================

Import the project as Maven Project in Eclipse/STS.

Build the project using mvn install.

Run the application as Sprint Boot.

Access the URLs mentioned above to add new transactions, view transactions (all/by customer id) and get reward points ( all/by customer id).

Sample transactions are being inserted into h2 db eachtime the application startsup.

HTTP status code 400 will be sent if the given customer id is not in db.

HTTP status code 500 will be sent if there are any exceptions in the code.

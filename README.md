
# Customer Statement Processor

This project consists of REST API which validates the customer statements recieved in a particular bank. The requirements are specified [here](https://github.com/rashmishrivastava/customer-statement-processor/blob/main/AssignmentSpecifications.md)

# Run the Project

Once you have configured this project in your IDE you can build it from there. However if you prefer you can use maven from the command line. In that case you could be interested in this short list of commands:

* `mvn compile`: it will just compile the code of your application and tell you if there are errors
* `mvn test`: it will compile the code of your application and your tests. It will then run your tests (if you wrote any) and let you know if some fails
* `mvn install`: it will do everything `mvn test` does and then if everything looks file it will install the library or the application into your local maven repository (typically under <USER FOLDER>/.m2). In this way you could use this library from other projects you want to build on the same machine

Once you have setup the project and ran the application in your IDE , you can use POSTMAN Tool to verify the REST API

# URL of REST API

* `http://localhost:8080/customer-statement-processor/v1`

# Sample Input request

[
  {
    "reference": 442216661,
    "accountNumber": "NL26RB1267626726",
    "startBalance": 123.43,
    "mutation": 3.43,
    "decription": "Paid to Grocery",
    "endBalance": 126.86
  },
  {
    "reference": 442216662,
    "accountNumber": "NL26RB1267626726",
    "startBalance": 123.43,
    "mutation": 3.43,
    "decription": "Paid to Bol.com",
    "endBalance": 126.86
  },
  {
    "reference": 442216663,
    "accountNumber": "NL26RB1267626726",
    "startBalance": 123.43,
    "mutation": 3.43,
    "decription": "Paid to Amazon",
    "endBalance": 126.86
  }
]

Below is the screenshot of testing the REST API in POSTMAN Tool:

<img width="1440" alt="Screenshot 2021-01-19 at 23 54 33" src="https://user-images.githubusercontent.com/61087653/105104131-fd480980-5ab1-11eb-8616-2f01c8cdc541.png">

# Response of above sample request 

{
    "result": "SUCCESSFUL",
    "errorRecords": []
}

You can verify all the scenerio's as per requirement specification.

# Assumption

* One Transaction Reference will be linked to only one account number even in the error record.
* Duplicate records are checked only with transaction reference and not account number.




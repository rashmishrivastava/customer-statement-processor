
# Customer Statement Processor

This project consists of REST API which validates the customer statements recieved in particular bank.

# Run the Project

Once you have configured this project in your IDE you can build it from there. However if you prefer you can use maven from the command line. In that case you could be interested in this short list of commands:

* `mvn compile`: it will just compile the code of your application and tell you if there are errors
* `mvn test`: it will compile the code of your application and your tests. It will then run your tests (if you wrote any) and let you know if some fails
* `mvn install`: it will do everything `mvn test` does and then if everything looks file it will install the library or the application into your local maven repository (typically under <USER FOLDER>/.m2). In this way you could use this library from other projects you want to build on the same machine

Once you have setup the project and ran the application in your IDE , you can use POSTMAN Tool to verify the REST API

# URL of REST API

* `http://localhost:8080/customer-statement-processor/v1`



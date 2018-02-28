# AWS Lambda Function other Hello World Java example

This folder contains an AWS Lambda Function example in Java on AWS (Amazon Web Services).

It handles a simple AWS Lambda function that shows the content of the call to the lambda function and returns a message including this content,
using classes for Request and Response.




## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

This code was written for Java 1.8 and AWS SDK for Java 1.11.x.




## Using the code

* Configure your AWS access keys.

* Create an AWS lambda function:
  * Name: SOME_NAME
  * Runtime: Java 8
  * Role: lambda-basic-execution
  * The triggers: Nothing
  * The resources the function's role has access: Amazon CloudWatch Logs

  Handler function:

  ```
  example.Hello::handleRequest
  ```

* Upload the Java JAR file.

  Artifact: 

  ```
  awslambdahello2.jar
  ```

* Create and configure a Test event:

  Input JSON file content:

  ```
  {

    "firstName": "Peter",

    "lastName": "Parker"

  }
  ```

* Test the AWS Lambda function:

  Run the code in a AWS lambda function using the test button.

  You should see the next messages in the log:

  ```
  "First Name: Peter"

  "Last Name: Parker"

  "Hello Peter Parker."
  ```

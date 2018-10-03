# AWS Lambda Function other Hello World Java example

This folder contains an AWS Lambda Function example in Java on AWS (Amazon Web Services).

It handles a simple AWS Lambda function that shows the content (JSON) of the call to the lambda function and returns a message including this content, using classes for Request and Response.

## Requirements

* You must have an [Amazon Web Services (AWS)](http://aws.amazon.com/) account.

* The code was written for Java 8 and AWS SDK for Java 1.x.

## Using the code

* Access the AWS console.

* Select AWS Lambda in the services menu.

* Create an AWS lambda function:
  * Name: <LAMBDA_NAME>
  * Runtime: Java 8
  * Role: lambda-basic-execution
  * The triggers: Nothing
  * The resources the function's role has access: Amazon CloudWatch Logs

  Handler function:

  ```bash
  example.Hello::handleRequest
  ```

* Upload the Java JAR file.

  Artifact:

  ```bash
  awslambdahello2.jar
  ```

* Create and configure a Test event:

  Input JSON file content:

  ```bash
  {
    "firstName": "Peter",
    "lastName": "Parker"
  }
  ```

* Run the code.

  Run the code in an AWS lambda function using the test button.

* Test the AWS Lambda function.

  You should see the next message in the log:

  ```bash
  {
    "message": "Hello Peter Parker!"
  }
  ```

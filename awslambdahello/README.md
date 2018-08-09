# AWS Lambda Function Hello World Java example

This folder contains an AWS Lambda Function example in Java on AWS (Amazon Web Services).

It handles a simple AWS Lambda function that shows the content of the call to the lambda function and returns a message including this content.

## Requirements

* You must have an [Amazon Web Services (AWS)](http://aws.amazon.com/) account.
* The code was written for Java 1.8 and AWS SDK for Java 1.11.x.

## Using the code

* Access the AWS console.

* Select AWS Lambda in the services menu.

* Create an AWS lambda function:
  * Name:    LAMBDA_NAME
  * Runtime: Java 8
  * Role:    lambda-basic-execution
  * The triggers: Nothing
  * The resources the function's role has access: Amazon CloudWatch Logs

  Handler function:

  ```bash
  example.Hello::myHandler
  ```

* Upload the Java JAR file.

  Artifact:

  ```bash
  awslambdahello.jar
  ```

* Create and configure a Test event:

  Input file content:

  ```bash
  "Peter"
  ```

* Test the AWS Lambda function:

  Run the code in a AWS lambda function using the test button.

  You should see the next message in the log:

  ```bash
  "Hello Peter"
  ```

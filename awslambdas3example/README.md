# AWS Lambda Function S3 Java example

This folder contains an AWS Lambda Function example in Java on AWS (Amazon Web Services).

It handles an AWS Lambda function that sends information to the log about a file when it appears in a S3 bucket.

## Requirements

* You must have an [Amazon Web Services (AWS)](http://aws.amazon.com/) account.
* The code was written for Java 1.8 and AWS SDK for Java 1.11.x.

## Using the code

* Access the AWS console.

* Create a S3 bucket.

* Create an AWS lambda function:
  * Name: <LAMBDA_NAME>
  * Runtime: Java 8
  * Role: Role-VM-buckets
  * The triggers: S3 (with access to the S3 bucket and Event type: ObjectCreated)
  * The resources the function's role has access: Amazon CloudWatch Logs

  Handler function:

  ```bash
  example.S3example::handleRequest
  ```

* Upload the Java JAR file.

  Artifact:

  ```bash
  awslambdas3example.jar
  ```

* Test the function:

  Copy a file in the source S3 bucket.

  You should see the next messages in the log:

  ```bash
  "Input: <LAMBDA_INPUT>"
  "Bucket: <BUCKET_NAME>"
  "File: <FILE_NAME>"
  ```
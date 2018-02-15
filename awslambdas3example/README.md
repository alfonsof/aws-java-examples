# AWS Lambda Function Java example

This folder contains an example of a Lambda Function in Java on AWS (Amazon Web Services).
Send information to the log about a file when it appears in a S3 bucket.




## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

This code was written for Java 1.8 and AWS SDK for Java 1.11.x.




## Using the code

Configure your AWS access keys.

Create a S3 bucket for the source.

Run the code in a AWS lambda function.

Artifact:
\out\artifacts\awslambdas3example_jar\awslambdas3example.jar

Handler function:
example.Hello::handleRequest

Test the function:
Copy a file in the source S3 bucket.
Watch the log file and you should see information about the file.


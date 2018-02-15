# AWS Lambda Function Hello World Java other example

This folder contains an example of a Lambda Function in Java on AWS (Amazon Web Services).




## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

This code was written for Java 1.8 and AWS SDK for Java 1.11.x.




## Using the code

Configure your AWS access keys.

Run the code in a AWS lambda function.

Artifact:
\out\artifacts\awslambdahello2_jar\awslambdahello2.jar

Handler function:
example.Hello::handleRequest

Test the function:
Input JSON file content:
{
  "firstName": "Peter",
  "lastName": "Parker"
}

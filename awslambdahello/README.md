# AWS Lambda Function Hello World Java example

This folder contains an AWS Lambda Function example in Java on AWS (Amazon Web Services).
Handle an AWS simple Lambda function that show the content of the call to the lambda function and return a message with this content.




## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

This code was written for Java 1.8 and AWS SDK for Java 1.11.x.




## Using the code

Configure your AWS access keys.

Create an AWS lambda function:
* Name:    SOME_NAME
* Runtime: Java 8
* Role:    lambda-basic-execution
* The triggers: Nothing
* The resources the function's role has access: Amazon CloudWatch Logs

Handler function:

```
example.Hello::myHandler
```

Upload the Java JAR file.

Artifact:

```
\out\artifacts\awslambdahello_jar\awslambdahello.jar
```

Create and configure a Test event:

Input file content:

```
"Peter"
```

Test the AWS Lambda function:

Run the code in a AWS lambda function using the test button.

You should see the next message in the log:

```
"Hello Peter" 
```


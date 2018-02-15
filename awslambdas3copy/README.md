# AWS Lambda Function S3 Copy Java example

This folder contains an example of a Lambda Function in Java on AWS (Amazon Web Services)
that Copy a file when it appears in a S3 bucket to another S3 bucket.




## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

This code was written for Java 1.8 and AWS SDK for Java 1.11.x.




## Using the code

Configure your AWS access keys.

Create a S3 bucket for the source and another S3 bucket for the target.

Create IAM Policy:
Policy-VM-buckets

Content of the IAM policy:

```
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "s3:GetObject"
            ],
            "Resource": [
                "arn:aws:s3:::sourcevm/*"
            ]
        },
        {
            "Effect": "Allow",
            "Action": [
                "s3:PutObject"
            ],
            "Resource": [
                "arn:aws:s3:::targetvm/*"
            ]
        },
        {
            "Sid": "Stmt1430872844000",
            "Effect": "Allow",
            "Action": [
                "cloudwatch:*"
            ],
            "Resource": [
                "*"
            ]
        },
        {
            "Sid": "Stmt1430872852000",
            "Effect": "Allow",
            "Action": [
                "logs:*"
            ],
            "Resource": [
                "*"
            ]
        }
    ]
}
```

Create role:
Role-VM-buckets

Basic Settings for the lambda funtion:
Memory (MB): 1024
Timeout: 10 sec

Run the code in a AWS lambda function.

Artifact:
\out\artifacts\awslambdas3copy_jar\awslambdas3copy.jar

Handler function:
example.S3Copy::handleRequest

Test the function:
Copy a file in the source S3 bucket.
The file should be copied to the target S3 bucket.


# AWS Lambda Function S3 Move Java example

This folder contains an AWS Lambda Function example in Java on AWS (Amazon Web Services).
It handles an AWS Lambda function that moves a file when it appears in a S3 bucket to another S3 bucket.

## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

This code was written for Java 1.8 and AWS SDK for Java 1.11.x.

## Using the code

* Configure your AWS access keys.

* You can select the destination bucket name changing the value of "destinationBucketName" variable in the code.

* Create a S3 bucket for the source and another S3 bucket for the target.

* Create a IAM Policy: Policy-VM-buckets:

  Content of the IAM policy:

  ```bash
  {
      "Version": "2012-10-17",
      "Statement": [
          {
              "Effect": "Allow",
              "Action": [
                  "s3:GetObject",
                  "s3:DeleteObject"
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

* Create a role: Role-VM-buckets:

  This role uses the policy: Policy-VM-buckets

* Create an AWS lambda function:
  * Name: SOME_NAME
  * Runtime: Java 8
  * Role: Role-VM-buckets
  * The triggers: S3 (with access to the S3 bucket and Event type: ObjectCreated)
  * The resources the function's role has access: Amazon CloudWatch, Amazon CloudWatch Logs, Amazon S3

  Basic Settings for the lambda function:

  * Memory (MB): 1024
  * Timeout: 10 sec

  Handler function:

  ```bash
  example.S3Move::handleRequest
  ```

* Upload the Java JAR file.

  Artifact:

  ```bash
  awslambdas3move.jar
  ```

* Test the function:

  Copy a file in the source S3 bucket.

  The file from the source S3 bucket should be copied to the target S3 bucket and deleted in the source S3 bucket.

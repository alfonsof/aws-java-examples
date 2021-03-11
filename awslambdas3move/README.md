# AWS Lambda Function S3 Move Java example

This folder contains an AWS Lambda Function example in Java on AWS (Amazon Web Services).

It handles an AWS Lambda function that moves an object when it appears in a S3 bucket to another S3 bucket.

## Requirements

* You must have an [Amazon Web Services (AWS)](http://aws.amazon.com/) account.

* The code was written for:
 
  *  Java 8
  *  Apache Maven (> 3.0)
  *  AWS SDK for Java (SDK V1)

## Using the code

* You can select the destination bucket name changing the value of `DESTINATION_BUCKET` variable in the code.

* Access the AWS console.

* Create a S3 bucket for the source and another S3 bucket for the target.

* Create an IAM Policy: ex. `Policy-VM-buckets`

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

* Create a role: `Role-VM-buckets`.

  This role uses the policy `Policy-VM-buckets`

* Create an AWS lambda function:
  * Name: `<LAMBDA_NAME>`
  * Runtime: `Java 8`
  * Handler: `example.S3Move::handleRequest`
  * Role: `Role-VM-buckets`
  * The triggers:
    * `S3`
      * Bucket: `<SOURCE_BUCKET_NAME>`
      * Event type: `ObjectCreated`
      * Enable trigger: `Yes`
  * The resources that the function's role has access to:
    * `Amazon CloudWatch`
    * `Amazon CloudWatch Logs`
    * `Amazon S3`
      * Lambda obtained information from the policy statements: `Managed policy Policy-VM-buckets`:
        * `s3:GetObject` --> `Allow: arn:aws:s3:::sourcevm/*`
        * `s3:DeleteObject` --> `Allow: arn:aws:s3:::sourcevm/*`
        * `s3:PutObject` --> `Allow: arn:aws:s3:::targetvm/*`
  * Basic Settings for the lambda function:
    * Memory (MB): `1024`
    * Timeout: `10 sec`

* Upload the Java JAR file.

  Artifact:

  ```bash
  awslambdas3move.jar
  ```

* Save the Lambda function.

  It deploys the Lambda function.

* Test the function:

  Copy a file in the source S3 bucket.

  The object from the source S3 bucket should be copied to the target S3 bucket and deleted in the source S3 bucket.

# Java examples on AWS (Amazon Web Services)

This repo contains Java code examples on AWS (Amazon Web Services).

These examples show how to use Java 8 and AWS SDK for Java 1.x in order to manage Amazon services on AWS.

AWS SDK for Java allows Java developers to write software that makes use of Amazon services like EC2 and S3.

## Quick start

You must have an [AWS (Amazon Web Services)](http://aws.amazon.com/) account.

The code for the samples is contained in individual folders on this repository.

For instructions on running the code, please consult the README in each folder.

This is the list of examples:

**Compute - Amazon EC2:**

* [awsec2instances](/awsec2instances) - AWS EC2 instances: Example of how to handle AWS EC2 instances.

**Compute - AWS Lambda:**

* [awslambdahello](/awslambdahello) - AWS Lambda Function Hello World: Example of how to handle an AWS simple Lambda function and a text input.
* [awslambdahello2](/awslambdahello2) - AWS Lambda Function other Hello World: Example of how to handle an AWS simple Lambda  function and a JSON input, using classes for Request and Response.
* [awslambdas3example](/awslambdas3example) - AWS Lambda Function S3 example: Example of how to handle an AWS Lambda Function and send information to the log about an object when it appears in a S3 bucket.
* [awslambdas3copy](/awslambdas3copy) - AWS Lambda Function S3 Copy: Example of how to handle an AWS Lambda function and copy an object when it appears in a S3 bucket to another S3 bucket.
* [awslambdas3move](/awslambdas3move) - AWS Lambda Function S3 Move: Example of how to handle an AWS Lambda function and move an object when it appears in a S3 bucket to another S3 bucket.

**Storage - Amazon S3:**

* [awss3create](/awss3create) - AWS S3 Create: Example of how to handle S3 buckets and create a new S3 bucket.
* [awss3delete](/awss3delete) - AWS S3 Delete: Example of how to handle S3 buckets and delete a S3 bucket.
* [awss3list](/awss3list) - AWS S3 List: Example of how to handle S3 buckets and list information about the objects in a S3 bucket.
* [awss3listall](/awss3listall) - AWS S3 List All: Example of how to handle S3 buckets and list information about all S3 buckets and the objects that they contain.
* [awss3upload](/awss3upload) - AWS S3 Upload: Example of how to handle S3 buckets and upload a local file to a S3 bucket.
* [awss3download](/awss3download) - AWS S3 Download: Example of how to handle S3 buckets and download an object from a S3 bucket to a local file.
* [awss3copy](/awss3copy) - AWS S3 Copy: Example of how to handle S3 buckets and copy an object from a S3 bucket to another S3 bucket.
* [awss3move](/awss3move) - AWS S3 Move: Example of how to handle S3 buckets and move an object from a S3 bucket to another S3 bucket.

## License

This code is released under the MIT License. See LICENSE file.
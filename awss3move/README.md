# AWS S3 Move Java example

This folder contains a Java application example that handles S3 buckets on AWS (Amazon Web Services).

Move a file from a S3 bucket to another S3 bucket.

## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

This code was written for Java 1.8 and AWS SDK for Java 1.11.x.

## Using the code

* Configure your AWS access keys.

* Create a S3 bucket for the source and another S3 bucket for the target.

* Copy a file to the source S3 bucket.

* Run the code.

  You must provide 3 parameters:
  
  * `<SOURCE_BUCKET>`      = Source bucket name
  * `<SOURCE_FILE>`        = Source file name
  * `<DESTINATION_BUCKET>` = Destination bucket name

  Run application:

  ```bash
  java -jar awss3move.jar source-bucket source-file destination-bucket
  ```

* Test the application.

  The file from the source S3 bucket should be copied to the target S3 bucket and deleted in the source S3 bucket.

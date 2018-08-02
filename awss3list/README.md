# AWS S3 List Java example

This folder contains a Java application example that handles S3 buckets on AWS (Amazon Web Services).

List information about the files in a S3 bucket.

## Requirements

* You must have an [Amazon Web Services (AWS)](http://aws.amazon.com/) account.
* The code was written for Java 1.8 and AWS SDK for Java 1.11.x.

## Using the code

* Configure your AWS access keys.

* Create a S3 bucket.

* Copy some files to the S3 bucket.

* Run the code.

  You must provide 1 parameter:
  
  * `<BUCKET_NAME>` = Bucket name

  Run application:

  ```bash
  java -jar awss3list.jar bucket-name
  ```

* Test the application.

  You should see the list of files stored in the S3 bucket.

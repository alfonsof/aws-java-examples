# AWS S3 Upload Java example

This folder contains a Java application example that handles S3 buckets on AWS (Amazon Web Services).

Upload a local file to a S3 bucket.

## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

* The code was written for Java 1.8 and AWS SDK for Java 1.11.x.

## Using the code

* Configure your AWS access keys.

* Run the code.

  You must provide 3 parameters:
  
  * `<BUCKET_NAME>`     = Bucket name
  * `<OBJECT_NAME>`     = Object file name in the bucket
  * `<LOCAL_FILE_NAME>` = Local file name

  Run application:

  ```bash
  java -jar awss3upload.jar bucket-name object-name local-file
  ```

* Test the application.

  You should see the new object created in the S3 bucket.

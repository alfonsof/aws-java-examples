# AWS S3 Download Java example

This folder contains a Java application example that handles S3 buckets on AWS (Amazon Web Services).

Download an object from a S3 bucket to a local file.




## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

This code was written for Java 1.8 and AWS SDK for Java 1.11.x.




## Using the code

* Configure your AWS access keys.

* Run the code.

  You must provide 3 parameters:
  
  * `<BUCKET_NAME>`     = Bucket name
  * `<OBJECT_NAME>`     = Object file name in the bucket
  * `<LOCAL_FILE_NAME>` = Local file name

  Run application:
  
  ```
  java -jar awss3download.jar bucket-name object-name local-file
  ```

* Test the application.

  You should see the new file created in your local destiny from the S3 bucket.

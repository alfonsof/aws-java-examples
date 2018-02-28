# AWS S3 Delete Java example

This folder contains a Java application example that handles S3 buckets on AWS (Amazon Web Services).

Delete a S3 bucket.




## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

This code was written for Java 1.8 and AWS SDK for Java 1.11.x.




## Using the code

* Configure your AWS access keys.

* You can select the bucket region changing the value of "region" variable in the code.

* Run the code:

  You must provide 1 parameter:
  
  <BUCKET_NAME> = Bucket name

  ```
  java -jar awss3delete.jar bucket-name
  ```

* Test the application:

  You should not see the S3 bucket deleted.

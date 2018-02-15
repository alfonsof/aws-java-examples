# AWS S3 List Java example

This folder contains an example of a Java application that manages S3 buckets on AWS (Amazon Web Services).
Copy a file from a S3 bucket to another S3 bucket.




## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

This code was written for Java 1.8 and AWS SDK for Java 1.11.x.




## Using the code

Configure your AWS access keys.

Create a S3 bucket for the source and another S3 bucket for the target.

Copy a files to the S3 bucket.

Run the code:

```
java -jar out/artifacts/awss3copy_jar/awss3copy.jar
```

Test the application:

The file from the source S3 bucket should be copied to the target S3 bucket and deleted in the source S3 bucket.

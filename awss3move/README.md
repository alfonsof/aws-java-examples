# AWS S3 Move Java example

This folder contains an example of a Java application that manages S3 buckets on AWS (Amazon Web Services).
Move a file from a S3 bucket to another S3 bucket.




## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

This code was written for Java 1.8 and AWS SDK for Java 1.11.x.




## Using the code

Configure your AWS access keys.

Create a S3 bucket for the source and another S3 bucket for the target.

Copy a files to the S3 bucket.

Run the code:

```
java -jar out/artifacts/awss3move_jar/awss3move.jar
```

Test the application:

You should see information of the files stored in the S3 bucket.

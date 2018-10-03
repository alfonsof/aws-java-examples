# AWS S3 List All Java example

This folder contains a Java application example that handles S3 buckets on AWS (Amazon Web Services).

List information about all S3 buckets and the objects that they contain.

## Requirements

* You must have an [Amazon Web Services (AWS)](http://aws.amazon.com/) account.

* The code was written for Java 8 and AWS SDK for Java 1.x.

## Using the code

* Configure your AWS access keys.

  **Important:** For security, it is strongly recommend that you use IAM users instead of the root account for AWS access.

  When you initialize a new service client without supplying any arguments, the AWS SDK for Java attempts to find AWS credentials by using the default credential provider chain.

  Setting your credentials for use by the AWS SDK for Java can be done in a number of ways, but here are the recommended approaches:

  * The default credential profiles file
  
    Set credentials in the AWS credentials profile file on your local system, located at:

    `~/.aws/credentials` on Linux, macOS, or Unix

    `C:\Users\USERNAME\.aws\credentials` on Windows

    This file should contain lines in the following format:

    ```bash
    [default]
    aws_access_key_id = <your_access_key_id>
    aws_secret_access_key = <your_secret_access_key>
    ```
    Substitute your own AWS credentials values for the values `<your_access_key_id>` and `<your_secret_access_key>`.

  * Environment variables `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY`
  
    Set the `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY` environment variables.

    To set these variables on Linux, macOS, or Unix, use `export`:

    ```bash
    export AWS_ACCESS_KEY_ID=<your_access_key_id>
    export AWS_SECRET_ACCESS_KEY=<your_secret_access_key>
    ```

    To set these variables on Windows, use `set`:

    ```bash
    set AWS_ACCESS_KEY_ID=<your_access_key_id>
    set AWS_SECRET_ACCESS_KEY=<your_secret_access_key>
    ```

* You can select the AWS region changing the value of `REGION` variable in the code.

* Create some S3 buckets.

* Copy some files to the S3 buckets.

* Run the code.

  Run application:

  ```bash
  java -jar awss3listall.jar
  ```

* Test the application.

  You should see the list of buckets and objects stored in each S3 buckets.

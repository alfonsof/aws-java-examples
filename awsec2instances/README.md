# AWS EC2 Instances Java example

This folder contains a Java application example that handles EC2 instances on AWS (Amazon Web Services).

## Requirements

* You must have an [Amazon Web Services (AWS) account](http://aws.amazon.com/).

* The code was written for Java 1.8 and AWS SDK for Java 1.11.x.

## Using the code

* Configure your AWS access keys.

* Run the code.

  Run application:

  ```bash
  java -jar awsec2instances.jar
  ```

  You can select an option in the menu in order to run every command:

  * 1 = Describe all instances
  * 2 = Run new instance
  * 3 = Describe instance
  * 4 = Start instance
  * 5 = Stop instance
  * 6 = Reboot instance
  * 7 = Terminate instance

* Test the application.

  You should see the new instance and modification of states with the AWS console.

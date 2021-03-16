/**
 * AWS Lambda Function S3 Event Java example.
 * It handles an AWS simple Lambda function that sends information to the log
 * about an object when it appears in a S3 bucket.
 */
 
package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;

public class S3CreationEvent implements RequestHandler<com.amazonaws.services.lambda.runtime.events.S3Event, String> {

    public String handleRequest(com.amazonaws.services.lambda.runtime.events.S3Event input, Context context) {
        // Get Event Record
        S3EventNotificationRecord record = input.getRecords().get(0);

        // Source Bucket Name
        String srcBucketName = record.getS3().getBucket().getName();

        // Source File Name
        String srcObjectName = record.getS3().getObject().getKey(); // Name doesn't contain any special characters

        context.getLogger().log("Input:  " + input + "\n");
        context.getLogger().log("Bucket: " + srcBucketName + "\n");
        context.getLogger().log("Object: " + srcObjectName + "\n");

        return srcBucketName + "/" + srcObjectName;
    }
}

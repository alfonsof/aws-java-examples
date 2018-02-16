/**
 * AWS Lambda Function S3 Java example
 * It handles an AWS simple Lambda function that sends information to the log about a file
 * when it appears in a S3 bucket
 */
 
package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;

public class S3Example implements RequestHandler<S3Event, String>{

    public String handleRequest(S3Event input, Context context){
        // Get Event Record
        S3EventNotificationRecord record = input.getRecords().get(0);

        // Source Bucket Name
        String srcBucketName = record.getS3().getBucket().getName();

        // Source File Name
        String srcFileName = record.getS3().getObject().getKey(); // Name doesn't contain any special characters

        context.getLogger().log("Input: " + input + "\n");
        context.getLogger().log("Bucket: " + srcBucketName + "\n");
        context.getLogger().log("File: " + srcFileName + "\n");

        return srcBucketName + "/" + srcFileName;
    }
}

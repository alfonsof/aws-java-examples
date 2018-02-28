/**
 * AWS Lambda Function S3 Copy Java example
 * It handles an AWS Lambda function that copies a file
 * when it appears in a S3 bucket to another S3 bucket
 */

package example;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.LambdaLogger;


public class S3Copy implements RequestHandler<S3Event, String> {

    public String handleRequest(S3Event input, Context context) {
        String sourceBucketName;                      // Source bucket name
        String sourceKeyName;                         // Source key
        String destinationBucketName = "targetvm";    // Destination bucket name
        String destinationKeyName;                    // Destination key

        LambdaLogger logger = context.getLogger();

        // Get Event Record
        S3EventNotificationRecord record = input.getRecords().get(0);

        // Source Bucket Name
        sourceBucketName = record.getS3().getBucket().getName();

        // Source File Name
        sourceKeyName = record.getS3().getObject().getKey(); // Name doesn't contain any special characters

        // Destination File Name
        destinationKeyName = sourceKeyName;

        logger.log("Input: " + input);
        logger.log("Source Bucket: " + sourceBucketName + "\n");
        logger.log("Source File:   " + sourceKeyName + "\n");
        logger.log("Target Bucket: " + destinationBucketName + "\n");
        logger.log("Target File:   " + destinationKeyName + "\n");

        // Instantiates a client
        AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

        try {
            logger.log("Copying object...");

            // Copy object
            CopyObjectRequest copyObjRequest = new CopyObjectRequest(
                    sourceBucketName, sourceKeyName, destinationBucketName, destinationKeyName);

            s3client.copyObject(copyObjRequest);

            logger.log("Copied");

        } catch (AmazonServiceException ase) {
            context.getLogger().log("Caught an AmazonServiceException, " +
                    "which means your request made it " +
                    "to Amazon S3, but was rejected with an error " +
                    "response for some reason.\n");
            logger.log("Error Message:    " + ase.getMessage() + "\n");
            logger.log("HTTP Status Code: " + ase.getStatusCode() + "\n");
            logger.log("AWS Error Code:   " + ase.getErrorCode() + "\n");
            logger.log("Error Type:       " + ase.getErrorType() + "\n");
            logger.log("Request ID:       " + ase.getRequestId() + "\n");
        } catch (AmazonClientException ace) {
            logger.log("Caught an AmazonClientException, " +
                    "which means the client encountered " +
                    "an internal error while trying to " +
                    " communicate with S3, " +
                    "such as not being able to access the network." + "\n");
            logger.log("Error Message: " + ace.getMessage() + "\n");
        }

        s3client.shutdown();

        return destinationBucketName + "/" + destinationKeyName;
    }
}

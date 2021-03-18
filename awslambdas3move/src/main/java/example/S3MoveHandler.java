/**
 * AWS Lambda Function S3 Move Java example
 * It handles an AWS Lambda function that moves an object
 * when it appears in a S3 bucket to another S3 bucket.
 */

package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;

public class S3MoveHandler implements RequestHandler<S3Event, String> {

    private static final String DESTINATION_BUCKET        = "targetbucket10";      // Destination bucket name

    public String handleRequest(S3Event input, Context context) {
        String sourceBucketName;              // Source bucket name
        String sourceKeyName;                 // Source key name
        String destinationBucketName;         // Destination bucket name
        String destinationKeyName;            // Destination key name

        LambdaLogger logger = context.getLogger();

        // Get Event Record
        S3EventNotificationRecord record = input.getRecords().get(0);

        // Source Bucket Name
        sourceBucketName = record.getS3().getBucket().getName();

        // Source Object Name
        sourceKeyName = record.getS3().getObject().getKey(); // Name doesn't contain any special characters

        // Destination Bucket Name
        destinationBucketName = DESTINATION_BUCKET;

        // Destination File Name
        destinationKeyName = sourceKeyName;

        logger.log("Input: " + input);
        logger.log("Source Bucket: " + sourceBucketName + "\n");
        logger.log("Source Object: " + sourceKeyName + "\n");
        logger.log("Target Bucket: " + destinationBucketName + "\n");
        logger.log("Target Object: " + destinationKeyName + "\n");

        // Instantiates a client
        AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

        try {
            logger.log("Moving object ...");

            // Copy object
            CopyObjectRequest copyObjRequest = new CopyObjectRequest(
                    sourceBucketName, sourceKeyName, destinationBucketName, destinationKeyName);
            s3client.copyObject(copyObjRequest);

            // Delete Object
            DeleteObjectRequest deleteObjRequest = new DeleteObjectRequest(
                    sourceBucketName, sourceKeyName);
            s3client.deleteObject(deleteObjRequest);

            logger.log("Moved");

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

/**
 * S3Move is an example that handles S3 buckets on AWS.
 * Move an object from a S3 bucket to another S3 bucket.
 * You must provide 3 parameters:
 * SOURCE_BUCKET      = Source bucket name
 * SOURCE_OBJECT      = Source object name
 * DESTINATION_BUCKET = Destination bucket name
 */

package example;

import java.io.IOException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;

public class S3Move {

    public static void main(String[] args) throws IOException {
        String region = "eu-west-1";   // Region name
        String sourceBucketName;       // Source bucket name
        String sourceKey;              // Source key
        String destinationBucketName;  // Destination bucket name
        String destinationKey;         // Destination key

        if (args.length < 3) {
            System.out.println("Not enough parameters.\nProper Usage is: java -jar s3move.jar <SOURCE_BUCKET> <SOURCE_OBJECT> <DESTINATION_BUCKET>");
            System.exit(1);
        }

        sourceBucketName      = args[0];
        sourceKey             = args[1];
        destinationBucketName = args[2];
        destinationKey        = sourceKey;

        System.out.println("From - bucket: " + sourceBucketName);
        System.out.println("From - object: " + sourceKey);
        System.out.println("To   - bucket: " + destinationBucketName);
        System.out.println("To   - object: " + destinationKey);

        // Instantiates a client
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withRegion(region).build();

        try {
            System.out.println("Moving object ...");

            // Copy object
            CopyObjectRequest copyObjRequest = new CopyObjectRequest(
                    sourceBucketName, sourceKey, destinationBucketName, destinationKey);
            s3client.copyObject(copyObjRequest);

            // Delete Object
            DeleteObjectRequest deleteObjRequest = new DeleteObjectRequest(
                    sourceBucketName, sourceKey);
            s3client.deleteObject(deleteObjRequest);
            //s3client.deleteObject(SourceBucketName, SourceKey);

            System.out.println("Moved");

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, " +
                    "which means your request made it " +
                    "to Amazon S3, but was rejected with an error " +
                    "response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, " +
                    "which means the client encountered " +
                    "an internal error while trying to " +
                    " communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        s3client.shutdown();
    }
}
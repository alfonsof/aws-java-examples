/**
 * S3Copy is an example that handles S3 buckets on AWS
 * Copy a file from a S3 bucket to another S3 bucket
 * You must use 3 parameters:
 * SOURCE_BUCKET      = Source bucket name
 * SOURCE_FILE        = Source file name
 * DESTINATION_BUCKET = Destination bucket name
 */

package example;

import java.io.IOException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;

public class S3Copy {

    public static void main(String[] args) throws IOException {
        String sourceBucketName;       // Source bucket name
        String sourceKey;              // Source key
        String destinationBucketName;  // Destination bucket name
        String destinationKey;         // Destination key

        if (args.length < 3) {
            System.out.println("Not enough parameters. Proper Usage is: java -jar s3copy.jar <SOURCE_BUCKET> <SOURCE_FILE> <DESTINATION_BUCKET>");
            System.exit(1);
        }

        sourceBucketName      = args[0];
        sourceKey             = args[1];
        destinationBucketName = args[2];
        destinationKey        = sourceKey;

        System.out.println("From - bucket: " + sourceBucketName);
        System.out.println("From - file:   " + sourceKey);
        System.out.println("To   - bucket: " + destinationBucketName);
        System.out.println("To   - file:   " + destinationKey);

        // Instantiates a client
        AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

        try {
            System.out.println("Copying object...");

            // Copy object
            CopyObjectRequest copyObjRequest = new CopyObjectRequest(
                    sourceBucketName, sourceKey, destinationBucketName, destinationKey);
            s3client.copyObject(copyObjRequest);

            System.out.println("Copied");

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
/**
 * S3Copy is an example that handles S3 buckets on AWS
 * Move a file from a S3 bucket to another S3 bucket
 */

package example;

import java.io.IOException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;

public class S3Move {
    private static String SourceBucketName      = "sourcevm";     // Source bucket name
    private static String SourceKey             = "example.txt";  // Source key
    private static String DestinationBucketName = "targetvm";     // Destination bucket name
    private static String destinationKey        = "example.txt";  // Destination key

    public static void main(String[] args) throws IOException {
        AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
        try {
            System.out.println("From - bucket: " + SourceBucketName);
            System.out.println("From - file:   "   + SourceKey);
            System.out.println("To   - bucket: " + DestinationBucketName);
            System.out.println("To   - file:   "   + destinationKey);

            System.out.println("Moving object...");

            // Copy object
            CopyObjectRequest copyObjRequest = new CopyObjectRequest(
                    SourceBucketName, SourceKey, DestinationBucketName, destinationKey);
            s3client.copyObject(copyObjRequest);

            // Delete Object
            DeleteObjectRequest deleteObjRequest = new DeleteObjectRequest(
                    SourceBucketName, SourceKey);
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
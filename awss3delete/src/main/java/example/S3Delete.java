/**
 * S3Delete is an example that handles S3 buckets on AWS
 * Delete a S3 bucket
 * You must use 1 parameter:
 * BUCKET_NAME = Name of the bucket
 */

package example;

import java.io.IOException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;

public class S3Delete {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Not enough parameters. Proper Usage is: java -jar s3delete.jar <BUCKET_NAME>");
            System.exit(1);
        }

        // The name for the new bucket
        String bucketName = args[0];

        System.out.println("Bucket name: " + bucketName);

        // Instantiates a client
        AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

        try {
            System.out.println("Deleting bucket...");

            if (s3client.doesBucketExistV2(bucketName)) {
                s3client.deleteBucket(bucketName);
                System.out.println("Deleted");
            } else {
                System.out.println("Bucket does not exist!!");
            }

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
}

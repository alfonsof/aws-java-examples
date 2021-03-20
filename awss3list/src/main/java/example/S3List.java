/**
 * S3List is an example that handles S3 buckets on AWS.
 * List information about the objects in a S3 bucket.
 * You must provide 1 parameter:
 * BUCKET_NAME     = Bucket name
 */

package example;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;


public class S3List {

    private static final String REGION = "eu-west-1";      // Region name

    public static void main(String[] args) {

        String bucketName;  // Bucket name

        if (args.length < 1) {
            System.out.println("Not enough parameters.\n" +
                    "Proper Usage is: java -jar s3list.jar <BUCKET_NAME>");
            System.exit(1);
        }

        // The name for the bucket
        bucketName = args[0];

        System.out.println("Bucket name: " + bucketName);

        // Instantiates a client
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withRegion(REGION).build();

        try {
            if (s3client.doesBucketExistV2(bucketName)) {
                System.out.println("Listing objects ...");
                // List objects in a Bucket
                ObjectListing objectListing = s3client.listObjects(new ListObjectsRequest()
                        .withBucketName(bucketName));
                for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                    System.out.println(" - " + objectSummary.getKey() + "  " +
                            "(size = " + objectSummary.getSize() + ")");
                }
                System.out.println("Listed");
            } else {
                System.out.println("Error: Bucket does not exist!!");
            }
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, " +
                    "which means your request made it " +
                    "to Amazon S3, but was rejected with an error response " +
                    "for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, " +
                    "which means the client encountered " +
                    "an internal error while trying to communicate" +
                    " with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        s3client.shutdown();
    }
}

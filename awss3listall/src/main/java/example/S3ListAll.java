/**
 * S3ListAll is an example that handles S3 buckets on AWS.
 * List information about all S3 buckets and the objects that they contain.
 */

package example;

import java.util.List;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;


public class S3ListAll {

    private static final String REGION = "eu-west-1";      // Region name

    public static void main(String[] args) {

        // Instantiates a client
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withRegion(REGION).build();

        try {
            System.out.println("Listing S3 buckets and objects ...");
            // List Buckets
            List<Bucket> buckets = s3client.listBuckets();
            System.out.println("Your Amazon S3 buckets:");
            String bucketName;
            for (Bucket b : buckets) {
                bucketName = b.getName();
                System.out.println("* Bucket: " + bucketName);
                ObjectListing objectListing = s3client.listObjects(new ListObjectsRequest()
                        .withBucketName(bucketName));
                // List Objects
                for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                    System.out.println("  - Object: " + objectSummary.getKey() + "  " +
                            "(size = " + objectSummary.getSize() + ")");
                }
            }
            System.out.println("Listed");
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

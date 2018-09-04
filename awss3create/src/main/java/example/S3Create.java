/**
 * S3Create is an example that handles S3 buckets on AWS.
 * Create a new S3 bucket.
 * You must provide 1 parameter:
 * BUCKET_NAME = Name of the bucket
 */

package example;

import java.io.IOException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;

public class S3Create {

    private static final String REGION = "eu-west-1";      // Region name

    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
           System.out.println("Not enough parameters.\nProper Usage is: java -jar s3create.jar <BUCKET_NAME>");
           System.exit(1);
        }

        // The name for the new bucket
        String bucketName = args[0];

        System.out.println("Bucket name: " + bucketName);

        // Instantiates a client
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withRegion(REGION).build();

        // You can use this instead of the previous one if you want to create a bucket in the default region
        // AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

        try {
            if (!s3client.doesBucketExistV2(bucketName)) {
                System.out.println("Creating bucket ...");
                // Note that CreateBucketRequest does not specify region. So bucket is
                // created in the region specified in the client.
                s3client.createBucket(new CreateBucketRequest(
                        bucketName));
                System.out.println("Created");

                // Get Bucket location
                String bucketLocation = s3client.getBucketLocation(new GetBucketLocationRequest(bucketName));
                System.out.println("Bucket location: " + bucketLocation);
            } else {
                System.out.println("Error: Bucket already exists!!");
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

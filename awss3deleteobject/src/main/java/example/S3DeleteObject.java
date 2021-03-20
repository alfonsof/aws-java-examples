/**
 * S3DeleteObject is an example that handles S3 buckets on AWS.
 * Delete an object in a S3 bucket.
 * You must provide 2 parameters:
 * BUCKET_NAME = Name of the bucket
 * OBJECT_NAME = Name of the object in the bucket
 */

package example;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;


public class S3DeleteObject {
    private static final String REGION = "eu-west-1";      // Region name

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Not enough parameters.\n" +
                    "Proper Usage is: java -jar s3deleteobject.jar <BUCKET_NAME> <OBJECT_NAME>");
            System.exit(1);
        }

        // The name for the bucket
        String bucketName = args[0];

        // The name for the object
        String keyName = args[1];

        System.out.println("Bucket name: " + bucketName);
        System.out.println("Object name: " + keyName);

        // Instantiates a client
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withRegion(REGION).build();

        try {
            boolean exists = s3client.doesObjectExist(bucketName, keyName);
            if (exists) {
                System.out.println("Deleting object ...");

                // Delete Object
                DeleteObjectRequest deleteObjRequest = new DeleteObjectRequest(
                        bucketName, keyName);
                s3client.deleteObject(deleteObjRequest);

                System.out.println("Deleted");
            }
            else {
                System.out.println("Error: Bucket/Object \"" + bucketName + "/" + keyName + "\" does not exist!!");
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
        s3client.shutdown();
    }
}

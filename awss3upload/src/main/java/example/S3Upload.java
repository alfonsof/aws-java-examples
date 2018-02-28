/**
 * S3Upload is an example that handles S3 buckets on AWS
 * Upload a local file to a S3 bucket
 * BUCKET_NAME     = Bucket name
 * OBJECT_NAME     = Object file name in the bucket
 * LOCAL_FILE_NAME = Local file name
 */

package example;

import java.io.File;
import java.io.IOException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Upload {
    public static void main(String[] args) throws IOException {
        String bucketName;     // Bucket name
        String keyName;        // Key name, it is the object name
        String uploadFileName; // Upload local file name

        if (args.length < 3) {
            System.out.println("Not enough parameters. Proper Usage is: java -jar s3upload.jar <BUCKET_NAME> <OBJECT_NAME> <LOCAL_FILE_NAME>");
            System.exit(1);
        }

        bucketName     = args[0];
        keyName        = args[1];
        uploadFileName = args[2];

        System.out.println("Bucket:     " + bucketName);
        System.out.println("Object/Key: " + keyName);
        System.out.println("Local file: " + uploadFileName);

        // Instantiates a client
        AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

        try {
            System.out.println("Uploading an object to S3 from a file ...");

            // Get local file
            File file = new File(uploadFileName);

            // Upload object
            s3client.putObject(new PutObjectRequest(
                    bucketName, keyName, file));

            System.out.println("Uploaded");

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

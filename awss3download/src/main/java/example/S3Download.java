/**
 * S3Download is an example that handles S3 buckets on AWS.
 * Download an object from a S3 bucket to a local file.
 * You must provide 3 parameters:
 * BUCKET_NAME     = Bucket name
 * OBJECT_NAME     = Object file name in the bucket
 * LOCAL_FILE_NAME = Local file name
 */

package example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class S3Download {

    private static final String REGION = "eu-west-1";      // Region name
    private static final int MAX_BUFFER_SIZE = 1024*1000;  // Maximum buffer size for the file

    public static void main(String[] args) throws IOException {
        String bucketName;            // Source bucket name
        String keyName;               // Key name, it is the object name
        String localFileName;         // Local file name

        if (args.length < 3) {
            System.out.println("Not enough parameters.\nProper Usage is: java -jar s3download.jar <BUCKET_NAME> <OBJECT_NAME> <LOCAL_FILE_NAME>");
            System.exit(1);
        }

        bucketName    = args[0];
        keyName       = args[1];
        localFileName = args[2];

        System.out.println("Bucket:     " + bucketName);
        System.out.println("Object/Key: " + keyName);
        System.out.println("Local file: " + localFileName);

        // Instantiates a client
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withRegion(REGION).build();

        try {
            System.out.println("Downloading an object from a S3 to a local file ...");

            // Get object
            S3Object s3object = s3client.getObject(bucketName, keyName);

            // Get content object
            S3ObjectInputStream inputStream = s3object.getObjectContent();

            // Write content to the file
            FileOutputStream fileOutputStream = new FileOutputStream(new File(localFileName));
            byte[] readBuffer = new byte[MAX_BUFFER_SIZE];
            int readLen = 0;
            while ((readLen = inputStream.read(readBuffer)) > 0) {
                fileOutputStream.write(readBuffer, 0, readLen);
            }
            fileOutputStream.close();
            inputStream.close();
            System.out.println("Downloaded");
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

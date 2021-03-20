/**
 * LambdaListAll is an example that handles Lambda functions on AWS.
 * List all Lambda functions and their information
 */

package example;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.FunctionConfiguration;
import com.amazonaws.services.lambda.model.ListFunctionsResult;
import com.amazonaws.services.lambda.model.ServiceException;
import java.util.Iterator;
import java.util.List;


public class LambdaListAll {
    
    private static final String REGION = "eu-west-1";      // Region name

    public static void main(String[] args) {

        ListFunctionsResult functionResult = null;

        AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider())
                .withRegion(REGION).build();

        try {
            System.out.println("Listing Lambda functions ...");

            functionResult = awsLambda.listFunctions();

            List<FunctionConfiguration> list = functionResult.getFunctions();

            for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                FunctionConfiguration config = (FunctionConfiguration)iter.next();

                System.out.println("Function name: "+config.getFunctionName());
                System.out.println("  - ARN: "+config.getFunctionArn());
                System.out.println("  - Runtime: "+config.getRuntime());
                System.out.println("  - Role: "+config.getRole());
                System.out.println("  - Handler: "+config.getHandler());
                System.out.println("  - Description: "+config.getDescription());
                System.out.println("  - Timeout: "+config.getTimeout());
                System.out.println("  - Memory Size: "+config.getMemorySize());
                System.out.println("  - Last Modified: "+config.getLastModified());
                System.out.println("  - Code Size: "+config.getCodeSize());
                System.out.println("  - Version: "+config.getVersion());
                System.out.println();
            }

        } catch (ServiceException e) {
            System.out.println("ServiceException: " + e);
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, " +
                    "which means your request made it " +
                    "to AWS Lambda, but was rejected with an error " +
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
                    " communicate with AWS Lambda, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        awsLambda.shutdown();
    }
}

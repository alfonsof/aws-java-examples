/**
 * LambdaList is an example that handles Lambda functions on AWS.
 * List Lambda function information.
 * You must provide 1 parameter:
 * FUNCTION_NAME      = Lambda function name
 */

package example;

import java.io.IOException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.GetFunctionConfigurationRequest;
import com.amazonaws.services.lambda.model.GetFunctionConfigurationResult;
import com.amazonaws.services.lambda.model.ServiceException;


public class LambdaList {

    private static final String REGION = "eu-west-1";      // Region name

    public static void main(String[] args) throws IOException {

        try {
            AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                    .withCredentials(new ProfileCredentialsProvider())
                    .withRegion(REGION).build();

            if (args.length < 1) {
                System.out.println("Not enough parameters.\nProper Usage is: java -jar lambdalist.jar <FUNCTION_NAME>");
                System.exit(1);
            }

            String functionName = args[0];

            System.out.println("Lambda function name: " + functionName);

            GetFunctionConfigurationRequest configRequest = new GetFunctionConfigurationRequest()
                    .withFunctionName(functionName);
            GetFunctionConfigurationResult config = awsLambda.getFunctionConfiguration(configRequest);

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
    }
}

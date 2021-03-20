/**
 * LambdaInvoke is an example that handles Lambda functions on AWS.
 * Invoke a Lambda function.
 * You must provide 1 parameter:
 * FUNCTION_NAME      = Lambda function name
 */

package example;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;
import java.nio.charset.StandardCharsets;


public class LambdaInvoke {

    private static final String REGION = "eu-west-1";      // Region name

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Not enough parameters.\n" +
                    "Proper Usage is: java -jar lambdainvoke.jar <FUNCTION_NAME>");
            System.exit(1);
        }

        String functionName = args[0];

        System.out.println("Lambda function name: " + functionName);

        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(functionName)
                .withPayload("{\n" +
                        " \"firstName\": \"Peter\",\n" +
                        " \"lastName\": \"Parker\"\n" +
                        "}");

        AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider())
                .withRegion(REGION).build();

        InvokeResult invokeResult = null;

        try {
            System.out.println("Invoking Lambda function ...");

            invokeResult = awsLambda.invoke(invokeRequest);

            String ans = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);

            System.out.println("Lambda return value: " + ans);

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
        System.out.println("Lambda status code: " + invokeResult.getStatusCode());
    }
}
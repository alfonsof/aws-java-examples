/**
 * LambdaUpdate is an example that handles Lambda functions on AWS.
 * Update a Lambda function.
 * You must provide 1 parameter:
 * FUNCTION_NAME      = Lambda function name
 * FUNCTION_FILE      = The path to the JAR or ZIP file where the code of the Lambda function is located
 * FUNCTION_ROLE      = The role ARN that has Lambda permissions
 * FUNCTION_HANDLER   = The fully qualified method name (Ex: example.Handler::handleRequest)
 */

package example;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.ServiceException;
import com.amazonaws.services.lambda.model.UpdateFunctionConfigurationRequest;
import com.amazonaws.services.lambda.model.UpdateFunctionConfigurationResult;
import com.amazonaws.services.lambda.model.UpdateFunctionCodeRequest;
import com.amazonaws.services.lambda.model.UpdateFunctionCodeResult;


public class LambdaUpdate {
    private static final String REGION = "eu-west-1";      // Region name

    public static void main(String[] args) {

        if (args.length < 4) {
            System.out.println("Not enough parameters.\n"+
                    "Proper Usage is: java -jar lambdaupdate.jar " +
                    "<FUNCTION_NAME> <FUNCTION_FILE> <FUNCTION_ROLE> <FUNCTION_HANDLER>");
            System.exit(1);
        }

        String functionName = args[0];
        String functionFile = args[1];
        String functionRole = args[2];
        String functionHandler = args[3];

        System.out.println("Lambda function name:    " + functionName);
        System.out.println("Lambda function file:    " + functionFile);
        System.out.println("Lambda function role:    " + functionRole);
        System.out.println("Lambda function handler: " + functionHandler);

        AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider())
                .withRegion(REGION).build();

        try {
            System.out.println("Updating Lambda function ...");

            byte[] byteArray = Files.readAllBytes(Paths.get(functionFile));
            ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

            // Update Configuration of Lambda function
            UpdateFunctionConfigurationRequest functionConfigurationRequest = new UpdateFunctionConfigurationRequest()
                    .withFunctionName(functionName)
                    .withRuntime("java8")
                    .withRole(functionRole)
                    .withHandler(functionHandler)
                    .withDescription("Created by the Lambda Java API")
                    .withTimeout(15)
                    .withMemorySize(128);
            UpdateFunctionConfigurationResult functionConfigurationResponse =
                                                awsLambda.updateFunctionConfiguration(functionConfigurationRequest);

            // Update Code of Lambda function
            UpdateFunctionCodeRequest functionCodeRequest = new UpdateFunctionCodeRequest()
                    .withFunctionName(functionName)
                    .withZipFile(byteBuffer)
                    .withPublish(true);
            UpdateFunctionCodeResult functionCodeResponse = awsLambda.updateFunctionCode(functionCodeRequest);

            System.out.println("Updated");
            System.out.println("The function ARN (Conf.) is " + functionConfigurationResponse.getFunctionArn());
            System.out.println("The function ARN (Code) is  " + functionCodeResponse.getFunctionArn());

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
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

/**
 * Example to manage AWS Lambda Functions
 * Simple example in Java using RequestClass and ResponseClass
 */

package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Hello implements RequestHandler<RequestClass, ResponseClass>{

    public ResponseClass handleRequest(RequestClass request, Context context){
        context.getLogger().log("First Name: " + request.firstName + "\n");
        context.getLogger().log("Last Name: " + request.lastName + "\n");
        String greetingString = String.format("Hello %s %s.", request.firstName, request.lastName);
        return new ResponseClass(greetingString);
    }
}

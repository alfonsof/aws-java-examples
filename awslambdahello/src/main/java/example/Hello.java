/**
 * Example to manage AWS Lambda Functions
 * Simple example in Java
 */

package example;

import com.amazonaws.services.lambda.runtime.Context;


public class Hello {
    public String myHandler(String name, Context context) {
        context.getLogger().log("Name: " + name + "\n");
        return String.format("Hello %s.", name);
    }
}

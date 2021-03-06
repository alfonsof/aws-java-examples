/**
     * AWSHelper class with methods for managing AWS EC2 instances.
     */

package example;

import java.util.List;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.*;


public final class AWSHelper {
    private static final String REGION        = "eu-west-1";      // Region name
    private static final String AMI_ID        = "ami-785db401";   // AMI Id
    private static final String INSTANCE_TYPE = "t2.micro";       // Instance Type
    //private static final String SECURITY_GROUP = "default";        //"sg-068f967e";

    private AWSHelper() {
    }


    /**
     * Describes all EC2 instances associated with an AWS account
     */
    public static void describeInstances() {
        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                .withRegion(REGION).build();
        boolean done = false;

        System.out.println("Describing EC2 instances ...");

        while (!done) {
            DescribeInstancesRequest request = new DescribeInstancesRequest();
            DescribeInstancesResult response = ec2.describeInstances(request);

            for (Reservation reservation : response.getReservations()) {
                for (Instance instance : reservation.getInstances()) {
                    System.out.printf(
                            "Found reservation with id \"%s\", " +
                                    "AMI \"%s\", " +
                                    "type \"%s\", " +
                                    "state \"%s\" " +
                                    "and monitoring state \"%s\"\n",
                            instance.getInstanceId(),
                            instance.getImageId(),
                            instance.getInstanceType(),
                            instance.getState().getName(),
                            instance.getMonitoring().getState());

                    List<Tag> tags = instance.getTags();
                    System.out.println("      Tags:   " + tags);
                }
            }

            request.setNextToken(response.getNextToken());

            if (response.getNextToken() == null) {
                done = true;
            }
        }
        System.out.println();
    }


    /**
     * Run an EC2 instance
     */
    public static String runInstance() {
        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                                .withRegion(REGION).build();

        System.out.println("Creating EC2 instance ...");

        RunInstancesRequest runRequest = new RunInstancesRequest()
                .withImageId(AMI_ID)
                .withInstanceType(INSTANCE_TYPE)
                .withMaxCount(1)
                .withMinCount(1);
                //.withSecurityGroups(SECURITY_GROUP);

        RunInstancesResult instancesResult = ec2.runInstances(runRequest);

        String reservationId = instancesResult.getReservation().getReservationId();

        System.out.println("Reservation Id:   " + reservationId);

        List<Instance> instances = instancesResult.getReservation().getInstances();
        Instance instance = instances.get(0);
        String instanceId = instance.getInstanceId();

        // Tag EC2 Instance
        String tagName = "my-instance";
        tagInstance(ec2, instanceId, "Name", tagName);
        System.out.printf("Added Tag: Name with Value: %s\n", tagName);

        return instanceId;
    }


    /**
     * Describes an EC2 instance
     */
    public static void describeInstance(String instanceId) {

        if (instanceId == null || instanceId.isEmpty()) {
            System.out.println("Error: NO Instance.");
            return;
        }

        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                                    .withRegion(REGION).build();

        System.out.println("Describing EC2 instance ...");

        DescribeInstancesRequest request = new DescribeInstancesRequest()
                .withInstanceIds(instanceId);
        DescribeInstancesResult response = ec2.describeInstances(request);

        List<Reservation> reservations = response.getReservations();
        Reservation reservation = reservations.get(0);

        List<Instance> instances = reservation.getInstances();
        Instance instance = instances.get(0);

        String imageId = instance.getImageId();
        String instanceType = instance.getInstanceType();
        String state = instance.getState().getName();
        List<GroupIdentifier> groups = instance.getSecurityGroups();
        String privateDnsName = instance.getPrivateDnsName();
        String privateIpAddress = instance.getPrivateIpAddress();
        String publicDnsName = instance.getPublicDnsName();
        String publicIpAddress = instance.getPublicIpAddress();
        List<Tag> tags = instance.getTags();

        System.out.println("Instance Id:       " + instanceId);
        System.out.println("Image Id:          " + imageId);
        System.out.println("Instance Type:     " + instanceType);
        System.out.println("Security Groups:   " + groups);
        for (int i = 0; i < groups.size(); i++) {
            System.out.println("Security Group:    " + groups.get(i));
        }
        System.out.println("State:             " + state);
        System.out.println("Private DNS Name:  " + privateDnsName);
        System.out.println("Private IP Name:   " + privateIpAddress);
        System.out.println("Public DNS Name:   " + publicDnsName);
        System.out.println("Public IP Name:    " + publicIpAddress);
        System.out.println("Tags:              " + tags);
        for (int i = 0; i < tags.size(); i++) {
            System.out.println("Tags:              " + tags.get(i));
        }
        System.out.println();
    }


    /**
     * Start an EC2 instance
     */
    public static void startInstance(String instanceId) {

        if (instanceId == null || instanceId.isEmpty()) {
            System.out.println("Error: NO Instance.");
            return;
        }

        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                                    .withRegion(REGION).build();

        System.out.println("Starting EC2 instance ...");

        StartInstancesRequest request = new StartInstancesRequest()
                .withInstanceIds(instanceId);

        ec2.startInstances(request);
    }


    /**
     * Stop an EC2 instance
     */
    public static void stopInstance(String instanceId) {

        if (instanceId == null || instanceId.isEmpty()) {
            System.out.println("Error: NO Instance.");
            return;
        }

        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                                    .withRegion(REGION).build();

        System.out.println("Stopping EC2 instance ...");

        StopInstancesRequest request = new StopInstancesRequest()
                .withInstanceIds(instanceId);

        ec2.stopInstances(request);
    }


    /**
     * Reboot an EC2 instance
     */
    public static void rebootInstance(String instanceId) {

        if (instanceId == null || instanceId.isEmpty()) {
            System.out.println("Error: NO Instance.");
            return;
        }

        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                                .withRegion(REGION).build();

        System.out.println("Rebooting EC2 instance ...");

        RebootInstancesRequest request = new RebootInstancesRequest()
                .withInstanceIds(instanceId);

        RebootInstancesResult response = ec2.rebootInstances(request);
    }


    /**
     * Terminate an EC2 instance
     */
    public static void terminateInstance(String instanceId) {

        if (instanceId == null || instanceId.isEmpty()) {
            System.out.println("Error: NO Instance.");
            return;
        }

        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                                    .withRegion(REGION).build();

        System.out.println("Terminating EC2 instance ...");

        TerminateInstancesRequest request = new TerminateInstancesRequest()
                .withInstanceIds(instanceId);

        TerminateInstancesResult response = ec2.terminateInstances(request);
    }


    /**
     * Wait some milliseconds
     */
    private static void wait(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            // swallow
        }
    }


    /**
     * Create a tag attached an EC2 instance
     */
    private static void tagInstance(AmazonEC2 ec2, String instanceId, String tagName, String value) {
        CreateTagsRequest tagRequest = new CreateTagsRequest()
                .withResources(instanceId)
                .withTags(new Tag(tagName, value));
        ec2.createTags(tagRequest);
    }


    /**
     * Create some tags attached EC2 instances
     */
    private static void tagResources(AmazonEC2 ec2, List<String> resources, List<Tag> tags) {
        // Create a tag request.
        CreateTagsRequest createTagsRequest = new CreateTagsRequest();
        createTagsRequest.setResources(resources);
        createTagsRequest.setTags(tags);

        // Try to tag the tag request submitted
        try {
            ec2.createTags(createTagsRequest);
        } catch (AmazonServiceException e) {
            // Write out any exceptions that may have occurred.
            System.out.println("Error terminating instances");
            System.out.println("Caught Exception:     " + e.getMessage());
            System.out.println("Response Status Code: " + e.getStatusCode());
            System.out.println("Error Code:           " + e.getErrorCode());
            System.out.println("Request ID:           " + e.getRequestId());
        }
    }
}

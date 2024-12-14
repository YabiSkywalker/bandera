package com.example.bandera;


import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class NotificationService {
    private final String Topic_Arn = "/////YOUR TOPIC ARN HERE";

    public void sendNotification(String message) {
        try (SnsClient snsClient = SnsClient.builder().region(Region.US_EAST_1).build()) {
            System.out.println("Publishing to Topic ARN: " + Topic_Arn);

            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .topicArn(Topic_Arn)
                    .build();

            PublishResponse response = snsClient.publish(request);
            System.out.println("Message published successfully! Message ID: " + response.messageId());
        } catch (Exception e) {
            System.err.println("Error publishing to SNS: " + e.getMessage());
        }
    }
}



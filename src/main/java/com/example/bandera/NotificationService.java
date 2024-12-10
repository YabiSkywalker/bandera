package com.example.bandera;

/*
public class NotificationService {
    private final String Topic_Arn = "arn:aws:sns:us-east-1:897105211357:TicketNotifications";

    public void sendNotification(String message) {
        try (SnsClient snsClient = SnsClient.create()) {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .topicArn(Topic_Arn)
                    .build();

            snsClient.publish(request);
            snsClient.close();

            PublishResponse response = snsClient.publish(request);
            System.out.println("Message ID: " + response.messageId());

        } catch (Exception e) {
            System.err.println("Error publishing to SNS: " + e.getMessage());
        }
    }
}

 */

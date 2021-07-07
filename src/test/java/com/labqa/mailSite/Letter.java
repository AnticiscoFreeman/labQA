package com.labqa.mailSite;

import java.util.Random;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 07.07.2021
 */

public class Letter {

    private String receiverEmail;
    private String subject;
    private StringBuilder body;

    public Letter(String receiverEmail, String subject, int count) {
        this.receiverEmail = receiverEmail;
        this.subject = subject;
        generateRandomText(count);
    }

    private void generateRandomText(int count) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        body = new StringBuilder(count);
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            char c = chars[random.nextInt(chars.length)];
            body.append(c);
        }
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body.toString();
    }
}

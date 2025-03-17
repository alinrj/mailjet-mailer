package com.mailer.mailermailjet.service;

import com.mailer.mailermailjet.dto.EmailDataDto;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailjetService {
    @Value("${mailjet.api.key}")
    private String apiKey;

    @Value("${mailjet.secret.key}")
    private String apiSecretKey;

    @Value("${mailjet.sender.email}")
    private String senderEmail;
    public void sendEmail(EmailDataDto emailDataDto) throws MailjetException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;

        client = new MailjetClient(apiKey, apiSecretKey);
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", senderEmail)
                                        .put("Name", "Mailer System"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", emailDataDto.getReceiverEmail())
                                                .put("Name", "Receiver")))
                                .put(Emailv31.Message.TEMPLATEID, emailDataDto.getTemplateId())  // Replace with your Mailjet Template ID
                                .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                                .put(Emailv31.Message.SUBJECT, "Mesaj nou")
                                .put("Variables", new JSONObject(emailDataDto.getParameters()))
                        )
                );
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}

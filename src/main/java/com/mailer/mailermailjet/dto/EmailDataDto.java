package com.mailer.mailermailjet.dto;

import lombok.Data;

import java.util.Map;

@Data
public class EmailDataDto {
    String receiverEmail;
    Integer templateId;
    Map<String, String> parameters;
}

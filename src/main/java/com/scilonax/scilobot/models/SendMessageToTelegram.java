package com.scilonax.scilobot.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SendMessageToTelegram {

    @Autowired
    private RestTemplate restTemplate;


    public void sendMessage(String response){

        String url = "https://api.telegram.org/bot" + System.getenv("botToken") + "/sendMessage";
        MultiValueMap<String,Object> parts = new LinkedMultiValueMap<>();
        parts.add("chat_id", 608316978);
        parts.add("text", response);
        restTemplate.postForObject(url, parts, String.class);
    }
}

package com.scilonax.scilobot.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ScheduledTasks {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SendMessageToTelegram sendMessageToTelegram;

    @Scheduled(cron = "* */5 * * * *")
    public void keepAlive(){
        String url = "https://scilobot.herokuapp.com/";
        MultiValueMap<String,Object> parts = new LinkedMultiValueMap<>();
        restTemplate.getForEntity(url, String.class);
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void callMITScraper(){
        System.out.println("I'll start running MIT news right now, don't stop me now!");
        String command = "python mit_news.py";
        try{
            Process mitCall = Runtime.getRuntime().exec(command);

            BufferedReader reader =
                    new BufferedReader((new InputStreamReader(mitCall.getInputStream())));
            StringBuilder builder = new StringBuilder();
            String line = null;
            builder.append(reader.readLine());
            String result = builder.toString();

            if(result != null){
                String response = "Hi Scilonax, it seems MIT posted some news about Machine Learning, here it is:\n" + result;
                sendMessageToTelegram.sendMessage(response);
            }
            System.out.println("I've finished with python");

        }catch(IOException io){
            io.printStackTrace();
        }


    }

}

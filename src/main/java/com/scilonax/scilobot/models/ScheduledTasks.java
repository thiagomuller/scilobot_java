package com.scilonax.scilobot.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduledTasks {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DBhandler dbHandler;

    @Autowired
    private SendMessageToTelegram sendMessageToTelegram;

    @Scheduled(cron = "0 */5 * * * *")
    public void keepAlive(){
        String url = "https://scilobot.herokuapp.com/keep-alive";
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
            while((line = reader.readLine()) != null){
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }

            String result = builder.toString().trim();

            if(!result.equals("null")){
                Map<String, List<String>> myMap = new HashMap<String, List<String>>();
                ObjectMapper mapper = new ObjectMapper();
                myMap = mapper.readValue(result, new TypeReference<Map<String,List<String>>>(){});
                List<String> urls = myMap.get("urls");

                urls.forEach(url ->{
                    if(dbHandler.handleUrlOnDB(url)){
                        String response = "Hi Scilonax, it seems MIT posted some news about Machine Learning, here it is:\n" + result;
                        sendMessageToTelegram.sendMessage(url);
                    }
                });
            }

            System.out.println("I've finished with python");

        }catch(IOException io){
            io.printStackTrace();
        }
    }

}

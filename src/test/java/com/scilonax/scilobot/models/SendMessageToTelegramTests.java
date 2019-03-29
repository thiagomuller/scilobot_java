package com.scilonax.scilobot.models;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMessageToTelegramTests {

    @Mock
    private CloseableHttpClient client;

    @InjectMocks
    private SendMessageToTelegram sendMessageToTelegram;

    @Test
    public void callsSendMessaqeToTelegram(){
        List<String> responses = new ArrayList<>();
        responses.addAll(Arrays.asList("Hi there, I'm Scilobot! Let's make some science today?", "https://media1.tenor.com/images/1701ca955ab813437e4457667c980123/tenor.gif?itemid=12528285",
                "https://media.giphy.com/media/jlfulL2NK1D2M/giphy.gif", "That's obvious, it's 42!!!"));
        responses.forEach(response -> {
            sendMessageToTelegram.sendMessage(response);
            try{
                Mockito.verify(client, Mockito.atLeastOnce()).execute(Mockito.any(HttpPost.class));
                Mockito.verify(client, Mockito.atLeastOnce()).close();

            }catch(IOException io){
                io.printStackTrace();
            }
        });
    }

}

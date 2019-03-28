package com.scilonax.scilobot.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageHandler {
    @Autowired
    private SendMessageToTelegram sendMessageToTelegram;

    public void handle(TelegramUpdate telegramUpdate){
        if (telegramUpdate.getMessageText().toLowerCase().equals("hi") | telegramUpdate.getMessageText().toLowerCase().equals("hi there")){
            sendMessageToTelegram.sendMessageToTelegram("Hi there, I'm Scilobot! Let's make some science today?");
        }

        List<String> patterns = new ArrayList<>();

        patterns.addAll(Arrays.asList(".*(gl)+(ó|o)+(ria)+.*" , ".*((carl)|(carlinhos))+.*((saga)+(s|z|n)+)+.*" , "(qual)+.*o+.*(sentido)+.*(da)+.*(vida)+.*\\\\?+", ".*(mind)+.*(blowing)+"));


        patterns.forEach(pattern ->{
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(telegramUpdate.getMessageText().toLowerCase());
            System.out.println(pattern);
            System.out.println(telegramUpdate.getMessageText().toLowerCase());

            if(pattern.equals(".*(gl)+(ó|o)+(ria)+.*")){
                if(m.find()){
                    sendMessageToTelegram.sendMessageToTelegram("https://media1.tenor.com/images/1701ca955ab813437e4457667c980123/tenor.gif?itemid=12528285");
                }
            }else if(pattern.equals(".*((carl)|(carlinhos))+.*((saga)+(s|z|n)+)+.*")){
                if(m.find()){
                    sendMessageToTelegram.sendMessageToTelegram("https://media.giphy.com/media/jlfulL2NK1D2M/giphy.gif");
                }
            }else if(pattern.equals("(qual)+.*o+.*(sentido)+.*(da)+.*(vida)+.*\\\\?+")){
                if(m.find()){
                    sendMessageToTelegram.sendMessageToTelegram("That's obvious, it's 42!!!");
                }
            } else if(pattern.equals(".*(mind)+.*(blowing)+")){
                if(m.find()){
                    sendMessageToTelegram.sendMessageToTelegram("https://i.gifer.com/EsVg.gif");
                }
            }
            
        });


    }
}

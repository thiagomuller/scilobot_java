package com.scilonax.scilobot.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageHandlerTests {

    @Mock
    private TelegramUpdate telegramUpdate;

    @Mock
    private SendMessageToTelegram sendMessageToTelegram;

    @InjectMocks
    private MessageHandler messageHandler;


    @Test
    public void whenHandlerGetsCalledWithHi(){
        List<String> texts = new ArrayList<>();
        texts.addAll(Arrays.asList("Hi" , "hi" , "Hi there" , "hi there"));
        texts.forEach(text -> {
            when(telegramUpdate.getMessageText()).thenReturn(text);
            messageHandler.handle(telegramUpdate);
            verify(sendMessageToTelegram,atLeastOnce()).sendMessageToTelegram("Hi there, I'm Scilobot! Let's make some science today?");
        });
    }

    @Test
    public void whenHandlerGetsCalledWithGloria(){
        List<String> texts = new ArrayList<>();
        texts.addAll(Arrays.asList("Gloria","gloria", "Glória", "glória"));
        texts.forEach(text -> {
            when(telegramUpdate.getMessageText()).thenReturn(text);
            messageHandler.handle(telegramUpdate);
            verify(sendMessageToTelegram,atLeastOnce()).sendMessageToTelegram("https://media1.tenor.com/images/1701ca955ab813437e4457667c980123/tenor.gif?itemid=12528285");
        });
    }

    @Test
    public void whenHandlerGetsCalledWithCarlSagan(){
        List<String> texts = new ArrayList<>();
        texts.addAll(Arrays.asList("Carl Sagan", "carl sagan", "carlinhos sagaz", "Carlinhos Sagaz", "Carlinhos sagaz", "carlinho Sagaz"));
        texts.forEach(text -> {
            when(telegramUpdate.getMessageText()).thenReturn(text);
            messageHandler.handle(telegramUpdate);
            verify(sendMessageToTelegram,atLeastOnce()).sendMessageToTelegram("https://media.giphy.com/media/jlfulL2NK1D2M/giphy.gif");
        });


    }

    @Test
    public void whenHandlerGetsCalledWithSentidoDaVida(){
        List<String> texts = new ArrayList<>();
        texts.addAll(Arrays.asList("qual o sentido da vida", "Qual o sentido da vida", "sentido da vida", "Sentido da vida"));
        texts.forEach(text -> {
            when(telegramUpdate.getMessageText()).thenReturn(text);
            messageHandler.handle(telegramUpdate);
            verify(sendMessageToTelegram,atLeastOnce()).sendMessageToTelegram("That's obvious, it's 42!!!");
        });
    }

}

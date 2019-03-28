package com.scilonax.scilobot.controllers;

import com.scilonax.scilobot.models.MessageHandler;
import com.scilonax.scilobot.models.TelegramUpdate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageController.class)
public class MessageControllerTests {
    @MockBean
    private MessageHandler messageHandler;

    @Autowired
    private MessageController messageController;

    @MockBean
    private TelegramUpdate telegramUpdate;


    @Test
    public void testIfMessageControllerCallsMessageHandlerService(){
        messageController.receiveTelegramUpdate(telegramUpdate);
        verify(messageHandler, atLeastOnce()).handle(telegramUpdate);
    }
}

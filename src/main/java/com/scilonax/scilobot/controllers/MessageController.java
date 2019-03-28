package com.scilonax.scilobot.controllers;

import com.scilonax.scilobot.models.MessageHandler;
import com.scilonax.scilobot.models.TelegramUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {
    private MessageHandler messageHandler;

    @Autowired
    public MessageController(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @RequestMapping(value = "/")
    public void receiveTelegramUpdate(TelegramUpdate telegramUpdate){
        messageHandler.handle(telegramUpdate);
    }
}

package com.scilonax.scilobot.controllers;

import com.scilonax.scilobot.models.MessageHandler;
import com.scilonax.scilobot.models.TelegramUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {
    private MessageHandler messageHandler;

    @Autowired
    public MessageController(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public String receiveTelegramUpdate(@RequestBody TelegramUpdate telegramUpdate){
        messageHandler.handle(telegramUpdate);
        return "OK";
    }
}

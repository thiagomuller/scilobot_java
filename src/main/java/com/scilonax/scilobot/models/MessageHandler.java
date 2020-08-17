package com.scilonax.scilobot.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.optimaize.langdetect.DetectedLanguage;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.profiles.LanguageProfile;

@Service
public class MessageHandler {
    @Autowired
    private SendMessageToTelegram sendMessageToTelegram;

    public void handle(TelegramUpdate telegramUpdate) {

        if (telegramUpdate.getMessageText() != null) {
            int chatId = telegramUpdate.getMessage().getChat().getId();
            handleEoz(telegramUpdate.getMessageText(), chatId);
            if (telegramUpdate.getMessageText().toLowerCase().equals("hi")
                    || telegramUpdate.getMessageText().toLowerCase().equals("hi there")) {
                sendMessageToTelegram.sendMessage("Hi there, I'm Scilobot! Let's make some science today?", chatId);
            } else if (Pattern.compile(".*(gl)+(ó|o)+(ria)+.*").matcher(telegramUpdate.getMessageText().toLowerCase())
                    .find()) {
                sendMessageToTelegram.sendMessage(
                        "https://media1.tenor.com/images/1701ca955ab813437e4457667c980123/tenor.gif?itemid=12528285",
                        chatId);
            } else if (Pattern.compile(".*((carl)|(carlinhos))+.*((saga)+(s|z|n)+)+.*")
                    .matcher(telegramUpdate.getMessageText().toLowerCase()).find()) {
                sendMessageToTelegram.sendMessage("https://media.giphy.com/media/jlfulL2NK1D2M/giphy.gif", chatId);
            } else if (Pattern.compile("(qual)+.*o+.*(sentido)+.*(da)+.*(vida)+.*?")
                    .matcher(telegramUpdate.getMessageText().toLowerCase()).find()) {
                sendMessageToTelegram.sendMessage("That's obvious, it's 42!!!", chatId);
            } else if (Pattern.compile(".*(mind)+.*(blowing)+").matcher(telegramUpdate.getMessageText().toLowerCase())
                    .find()) {
                sendMessageToTelegram.sendMessage("https://i.gifer.com/EsVg.gif", chatId);
            }
        }
    }

    public void handleEoz(String message, int chatId) {
        // load all languages:
        try {
            List<String> languages = new ArrayList<>();
            languages.add("en");
            languages.add("pt");
            List<LanguageProfile> languageProfiles = new LanguageProfileReader().read(languages);
            // build language detector:
            LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                    .withProfiles(languageProfiles).build();

            // query:
            List<DetectedLanguage> langs = languageDetector.getProbabilities(message);
            if (!langs.get(0).getLocale().getLanguage().equals("en")) {
                sendMessageToTelegram.sendMessage("In this group we should only speak in english! :)", chatId);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

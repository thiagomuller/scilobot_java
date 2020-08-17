package com.scilonax.scilobot.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
            handleEoz(telegramUpdate.getMessageText());
            if (telegramUpdate.getMessageText().toLowerCase().equals("hi")
                    || telegramUpdate.getMessageText().toLowerCase().equals("hi there")) {
                sendMessageToTelegram.sendMessage("Hi there, I'm Scilobot! Let's make some science today?");
            } else if (Pattern.compile(".*(gl)+(ó|o)+(ria)+.*").matcher(telegramUpdate.getMessageText().toLowerCase())
                    .find()) {
                sendMessageToTelegram.sendMessage(
                        "https://media1.tenor.com/images/1701ca955ab813437e4457667c980123/tenor.gif?itemid=12528285");
            } else if (Pattern.compile(".*((carl)|(carlinhos))+.*((saga)+(s|z|n)+)+.*")
                    .matcher(telegramUpdate.getMessageText().toLowerCase()).find()) {
                sendMessageToTelegram.sendMessage("https://media.giphy.com/media/jlfulL2NK1D2M/giphy.gif");
            } else if (Pattern.compile("(qual)+.*o+.*(sentido)+.*(da)+.*(vida)+.*?")
                    .matcher(telegramUpdate.getMessageText().toLowerCase()).find()) {
                sendMessageToTelegram.sendMessage("That's obvious, it's 42!!!");
            } else if (Pattern.compile(".*(mind)+.*(blowing)+").matcher(telegramUpdate.getMessageText().toLowerCase())
                    .find()) {
                sendMessageToTelegram.sendMessage("https://i.gifer.com/EsVg.gif");
            }
        }
    }

    public void handleEoz(String message) {
        // load all languages:
        try {
            List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();
            // build language detector:
            LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                    .withProfiles(languageProfiles).build();

            // query:
            List<DetectedLanguage> langs = languageDetector.getProbabilities(message);
            if (langs.get(0).getLocale().getLanguage() != "en") {
                sendMessageToTelegram.sendMessage("In this group we should only speak in english! :)");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

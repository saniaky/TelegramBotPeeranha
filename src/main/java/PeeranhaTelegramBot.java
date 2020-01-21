import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PeeranhaTelegramBot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            // Set variables
            String text = update.getMessage().getText();

            if (text.toLowerCase().contains("how")) {
                String userFirstName = update.getMessage().getChat().getFirstName();
                String userLastName = update.getMessage().getChat().getLastName();
                String userUsername = update.getMessage().getChat().getUserName();
                String botAnswer = String.format("Hi there! You asked\n\"%s\"\n " +
                                "I don't know the answer, but you can check our website: " +
                                "https://peeranha.io/faq/", text);
                long chatId = update.getMessage().getChatId();

                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chatId)
                        .setText(botAnswer);

                log(userFirstName, userLastName, userUsername, text, botAnswer);

                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return EnvVarUtil.getVar("telegram_bot_name");
    }

    @Override
    public String getBotToken() {
        return EnvVarUtil.getVar("telegram_bot_token");
    }

    private void log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }

}

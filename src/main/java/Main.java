import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {
        System.out.printf("Starting '%s' bot...\n", EnvVarUtil.getVar("telegram_bot_name"));

        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new PeeranhaTelegramBot());
            System.out.println("Telegram bot started.");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}

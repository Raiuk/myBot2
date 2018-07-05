import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    Cafe cafe = new Cafe("cafe");
    Thread c = new Thread(cafe);

    Bot() {

    }

    public static void main(String[] args) {


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message != null && message.hasText()) {
            if(message.getText().equals("/help")) {
                sendMsg(message, "Hi, i'm test bot!");
            }
            else if(message.getText().equals("/queue")) {
                try {
                    //c.sleep(10);
                    sendMsg(message, cafe.tableList.get(0).ordersQueue.get(0).toString());
                    Order o = new Order();
                    Client cl = new Client();
                    o.testTime = 6;
                    o.client = cl;
                    cafe.tableList.get(0).ordersQueue.add(o);
                    //c.notifyAll();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
                sendMsg(message, "I'm test robot");
        }
    }

    private  void sendMsg(Message message, String s) {
        SendMessage sendMessage = new SendMessage();
        //sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(s);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "SelecterBot";
    }

    @Override
    public String getBotToken() {
        return "614545720:AAEPKyxx0t_Sc4Luo5I_77gH_xU1LGc5hsY";
    }
}

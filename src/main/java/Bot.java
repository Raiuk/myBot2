import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    Cafe cafe = new Cafe("cafe");


    public static void main(String[] args) {


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message != null && message.hasText()) {
            if(message.getText().equals("/help")) {
                sendMsg(message, "Sorry but no one will help you.");
            }
            else if(message.getText().equals("/info")) {
                //information about queues will be here
            }
            else if(message.getText().equals("/show")) {
                sendMsg(message, cafe.getCurrentTask());
            }
            else if(message.getText().equals("/leave")) {
                //leave the queue
                int place = cafe.placeInQueue(message.getChat().getFirstName() + " " + message.getChat().getLastName());
                if(place == -1) {
                    sendMsg(message, "You have no place in the queue.");
                }
                else cafe.removeOrder(place);

            }
            else if(message.getText().equals("/queue")) {
                int place = cafe.placeInQueue(message.getChat().getFirstName() + " " + message.getChat().getLastName());
                if(place == -1) {
                    try {
                        //sendMsg(message, cafe.tableList.get(0).ordersQueue.get(0).toString());
                        Order o = new Order();
                        Client cl = new Client();

                        cl.setName(message.getChat().getFirstName() + " " + message.getChat().getLastName());
                        cl.setPhoneNumber(message.getChatId() + "");
                        o.testTime = 6;
                        o.client = cl;
                        cafe.tableList.get(0).ordersQueue.add(o);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                else sendMsg(message, "You are " + place + " in the queue.");
            }
            else if(message.getText().equals("/place")) {
                sendMsg(message, "You are " + cafe.placeInQueue(message.getChat().getFirstName() + " "
                                                + message.getChat().getLastName()) + " in the queue.");
            }
            else
                sendMsg(message, Constants.BOT_COMMANDS);
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

    public String getBotUsername() {
        return Constants.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Constants.BOT_TOKEN;
    }
}

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();//инициальизация апи
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();//создаем объект
        try {                          //регистрация бота
            telegramBotsApi.registerBot(new Bot());

        }
        catch (TelegramApiException e){ //в случае ошибки
        e.printStackTrace();
        }
    }

    public void sendMsg(Message message , String text){//ответ на сообщение
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());//чтобы знать кому отвечать
        sendMessage.setReplyToMessageId(message.getMessageId());//на какое именно сообщение ответить
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) { //прием сообщений
        Message message = update.getMessage(); //в объекте будет текст из сообщений
        if (message !=null && message.hasText()){
            switch (message.getText()){
                case "привет":
                     sendMsg(message,"Привет!");
                    break;
                default:
            }
        }
    }

    @Override
    public String getBotUsername() {//для возврата имя боту при регистрации
        return "StreetGames_bot";
    }

    @Override
    public String getBotToken() {
        return "1792487133:AAHuD2sGpC2JnD8tnNgIPgQTt-b2qRmojV4";
    }
}

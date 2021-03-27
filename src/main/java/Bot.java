import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

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
            setButton(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setButton(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();//клавиатура
        sendMessage.setReplyMarkup(replyKeyboardMarkup);//разметка клавиатуры
        replyKeyboardMarkup.setSelective(true);//вывод всем
        replyKeyboardMarkup.setResizeKeyboard(true);//количество кнопок больше или меньше
        replyKeyboardMarkup.setOneTimeKeyboard(false);//скрывать или нет кнопки после нажатия
        //создание самой кнопки
        List<KeyboardRow> keyboardRowList = new ArrayList<>();//создаем лист кнопок
        KeyboardRow keyboardFirstRow = new KeyboardRow();//первая строка кнопок

        keyboardFirstRow.add(new KeyboardButton("привет"));

        keyboardRowList.add(keyboardFirstRow);//добавляем все строчки в список
        replyKeyboardMarkup.setKeyboard(keyboardRowList);//устанавливаем список

    }

    @Override
    public void onUpdateReceived(Update update) { //прием сообщений
        Message message = update.getMessage(); //в объекте будет текст из сообщений
        if (message !=null && message.hasText()){
            switch (message.getText()){
                case "привет":
                     sendMsg(message,"Привет!");
                    break;
                case "пока":
                    sendMsg(message,"Пока!");
                case "Кто я?":
                    sendMsg(message,"Ты ходячий секс)");
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

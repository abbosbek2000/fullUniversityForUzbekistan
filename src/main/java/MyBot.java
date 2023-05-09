import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "SpringAcademyBot";
    }

    @Override
    public String getBotToken() {
        return "5829081396:AAHz8gJi23dV2lGVAqcYH0qsRXhfPXj9UQo";
    }
    // 26 qatorda o'zgarish boldi


    ///
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {

            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals("/start")) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText("Iltimos raqamingizni yuboring ");
                    sendMessage.setChatId(message.getChatId());
                    KeyboardButton contactButton = new KeyboardButton();
                    contactButton.setRequestContact(true);
                    contactButton.setText("Share Contact");
                    KeyboardRow keyboardRow = new KeyboardRow();
                    keyboardRow.add(contactButton);
                    List<KeyboardRow> list = new LinkedList<>();
                    list.add(keyboardRow);
                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(list);
                    replyKeyboardMarkup.setResizeKeyboard(true);

                    sendMessage.setReplyMarkup(replyKeyboardMarkup);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (message.hasContact()) {
                Contact contact = message.getContact();
                String phoneNumber = contact.getPhoneNumber();
                if (phoneNumber.equals("+998943667299")) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText("Marhamat botimizdan foydalanishingiz mumkin");
                    sendMessage.setChatId(message.getChatId());
                    InlineKeyboardMarkup inlineKeyboardMarkup =
                            new InlineKeyboardMarkup();
                    InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                    inlineKeyboardButton.setCallbackData("add_university");
                    inlineKeyboardButton.setText("ADD_UNIVERSITY");

                    List<InlineKeyboardButton> inlineKeyboardButtonList =
                            new ArrayList<>();
                    inlineKeyboardButtonList.add(inlineKeyboardButton);

                    List<List<InlineKeyboardButton>> collectionRow = new ArrayList<>();
                    collectionRow.add(inlineKeyboardButtonList);

                    inlineKeyboardMarkup.setKeyboard(collectionRow);

                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(contact.getFirstName() + " akahon birinchi pul to'lang keyin foydalansiz");
                    sendMessage.setChatId(message.getChatId());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            if (callbackQuery.getData().equals("add_university")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText("Universitetizni qo'shishiz mumkin");
                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

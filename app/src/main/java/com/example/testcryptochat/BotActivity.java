package com.example.testcryptochat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;

public class BotActivity extends AppCompatActivity {

    Button contacts;
    Button library;
    Button openSite;
    Button myTasks;
    Button mySchedule;
    Button moneyInfo;
    RecyclerView chatWindow;
    MessageController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        getSupportActionBar().setTitle("Чат-бот");

        controller = new MessageController();
        contacts = findViewById(R.id.contacts);
        library = findViewById(R.id.library);
        openSite = findViewById(R.id.openSite);
        myTasks = findViewById(R.id.myTasks);
        mySchedule = findViewById(R.id.mySchedule);
        moneyInfo = findViewById(R.id.buttonMoneyInfo);
        chatWindow = findViewById(R.id.chatBotWindow);
        controller.setIncomingLayout(R.layout.message_bot);
        controller.setOutgoingLayout(R.layout.outgoing_message);
        controller.setMessageTextId(R.id.messageText);
        controller.setUserNameId(R.id.userName);
        controller.setMessageTimeId(R.id.messageDate);
        controller.appendTo(chatWindow, this);

        controller.addMessage(
                new MessageController.Message("Привет, я твой бот (BotName) и я помогу получить тебе информацию. " +
                        "Выбери, какая информация тебе необходима. ", "Bot", false)

        );

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addMessage(
                        new MessageController.Message("Контакты обратной связи: \n" +
                                "Номер телефона: +79600537639\n" +
                                "E-mail: same7dov2@gmail.com \n" +
                                "Git: https://github.com/NeoGiotto\n"
                                , "Bot", false)
                );
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addMessage(
                        new MessageController.Message("Библиотка инстуркций: \n" +
                                "1. Здесь техника безопасности\n" +
                                "2. Здесь инструкция использования оборудования \n" +
                                "3. Здесь информация о показателях датчиков\n" +
                                "4. Здесь инструкция написаня отчётов\n" +
                                "5. Здесь инструкция по обработке кожи и защите органов дыхания\n" +
                                "6. Здесь список нарушений, за которые предусмотрены штрафы\n"
                                , "Bot", false)
                );
            }
        });
        myTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addMessage(
                        new MessageController.Message("Ваши задачи: \n" +
                                "1. Подготовить оборудование\n" +
                                "2. Использовать средства защиты \n" +
                                "3. Првоести работу над химическими веществами\n" +
                                "4. Убрать рабочее место, обрботать всё отчищающими средствами\n" +
                                "5. Одежду и средства защиты отправить на санитарную обработку\n" +
                                "6. Написать отчёт о проделанной работе\n", "Bot", false)
                );
            }
        });
        mySchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addMessage(
                        new MessageController.Message("Рассписание: \n" +
                                "Понедельник: С 8:00 до 17:00, перерыв с 12:00 до 13:30\n" +
                                "Вторник: С 8:00 до 17:00, перерыв с 12:00 до 13:30 \n" +
                                "Среда: С 8:00 до 17:00, перерыв с 12:00 до 13:30\n" +
                                "Четверг: С 9:00 до 15:00, перерыв с 12:00 до 13:00\n" +
                                "Пятница: С 9:00 до 15:00, перерыв с 12:00 до 13:00\n" +
                                "Суббота: Выходной\n" +
                                "Воскресенье: Выходной\n", "Bot", false)
                );
            }
        });
        openSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addMessage(
                        new MessageController.Message("Наш сайт: www.домен.com", "Bot", false)
                );
            }
        });
        moneyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addMessage(
                        new MessageController.Message("Ваша ЗП: 25000р/месяц", "Bot", false)
                );
            }
        });

    }

}

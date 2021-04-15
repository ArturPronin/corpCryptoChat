package com.example.testcryptochat.Fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testcryptochat.MessageController;
import com.example.testcryptochat.R;
import com.example.testcryptochat.Server;

public class Addfragment extends Fragment {
    Button sendButton;
    EditText messageText;
    RecyclerView chatWindow;
    MessageController controller;
    Server server;
    String userName;

    public void getUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Введите ваше имя");
        final EditText nameInput = new EditText(getActivity());
        builder.setView(nameInput);

            builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                        userName = nameInput.getText().toString();
                        server.sendUserName(userName);
                }
            });
        builder.show();
        }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getUserName();

        controller = new MessageController();
        controller.setIncomingLayout(R.layout.message);
        controller.setOutgoingLayout(R.layout.outgoing_message);
        controller.setMessageTextId(R.id.messageText);
        controller.setUserNameId(R.id.userName);
        controller.setMessageTimeId(R.id.messageDate);




        server = new Server(new Consumer<Pair<String, String>>() {
            @Override
            public void accept(final Pair<String, String> p) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        controller.addMessage(
                                new MessageController.Message(p.second, p.first, false)
                        );
                    }
                });
            }
        },
                new Consumer<Integer>() {
                    @Override
                    public void accept(final Integer usersAmount) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getActivity().setTitle("Пользователей онлайн: " + usersAmount);
                            }
                        });
                    }
                },
                new Consumer<String>() {
                    @Override
                    public void accept(final String userName) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), userName + " подключился к чату!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
        );

        server.connect();

    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_chat, container, false);

        sendButton = view.findViewById(R.id.sendButton);
        messageText =  view.findViewById(R.id.messageText);
        chatWindow =  view.findViewById(R.id.chatWindow);
        //actionBar = getSupportActionBar();
        //context = getApplicationContext();

        controller.appendTo(chatWindow, getActivity());

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInput = messageText.getText().toString();
                if (TextUtils.isEmpty(messageText.getText())) {
                    Toast.makeText(getContext(), "Пожалуйста, введите сообщение!", Toast.LENGTH_SHORT).show();

                } else {
                    controller.addMessage(
                            new MessageController.Message(userInput, userName, true)
                    );
                    server.sendMessage(userInput);
                    messageText.setText("");
                }
            }

        });

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.d("Addfragment", "onAttach");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("Addfragment", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d("Addfragment", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("Addfragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d("Addfragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d("Addfragment", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("Addfragment", "onDestroyView");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("Addfragment", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;

        Log.d("Addfragment", "onDetach");
    }

}

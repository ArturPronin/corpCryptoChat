package com.example.testcryptochat.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testcryptochat.R;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Videofragment extends Fragment {
    View view;
    Button videoMeetButton;
    EditText conferenceName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_video_meet, null);

        //getActivity().setTitle("Видео-чат");
        videoMeetButton = view.findViewById(R.id.video_meet_button);
        conferenceName = view.findViewById(R.id.conferenceName);

        // using try catch block to handle exceptions
        try {
            // object creation of JitsiMeetConferenceOptions
            // class by the name of options
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL(""))
                    .setWelcomePageEnabled(false)
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        videoMeetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = conferenceName.getText().toString();

                if (text.length() > 0) {
                    JitsiMeetConferenceOptions options
                            = new JitsiMeetConferenceOptions.Builder()
                            .setRoom(text)
                            .build();
                    JitsiMeetActivity.launch(getContext(), options);
                }
            }
        });

        return view;
    }

    /*
    // we have declared the name of onButtonClick() method
    // in our xml file  now we are going to define it.
    public void onButtonClick(View v) {
        // initialize editText with method findViewById()
        // here editText will hold the name of room which is given by user
        EditText editText = view.findViewById(R.id.conferenceName);

        // store the string input by user in editText in
        // an local variable named text of string type
        String text = editText.getText().toString();

        // if user has typed some text in
        // EditText then only room will create
        if (text.length() > 0) {
            // creating a room using  JitsiMeetConferenceOptions class
            // here .setRoom() method will set the text in room name
            // here launch method with launch a new room to user where
            // they can invite others too.
            JitsiMeetConferenceOptions options
                    = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(text)
                    .build();
            JitsiMeetActivity.launch(getContext(), options);
        }
    } */

}

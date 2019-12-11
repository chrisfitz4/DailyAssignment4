package com.example.dailyassignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ResponderActivity extends AppCompatActivity {

    //arrays for keeping track of the speech bubbles
    private int[] textIDS;
    private int[] bubbleIDS;


    //arraylist for keeping track of the conversation
    ArrayList<String> newConversation;

    //key for getting the intent extras
    public static final String RESPONDER_ACTIVITY_KEY = "ResponderActivityKey";

    //edittext whose values get added to the array
    EditText editText;
    //imageview holds the users icon
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder);

        editText = findViewById(R.id.responder_activity_editText);

        imageView = findViewById(R.id.activity_responder_user_image);

        //Glide.with(this).load(R.drawable.speech_bubble_bottom_part).into(imageView);
        //imageView.setBackgroundColor(R.color.button_bk_color2);

//        Glide.with(this)
//                .load(getString(R.string.user2URL))
//                .into(imageView);

        Intent intent = getIntent();
        if(!intent.hasExtra(RESPONDER_ACTIVITY_KEY)){
            newConversation = new ArrayList<String>();
        }else {
            newConversation = intent.getStringArrayListExtra(RESPONDER_ACTIVITY_KEY);
        }
        if(newConversation.size()<=6) {
            textIDS = new int[]{R.id.responder_activity_textView1, R.id.responder_activity_textView2, R.id.responder_activity_textView3, R.id.responder_activity_textView4, R.id.responder_activity_textView5, R.id.responder_activity_textView6};
            bubbleIDS = new int[]{R.id.responder_activity_speechBubble1, R.id.responder_activity_speechBubble2, R.id.responder_activity_speechBubble3, R.id.responder_activity_speechBubble4, R.id.responder_activity_speechBubble5, R.id.responder_activity_speechBubble6};
        }else{
            textIDS = new int[]{R.id.responder_activity_textView2, R.id.responder_activity_textView3, R.id.responder_activity_textView4, R.id.responder_activity_textView5, R.id.responder_activity_textView6, R.id.responder_activity_textView7};
            bubbleIDS = new int[]{R.id.responder_activity_speechBubble2, R.id.responder_activity_speechBubble3, R.id.responder_activity_speechBubble4, R.id.responder_activity_speechBubble5, R.id.responder_activity_speechBubble6, R.id.responder_activity_speechBubble7};
            View view = findViewById(R.id.responder_activity_textView1);
            view.setVisibility(View.GONE);
            view = findViewById(R.id.responder_activity_speechBubble1);
            view.setVisibility(View.GONE);
        }
        writeInTextBubbles();
    }

    public void writeInTextBubbles(){
        View view;
        int i = 0;
        int k = 0;
        if(newConversation.size()>6){
            k=newConversation.size()-6;
        }
        while(k<newConversation.size()){
            view = findViewById(textIDS[i]);
            ((TextView)view).setText(newConversation.get(k));
            view.setVisibility(View.VISIBLE);
            view = findViewById(bubbleIDS[i]);
            view.setVisibility(View.VISIBLE);
            i++;
            k++;
        }

    }

    public void onClick(View view){
        String text = editText.getText().toString();
        newConversation.add(text);
        Intent intent = new Intent(this,MainActivity.class);
        intent.putStringArrayListExtra(MainActivity.MAIN_ACTIVITY_KEY,newConversation);
        startActivity(intent);
        finish();
    }
}

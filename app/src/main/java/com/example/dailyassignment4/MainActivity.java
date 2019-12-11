package com.example.dailyassignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    //arrays for keeping track of the speech bubbles
    private int[] textIDS;
    private int[] bubbleIDS;

    //arraylist for keeping track of the conversation
    ArrayList<String> newConversation;

    //string key for retrieving intents
    public static final String MAIN_ACTIVITY_KEY = "MainActivityKey";

    //edittext whose values get added to the array
    EditText editText;
    //imageView that holds the user profile pic
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.main_activity_editText);

        imageView = findViewById(R.id.activity_main_user_image_);


        imageView.setImageDrawable(getDrawable(R.drawable.speech_bubble_left));
        imageView.setBackgroundColor(getResources().getColor(R.color.button_bk_color));

//        Picasso.get().load("https://www.google.com/imgres?imgurl=http%3A%2F%2Fimages.clipartpanda.com%2Fapple-20clip-20art-nicubunu_Apple_Clipart_Free.png&imgrefurl=http%3A%2F%2Fwww.clipartpanda.com%2Fclipart_images%2Fapple-small-clipart-300pixel-191402&docid=sl-qLb1GfNPY5M&tbnid=iYliMNT2esdEiM%3A&vet=10ahUKEwjD2_Pe06zmAhXjQd8KHWTLBAEQMwhuKAMwAw..i&w=300&h=300&bih=594&biw=1280&q=small%20image&ved=0ahUKEwjD2_Pe06zmAhXjQd8KHWTLBAEQMwhuKAMwAw&iact=mrc&uact=8")
//                .placeholder(R.drawable.speech_bubble_bottom_part)
//                .error(R.drawable.speech_bubble_bottom_part).into(imageView);
        Intent intent = getIntent();
        if(!intent.hasExtra(MAIN_ACTIVITY_KEY)){
            newConversation = new ArrayList<String>();
        }else {
            newConversation = intent.getStringArrayListExtra(MAIN_ACTIVITY_KEY);
        }
        textIDS = new int[]{R.id.main_activity_textView1,R.id.main_activity_textView2,R.id.main_activity_textView3,R.id.main_activity_textView4,R.id.main_activity_textView5,R.id.main_activity_textView6};
        bubbleIDS = new int[]{R.id.main_activity_speechBubble1,R.id.main_activity_speechBubble2,R.id.main_activity_speechBubble3,R.id.main_activity_speechBubble4,R.id.main_activity_speechBubble5,R.id.main_activity_speechBubble6};
        writeInTextBubbles();



//        if(haveNetworkConnection()){
//            editText.setText("you have connection you loser");
//        }
    }

//for testing if the internet is actually connected, because glide refuses to work
//    private boolean haveNetworkConnection() {
//        boolean haveConnectedWifi = false;
//        boolean haveConnectedMobile = false;
//
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
//        for (NetworkInfo ni : netInfo) {
//            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
//                if (ni.isConnected())
//                    haveConnectedWifi = true;
//            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
//                if (ni.isConnected())
//                    haveConnectedMobile = true;
//        }
//        return haveConnectedWifi || haveConnectedMobile;
//    }

    public void writeInTextBubbles(){
        View view = null;
        int i = 0;
        int k = 0;
        if(newConversation.size()>=6){
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

    public void glide(){
                Glide.with(this)
                        .load(getString(R.string.test3))
                        .into(imageView);
    }

    public void onClick(View view){
        String text = editText.getText().toString();
        newConversation.add(text);
        Intent intent = new Intent(this,ResponderActivity.class);
        intent.putStringArrayListExtra(ResponderActivity.RESPONDER_ACTIVITY_KEY,newConversation);
        startActivity(intent);
        finish();
    }
}

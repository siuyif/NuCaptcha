package com.siuyifypcaptcha.nucaptcha;

import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button1);

        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(Html.fromHtml(getString(R.string.any_text)));

        getWindow().setFormat(PixelFormat.UNKNOWN);

        //displays a video file
        VideoView mVideoView = (VideoView)findViewById(R.id.videoView1);

        //obtaining the video file and randomising it
         Uri videoPath1 = Uri.parse("android.resource://com.siuyifypcaptcha.nucaptcha/"+R.raw.video1);
         Uri videoPath2 = Uri.parse("android.resource://com.siuyifypcaptcha.nucaptcha/"+R.raw.video2);
         Uri [] videopaths = {videoPath1, videoPath2};
         Random rnd = new Random();
         Uri randomVideo = videopaths[rnd.nextInt(2)];

        mVideoView.setVideoURI(randomVideo);
        mVideoView.requestFocus();
        mVideoView.start();

        // to keep looping the video
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            public void onClick(View view) {
                try  {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                catch (Exception e) {
                    // TODO: handle exception
                }

                // To capture user's input based on the video
                String strInput;
                EditText et = (EditText)findViewById(R.id.editText1);
                strInput = et.getText().toString();

                // to check if user input is correct.
                if(strInput.equals("GFWHE")) {
                    Toast position = Toast.makeText(MainActivity.this, "Verification Successful", Toast.LENGTH_LONG);
                    position.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    position.show();
                }
                else if (strInput.equals("YSEQW")){
                    Toast position = Toast.makeText(MainActivity.this, "Verification Successful", Toast.LENGTH_LONG);
                    position.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    position.show();
                }
                else{
                    Toast position = Toast.makeText(MainActivity.this, "Incorrect Input. Please try again.", Toast.LENGTH_LONG);
                    position.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    position.show();
                    et.getText().clear();
                }
            }
        });
    }
}

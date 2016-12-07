package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.lib.player.ui;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;

/**
 * Created by jvg63 on 07/12/2016.
 */

public class MediaPlayerIntegrado extends AppCompatActivity implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;

    private EditText editText;
    private ImageButton bPlay, bPause, bStop, bLog;
    private TextView logTextView;
    private boolean pause, stop;
    private String path;
    private int savePos = 0;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.reproductor_multimedia);


        logTextView = (TextView) findViewById(R.id.Log);
        bPlay = (ImageButton) findViewById(R.id.play);
        bPlay.setOnClickListener(new View.OnClickListener() {
                                     public void onClick(View view) {
                                         if (mediaPlayer != null && !stop) {
                                             if (pause) {
                                                 mediaPlayer.start();
                                             } /*else {
                                                 playVideo();
                                             }*/
                                         } else {
                                             playVideo();
                                         }
                                     }
                                 }

        );
        bPause = (ImageButton)

                findViewById(R.id.pause);

        bPause.setOnClickListener(new View.OnClickListener()

                                  {
                                      public void onClick(View view) {
                                          if (mediaPlayer != null) {
                                              pause = true;
                                              mediaPlayer.pause();
                                          }

                                      }
                                  }

        );
        bStop = (ImageButton)

                findViewById(R.id.stop);

        bStop.setOnClickListener(new View.OnClickListener()

                                 {
                                     public void onClick(View view) {
                                         if (mediaPlayer != null) {
                                             pause = false;
                                             stop = true;
                                             mediaPlayer.stop();
                                         }
                                     }
                                 }

        );
        bLog = (ImageButton)

                findViewById(R.id.logButton);

        bLog.setOnClickListener(new View.OnClickListener()

                                {
                                    public void onClick(View view) {
                                        if (logTextView.getVisibility() == TextView.VISIBLE) {
                                            logTextView.setVisibility(TextView.INVISIBLE);
                                        } else {
                                            logTextView.setVisibility(TextView.VISIBLE);
                                        }
                                    }
                                }

        );

        showMessage("");
    }


    private final String TAG = MediaPlayerIntegrado.class.getSimpleName();

    private void showMessage(String msg) {
        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Value is: " + msg);


        logTextView.append(msg + "\n");
    }


    private void playVideo() {
        try {
            pause = false;
            stop = false;
            path = editText.getText().toString();
            if (mediaPlayer != null) mediaPlayer.release();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            //mediaPlayer.prepare();
            mediaPlayer.prepareAsync(); //Si streaming
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.seekTo(savePos);
        } catch (Exception e) {
            showMessage("ERROR: " + e.getMessage());
        }
    }


    public void onBufferingUpdate(MediaPlayer arg0, int percent) {
        showMessage("onBufferingUpdate percent:" + percent);
    }

    public void onCompletion(MediaPlayer arg0) {
        showMessage("onCompletion called");
    }

    public void onPrepared(MediaPlayer mediaplayer) {
        showMessage("onPrepared called");


        mediaPlayer.start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null & !pause) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mediaPlayer != null & !pause) {
            mediaPlayer.start();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        if (mediaPlayer != null) {
            int pos = mediaPlayer.getCurrentPosition();
            guardarEstado.putString("ruta", path);
            guardarEstado.putInt("posicion", pos);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        if (recEstado != null) {
            path = recEstado.getString("ruta");
            savePos = recEstado.getInt("posicion");
        }
    }
}
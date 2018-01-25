package com.example.jayanthl.semi_finale_file_handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Socket socket;
    EditText editText;
    Button butt;
    String send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        butt = (Button) findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    public void run() {

                        try {
                            Log.i("Status :","Entering");
                            Socket socket = new Socket("192.168.43.232",3000);
                            Log.i("it is ","success");
                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                            send = editText.getText().toString();
                            output.writeObject(send);
                            output.flush();

                            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                            String message = (String) input.readObject();
                            Log.i("input ","from server :" + message);
                        } catch (Exception e) {
                            Log.i("Error ","is : IOexception");
                        }
                    }
                }).start();
            }
        });
    }
}

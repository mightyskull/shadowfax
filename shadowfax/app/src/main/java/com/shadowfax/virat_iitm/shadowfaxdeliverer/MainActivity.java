package com.shadowfax.virat_iitm.shadowfaxdeliverer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button button;

    public void initDelivery()
    {
        try {
            EditText orderId_view = (EditText) findViewById(R.id.editText_orderId);
            URL url = new URL("https://192.168.1.30:8080/virat_iitm/rest/UserService/customerSms?orderId=" + orderId_view.getText());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                InputStream it = new BufferedInputStream(urlConnection.getInputStream());
                Reader read = new InputStreamReader(it);
                BufferedReader buff = new BufferedReader(read);
                StringBuilder dta = new StringBuilder();
                String chunks;
                while ((chunks = buff.readLine()) != null) {
                    dta.append(chunks);
                }
            } else {
                //Handle else
            }
        }
        catch(Exception e)
        {
            Log.i("com.viratiitm.shadowfax","Exception caught while initiating delivery");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button_initDelivery);

        /*webview.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;

        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        webview.loadUrl("http://www.google.co.in");*/

        setContentView(R.layout.activity_main);
    }

}

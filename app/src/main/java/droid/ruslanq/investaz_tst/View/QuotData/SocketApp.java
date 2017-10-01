package droid.ruslanq.investaz_tst.View.QuotData;

import android.app.Application;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by Ruslanq on 01.10.2017.
 */

public class SocketApp extends Application {

    private static SocketApp instance;
    public String message;
    private Socket quotSocket;
    private Emitter.Listener quotMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            JSONObject data = (JSONObject) args[0];
            String username;
            try {

                message = data.getString("total");
                JSONArray js = data.getJSONArray("result");
                Log.d("sokki", "soki soki");
            } catch (JSONException e) {
                return;
            }


        }
    };

    {
        try {
            quotSocket = IO.socket("https://q.investaz.net:3000");
        } catch (URISyntaxException e) {
        }
    }

    public static SocketApp getInstance() {
        if (instance == null) {
            synchronized (SocketApp.class) {
                if (instance == null) {
                    instance = new SocketApp();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        quotSocket.on("message", quotMessage);
        quotSocket.connect();
    }

}



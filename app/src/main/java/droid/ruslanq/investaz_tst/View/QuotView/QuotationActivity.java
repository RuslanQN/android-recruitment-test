package droid.ruslanq.investaz_tst.View.QuotView;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import droid.ruslanq.investaz_tst.R;
import droid.ruslanq.investaz_tst.View.QuotData.DBHelper;

/**
 * Created by Ruslanq on 01.10.2017.
 */

public class QuotationActivity extends AppCompatActivity {
    private Socket quotSocket;
    private Emitter.Listener quotMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];

                    try {

                        JSONArray js = data.getJSONArray("result");
                        Log.d("Sockt.io", "result exists");
                    } catch (JSONException e) {
                        return;
                    }


                }
            });
        }

    };

    {
        try {
            quotSocket = IO.socket("https://q.investaz.net:3000");
        } catch (URISyntaxException e) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        quotSocket.close();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        quotSocket.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        quotSocket.disconnect();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quot_main);
        Fragment fragment = new QuotationFragment();
        getFragmentManager().beginTransaction().add(R.id.qout_list, fragment).commit();
        quotSocket.on("message", quotMessage);
        quotSocket.connect();
        new DBHelper(this).getWritableDatabase();
    }
}

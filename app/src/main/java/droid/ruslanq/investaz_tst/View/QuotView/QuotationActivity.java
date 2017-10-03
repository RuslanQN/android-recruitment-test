package droid.ruslanq.investaz_tst.View.QuotView;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import droid.ruslanq.investaz_tst.R;
import droid.ruslanq.investaz_tst.View.QuotData.DBHelper;
import droid.ruslanq.investaz_tst.View.QuotData.JsonHandler;
import droid.ruslanq.investaz_tst.View.QuotData.QuotDT_proc;

/**
 * Created by Ruslanq on 01.10.2017.
 */

public class QuotationActivity extends AppCompatActivity {

    private Socket quotSocket;
    Menu menu;
    final Context c = this;
    boolean indiRes = true;
    final List<String> total = new ArrayList<>();
    private Emitter.Listener quotMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    indiRes =false;
                    if(menu!=null) menu.getItem(0).setVisible(indiRes);
                    try {
                        if (total.size() != 0) {
                            if (total.get(0).equals(data.getString("total"))) {
                                return;
                            }
                            total.remove(0);
                        }
                        total.add(data.getString("total"));

                        new QuotDT_proc(c).insert(JsonHandler.fillListQuot(data.getJSONArray("result")));
                        QuotationFragment quotationFragment = (QuotationFragment) getFragmentManager().findFragmentByTag("quotations");
                        quotationFragment.refreshValues();
                        indiRes =true;
                        if(menu!=null) menu.getItem(0).setVisible(indiRes);
                    } catch (JSONException e) {
                        return;
                    }


                }
            });
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.indicator_menu, menu);
        this.menu = menu;

        return true;
    }

    public void menuVisible(Menu menu, boolean visibility) {
        MenuItem indicator = menu.findItem(R.id.indicator);
        indicator.setVisible(visibility);
    }



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
        if (savedInstanceState == null) {
            //setContentView(R.layout.quot_main);
            Fragment fragment = new QuotationFragment();
            getFragmentManager().beginTransaction().add(android.R.id.content, fragment, "quotations").commit();
            quotSocket.on("message", quotMessage);
            quotSocket.connect();
        }
    }


}

package droid.ruslanq.investaz_tst.View.QuotData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by RUSLAN on 10/2/2017.
 */

public class JsonHandler {


    public static List<QuotDataTemplate> fillListQuot(JSONArray jsonArray) {
        List<QuotDataTemplate> list = new ArrayList<>();
        try {

            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonToQuot(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static QuotDataTemplate jsonToQuot(JSONObject jsonObject) {
        QuotDataTemplate quotDataTemplate = new QuotDataTemplate();
        try {
            quotDataTemplate.direction = jsonObject.getString("0").toString();
            quotDataTemplate.name = jsonObject.getString("1").toString();
            quotDataTemplate.val_1 = jsonObject.getString("2").toString();
            quotDataTemplate.val_2 = jsonObject.getString("3").toString();
            quotDataTemplate.val_3 = jsonObject.getString("4").toString();
            quotDataTemplate.val_4 = jsonObject.getString("5").toString();
            quotDataTemplate.spec = jsonObject.getString("6").toString();
            quotDataTemplate.datetime = jsonObject.getString("7").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return quotDataTemplate;
    }
}

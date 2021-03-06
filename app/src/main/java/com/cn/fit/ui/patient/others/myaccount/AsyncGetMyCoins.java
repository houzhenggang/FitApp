package com.cn.fit.ui.patient.others.myaccount;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.cn.fit.http.NetTool;
import com.cn.fit.ui.basic.ActivityBasic;
import com.cn.fit.util.AbsParam;
import com.cn.fit.util.Constant;
import com.cn.fit.util.UtilsSharedData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AsyncGetMyCoins extends AsyncTask<Integer, Integer, String> {

    String result = "";
    private Activity act;
    private static String mycoins = "/goldcoins/querymycoins";
    private long userId;

    public AsyncGetMyCoins(Activity act) {
        this.act = act;
        UtilsSharedData.initDataShare(act);// ////////
        userId = UtilsSharedData.getLong(Constant.USER_ID, 1);
    }

    @Override
    protected String doInBackground(Integer... params) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("userID", userId + "");
        param.put("userType", 0 + "");
        try {
            String url = AbsParam.getBaseUrl() + mycoins;
            System.out.println("=-=-= AsyncGetMyCoins url： " + url);
            result = NetTool.sendPostRequest(url, param, "utf-8");
            System.out.println("=-=-= AsyncGetMyCoins result： " + result);
            Log.i("result", result);
        } catch (Exception e) {
            ((ActivityBasic) act).hideProgressBar();
            e.printStackTrace();
        }
        try {
            JSONObject json = new JSONObject(result);
            ActivityMyAccountCenter.myCoins = Float.parseFloat(json
                    .getString("coins"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
//		((ActivityBasic)act).hideProgressBar();

    }


}

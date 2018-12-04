package com.example.edu.iottermproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.edu.iottermproject.adapter.TempAdapter;
import com.example.edu.iottermproject.comm.LogManager;
import com.example.edu.iottermproject.item.TempListVO;
import com.example.edu.iottermproject.item.TempVO;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    AQuery aq = null;
    ListView listView;
    TempAdapter adapter;
    TempListVO tempListVO;
    ArrayList<TempVO> list = new ArrayList<>();
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button1:
                    doItemList("pos1");// Servlet Web
                    //doItemList1("pos1"); // Node.js Web
                    break;
                case R.id.button2:
                    doItemList("pos2");
                    //doItemList1("pos2");
                    break;
            }
        }
    };
    void doItemList1(String loc) {
        String url = "http://192.168.120.22/tempList?loc=" + loc;
        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray object, AjaxStatus status) {
                LogManager.print(MainActivity.this, object.toString());
                doChangeItem1(object);  //Node.js 반응
            }
        });
    }
    void doItemList(String loc) {
        String url = "http://192.168.120.22/tempList?loc=" + loc;
        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                LogManager.print(MainActivity.this, object.toString());
                doChangeItem(object); //Web Servelt 반응
            }
        });
    }
    void doChangeItem1(JSONArray jsonArray){
        LogManager.print(this, jsonArray.length() + "");
        JSONObject obj;
        TempVO tempVO = null;
        try{
            list.clear();
            for(int i = 0; i < jsonArray.length(); i++){
                obj = jsonArray.getJSONObject(i);
                tempVO = new TempVO();
                tempVO.setIdate(obj.getString("idate"));
                tempVO.setLoc(obj.getString("loc"));
                tempVO.setNum(obj.getString("num"));
                tempVO.setType(obj.getString("type"));
                tempVO.setTemperature(obj.getString("temperature"));
                list.add(tempVO);
            }
        }catch(JSONException e){
        }
        adapter.setList(list);
        adapter.notifyDataSetChanged();;
    }
    void doChangeItem(JSONObject object){
        Gson gson = new Gson();
        tempListVO = gson.fromJson(object.toString(), TempListVO.class);
        list = tempListVO.getList();
        adapter.setList(list);
        adapter.notifyDataSetChanged();;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aq = new AQuery(this);
        listView = (ListView) findViewById(R.id.listView);
        findViewById(R.id.button1).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);

        adapter = new TempAdapter(this, R.layout.item, list);
        listView.setAdapter(adapter);
    }


}

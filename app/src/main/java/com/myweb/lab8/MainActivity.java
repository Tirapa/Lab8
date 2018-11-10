package com.myweb.lab8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txtStdid;
    private EditText txtStdName;
    private EditText txtStdTel;
    private EditText txtStdEmail;
    private EditText editStdId = null;
    private EditText editStdName = null;
    private EditText editStdTel = null;
    private EditText editStdEmail = null;
    private Button btnSave;
    private Button btnShow;
    private ListView dataView;
    private ListView clickView;
    private MySQLConnect mySQLConnect;
    private List<String> items;
    private ArrayAdapter<String> adt;
    private String dataStdId = null;
    private String dataStdName = null;
    private String dataStdTel = null;
    private String dataStdEmail = null;
    private View promptView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        update();

        clickView = findViewById(R.id.dataView);

        clickView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String selectFromList = (clickView.getItemAtPosition(pos).toString() + "");
                int indextel;
                String caltel = "";
                dataStdId = selectFromList.substring(0, 11);
                caltel = selectFromList.substring(12);
                indextel = caltel.indexOf("0");
                dataStdName = caltel.substring(0, indextel - 1);
                dataStdTel = caltel.substring(indextel, indextel + 10);
                dataStdEmail = caltel.substring(indextel + 11);
                return true;
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySQLConnect.sentData(txtStdid.getText().toString(), txtStdName.getText().toString(), txtStdTel.getText().toString(), txtStdEmail.getText().toString());
                items.add(txtStdid.getText().toString() + "\n" + txtStdName.getText().toString() + "\n" + txtStdTel.getText().toString() + "\n" + txtStdEmail.getText().toString());
                adt.notifyDataSetChanged();
                txtStdid.setText("");
                txtStdName.setText("");
                txtStdTel.setText("");
                txtStdEmail.setText("");
            }

        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkList();
            }
        });

    }
}




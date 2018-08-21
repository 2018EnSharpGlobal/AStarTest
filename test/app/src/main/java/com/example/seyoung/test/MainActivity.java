package com.example.seyoung.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProcedureManager procedureManager = new ProcedureManager();

        procedureManager.real_get_Path();
        procedureManager.Navi_Path();
        Log.e("outPut_1","------------------");
        procedureManager.outPut_Navi();
        Log.e("outPut_2","------------------");
        procedureManager.outPut_Navi();
        Log.e("outPut_3","------------------");
        procedureManager.outPut_Navi();
        setContentView(R.layout.activity_main);
    }
}

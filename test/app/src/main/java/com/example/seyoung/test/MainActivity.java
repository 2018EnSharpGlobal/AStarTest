package com.example.seyoung.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Node initialNode = new Node(2, 1);
        Node finalNode = new Node(5, 5);
        int rows = 6;
        int cols = 7;
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        int[][] blocksArray = new int[][] {{0,3}, { 1, 3 }, {2,3}, {3 ,3} ,{3,4},{3,5}};
        aStar.setBlocks(blocksArray);
        List<Node> path = aStar.findPath();
        for (Node node : path){
            Log.i(this.getClass().getName(),node.toString());
        }

        setContentView(R.layout.activity_main);



    }


}




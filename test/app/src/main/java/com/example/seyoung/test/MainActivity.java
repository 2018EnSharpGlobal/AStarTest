package com.example.seyoung.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
    //제공해야할 정보
    private static int exit_1 = 1;
    private static int exit_2 = 2;
    private static int exit_3 = 3;
    private static int exit_4 = 4;
    private static int exit_5 = 5;
    private static int exit_6 = 6;
    private static int bathroom_men = 7;
    private static int bathroom_women = 8;
    private static int ticket_barrier_1 = 9;
    private static int ticket_barrier_2 = 10;

    //이동수단
    private static int stair = 0;
    private static int escalator = -1;
    private static int elevator = -2;
    */

    Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Node initialNode = new Node(6, 1);
        initialNode.setFloor(-1);
        Node finalNode = new Node(0, 6);
        finalNode.setFloor(-2);

        //위도 경도
        double underGround1_latitude;
        double underGround1_longitude;
        double underGround2_latitude;
        double underGround2_longitude;
        double underGround3_latitude;
        double underGround3_longitude;

        map = new Map();

        AStar underGround_1;
        AStar underGround_2;

        if(initialNode.getFloor() != finalNode.getFloor()){
            Node better_transportation = map.better_Means_Transportation(initialNode,finalNode);
            underGround_1 = new AStar(map.underGround1_rows, map.underGround1_cols, initialNode, better_transportation);
            underGround_2 = new AStar(map.underGround2_rows, map.underGround2_cols, better_transportation, finalNode);

            //지하 1층 길이 막혀 있는 부분 설정
            underGround_1.setBlocks(map.underGround1_isBlock);
            underGround_2.setBlocks(map.underGround2_isBlock);

            List<Node> path = underGround_1.findPath();
            path.addAll(underGround_2.findPath());

            for (Node node : path){
                Log.i(String.valueOf(node.getFloor()),node.toString());
            }
        }
        else{

        }

        //지하 층 별로 변수 설정
        /*
        AStar underGround_1 = new AStar(underGround1_rows, underGround1_cols, initialNode, finalNode);
        AStar underGround_2 = new AStar(underGround2_rows, underGround2_cols, initialNode, finalNode);
        AStar underGround_3 = new AStar(underGround3_rows, underGround3_cols, initialNode, finalNode);
        */

        setContentView(R.layout.activity_main);
    }

}




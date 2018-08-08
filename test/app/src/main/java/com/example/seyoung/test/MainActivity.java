package com.example.seyoung.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int B1 = -1;
    private final int B2 = -2;
    private final int B3 = -3;

    SubWayMap map_underGround_1;
    SubWayMap map_underGround_2;
    SubWayMap map_underGround_3;

    //위도 경도
    double user_latitude;
    double user_longitude;

    int user_floor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Node initialNode = new Node(6, 1);
        initialNode.setFloor(-1);
        Node finalNode = new Node(0, 3);
        finalNode.setFloor(-2);

        map_underGround_1 = new UnderGround_1();
        map_underGround_2 = new UnderGround_2();
        map_underGround_3 = new UnderGround_3();

        List<Node> path= null;

        List<Node> floor = null;
        List<Node> other_floor = null;

        AStar underGround_1;
        AStar underGround_2;
        AStar underGround_3;

        if(initialNode.getFloor() != finalNode.getFloor()){
            Node better_transportation = null;

            switch(initialNode.getFloor()){
                case B1:
                    better_transportation = map_underGround_1.better_Means_Transportation(initialNode,finalNode);
                    underGround_1 = new AStar(map_underGround_1.underGround_rows, map_underGround_1.underGround_cols, initialNode, better_transportation);
                    underGround_1.setBlocks(map_underGround_1.underGround_isBlock);
                    floor = underGround_1.findPath();
                    break;
                case B2:
                    better_transportation = map_underGround_2.better_Means_Transportation(initialNode,finalNode);
                    underGround_2 = new AStar(map_underGround_2.underGround_rows, map_underGround_2.underGround_cols, initialNode, better_transportation);
                    underGround_2.setBlocks(map_underGround_2.underGround_isBlock);
                    floor = underGround_2.findPath();
                    break;
                case B3:
                    better_transportation =map_underGround_3.better_Means_Transportation(initialNode,finalNode);
                    underGround_3 = new AStar(map_underGround_3.underGround_rows, map_underGround_3.underGround_cols, initialNode, better_transportation);
                    underGround_3.setBlocks(map_underGround_3.underGround_isBlock);
                    floor = underGround_3.findPath();
                    break;
                default:
                    break;
            }

            for(Node node : floor){
                node.setFloor(initialNode.getFloor());
            }

            switch(finalNode.getFloor()){
                case B1:
                    underGround_1 = new AStar(map_underGround_1.underGround_rows, map_underGround_1.underGround_cols, better_transportation, finalNode);
                    underGround_1.setBlocks(map_underGround_1.underGround_isBlock);
                    other_floor = underGround_1.findPath();
                    break;
                case B2:
                    underGround_2 = new AStar(map_underGround_2.underGround_rows, map_underGround_2.underGround_cols, better_transportation, finalNode);
                    underGround_2.setBlocks(map_underGround_2.underGround_isBlock);
                    other_floor = underGround_2.findPath();
                    break;
                case B3:
                    underGround_3 = new AStar(map_underGround_3.underGround_rows, map_underGround_3.underGround_cols, better_transportation, finalNode);
                    underGround_3.setBlocks(map_underGround_3.underGround_isBlock);
                    other_floor = underGround_3.findPath();
                    break;
                default:
                    break;
            }

            for(Node node : other_floor){
                node.setFloor(finalNode.getFloor());
            }

            path = floor;
            path.addAll(other_floor);
        }
        else{
            switch (initialNode.getFloor()){
                case B1:
                    underGround_1 = new AStar(map_underGround_1.underGround_rows,map_underGround_1.underGround_cols,initialNode,finalNode);
                    underGround_1.setBlocks(map_underGround_1.underGround_isBlock);
                    path = underGround_1.findPath();
                    break;
                case B2:
                    underGround_2 = new AStar(map_underGround_2.underGround_rows,map_underGround_2.underGround_cols,initialNode,finalNode);
                    underGround_2.setBlocks(map_underGround_1.underGround_isBlock);
                    path = underGround_2.findPath();
                    break;
                case B3:
                    underGround_3 = new AStar(map_underGround_3.underGround_rows,map_underGround_3.underGround_cols,initialNode,finalNode);
                    underGround_3.setBlocks(map_underGround_1.underGround_isBlock);
                    path = underGround_3.findPath();
                    break;
                default:
                    break;
            }
            for(Node node : path){
                node.setFloor(initialNode.getFloor());
            }
        }

        //경로 로그에 출력하기
        for (Node node : path){
            Log.e(String.valueOf(node.getFloor()),"Floor:"+String.valueOf(node.getFloor())+node.toString());
        }

        setContentView(R.layout.activity_main);
    }

}




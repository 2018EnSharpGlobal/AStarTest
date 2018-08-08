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
    double underGround1_latitude;
    double underGround1_longitude;
    double underGround2_latitude;
    double underGround2_longitude;
    double underGround3_latitude;
    double underGround3_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Node initialNode = new Node(6, 1);
        initialNode.setFloor(-1);
        Node finalNode = new Node(0, 6);
        finalNode.setFloor(-2);

        map_underGround_1 = new UnderGround_1(7,7, new int[][]{{0,0},{1,0},{1,1},{2,1},{3,1},{4,1},{5,0},{5,1},{6,0},{3,4},{4,4},{5,3},{5,4},{6,3},{3,6},{4,6},{5,6},{6,6}});
        map_underGround_2 = new UnderGround_2(10,10, new int[][]{{3,4},{4,4},{5,3},{5,4},{6,3},{3,6},{4,6},{5,6},{6,6}});
        map_underGround_3 = new UnderGround_3(20,20, new int[][] {{0,3}, { 1, 3 }, {2,3}, {3 ,3} ,{3,4},{3,5}});

        List<Node> path=null;

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
                    path = underGround_1.findPath();
                    break;
                case B2:
                    better_transportation = map_underGround_2.better_Means_Transportation(initialNode,finalNode);
                    underGround_2 = new AStar(map_underGround_2.underGround_rows, map_underGround_2.underGround_cols, initialNode, better_transportation);
                    underGround_2.setBlocks(map_underGround_2.underGround_isBlock);
                    path = underGround_2.findPath();
                    break;
                case B3:
                    better_transportation =map_underGround_3.better_Means_Transportation(initialNode,finalNode);
                    underGround_3 = new AStar(map_underGround_3.underGround_rows, map_underGround_3.underGround_cols, initialNode, better_transportation);
                    underGround_3.setBlocks(map_underGround_3.underGround_isBlock);
                    path = underGround_3.findPath();
                    break;
                default:
                    break;
            }

            switch(finalNode.getFloor()){
                case B1:
                    better_transportation = map_underGround_1.better_Means_Transportation(initialNode,finalNode);
                    underGround_1 = new AStar(map_underGround_1.underGround_rows, map_underGround_1.underGround_cols, initialNode, better_transportation);
                    underGround_1.setBlocks(map_underGround_1.underGround_isBlock);
                    path.addAll(underGround_1.findPath());
                    break;
                case B2:
                    better_transportation = map_underGround_2.better_Means_Transportation(initialNode,finalNode);
                    underGround_2 = new AStar(map_underGround_2.underGround_rows, map_underGround_2.underGround_cols, initialNode, better_transportation);
                    underGround_2.setBlocks(map_underGround_2.underGround_isBlock);
                    path.addAll(underGround_2.findPath());
                    break;
                case B3:
                    better_transportation = map_underGround_3.better_Means_Transportation(initialNode,finalNode);
                    underGround_3 = new AStar(map_underGround_3.underGround_rows, map_underGround_3.underGround_cols, initialNode, better_transportation);
                    underGround_3.setBlocks(map_underGround_3.underGround_isBlock);
                    path.addAll(underGround_3.findPath());
                    break;
                default:
                    break;
            }
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
        }

        for (Node node : path){
            Log.e(String.valueOf(node.getFloor()),node.toString());
        }

        setContentView(R.layout.activity_main);
    }

}




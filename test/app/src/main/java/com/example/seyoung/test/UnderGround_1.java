package com.example.seyoung.test;

import android.util.Log;

public class UnderGround_1 extends SubWayMap {
    Node elevator_1;
    Node elevator_2;
    Node elevator_3;
    Node elevator_4;

    Node stair_1;
    Node stair_2;

    Node exit_1;
    Node exit_2;
    Node exit_3;
    Node exit_4;
    Node exit_5;
    Node exit_6;

    int[][] underGround1_isBlock = new int[][]{{0,0},{1,0},{1,1},{2,1},{3,1},{4,1},{5,0},{5,1},{6,0},{3,4},{4,4},{5,3},{5,4},{6,3},{3,6},{4,6},{5,6},{6,6}};

    private int checkElevator;
    private int checkStair;

    public UnderGround_1(){
        this.underGround_isBlock = underGround1_isBlock;

        elevator_1 = new Node(6,4);
        stair_1 = new Node(2,6);
    }

    @Override
    public Node better_Means_Transportation(Node initalNode, Node finalNode){
        AStar aStar = new AStar(underGround_rows,underGround_cols,initalNode,elevator_1);
        for(Node node : aStar.findPath()){
            checkElevator  += node.getF();
        }
        aStar = new AStar(underGround_rows,underGround_cols,elevator_1,finalNode);
        for(Node node : aStar.findPath()){
            checkElevator += node.getF();
        }
        aStar = new AStar(underGround_rows,underGround_cols,initalNode,stair_1);
        for(Node node : aStar.findPath()){
            checkStair += node.getF();
        }
        aStar = new AStar(underGround_rows,underGround_cols,stair_1,finalNode);
        for(Node node : aStar.findPath()){
            checkStair += node.getF();
        }

        Log.e(this.getClass().getName(),"지하 1층 checkStair: "+String.valueOf(checkStair)+"checkElevator: "+String.valueOf(checkElevator));

        if(checkStair >= checkElevator){
            return elevator_1;
        }
        else{
            return stair_1;
        }
    }
}

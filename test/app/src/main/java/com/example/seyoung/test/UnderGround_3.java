package com.example.seyoung.test;

import android.util.Log;

public class UnderGround_3 extends SubWayMap {

    Node elevator_1;
    Node elevator_2;

    Node stair_1;
    Node stair_2;
    Node stair_3;
    Node stair_4;

    int[][] underGround3_isBlock= new int[][]{};

    private int checkStair;
    private int checkElevator;

    public UnderGround_3(){
      this.underGround_isBlock = underGround3_isBlock;
    }

    @Override
    public Node better_Means_Transportation(Node initalNode, Node finalNode) {
        AStar aStar = new AStar(underGround_rows,underGround_cols,initalNode,elevator_1);
        for(Node node : aStar.findPath()){
            checkElevator += node.getF();
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

        Log.e(this.getClass().getName(),"지하 2층 checkStair: "+String.valueOf(checkStair)+"checkElevator: "+String.valueOf(checkElevator));

        if(checkStair >= checkElevator){
            return elevator_1;
        }
        else{
            return stair_1;
        }
    }
}

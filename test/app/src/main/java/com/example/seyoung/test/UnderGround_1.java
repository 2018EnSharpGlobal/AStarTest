package com.example.seyoung.test;

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

    private int checkElevator;
    private int checkStair;

    public UnderGround_1(int rows,int cols,int[][] underGround_isBlock){
        this.underGround_rows = rows;
        this.underGround_cols = cols;
        this.underGround_isBlock = underGround_isBlock;

        elevator_1 = new Node(6,4);
        stair_1 = new Node(2,6);
    }

    @Override
    public Node better_Means_Transportation(Node initalNode, Node finalNode){
        AStar aStar = new AStar(underGround_rows,underGround_cols,initalNode,elevator_1);
        for(Node node : aStar.findPath()){
            checkStair += node.getF();
        }
        aStar = new AStar(underGround_rows,underGround_cols,elevator_1,finalNode);
        for(Node node : aStar.findPath()){
            checkStair += node.getF();
        }
        aStar = new AStar(underGround_rows,underGround_cols,initalNode,stair_1);
        for(Node node : aStar.findPath()){
            checkElevator += node.getF();
        }
        aStar = new AStar(underGround_rows,underGround_cols,stair_1,finalNode);
        for(Node node : aStar.findPath()){
            checkElevator += node.getF();
        }

        if(checkStair >= checkElevator){
            return elevator_1;
        }
        else{
            return stair_1;
        }
    }
}

package com.example.seyoung.test;

public class UnderGround_2 extends SubWayMap {
    Node elevator_1;

    Node stair_1;
    Node stair_2;
    Node stair_3;
    Node stair_4;
    Node stair_5;
    Node stair_6;

    Node bathroom_men;
    Node bathroom_women;

    Node ticket_barrier1;
    Node ticket_barrier2;

    private int checkStair;
    private int checkElevator;

    public UnderGround_2(int rows,int cols,int[][] underGround_isBlock){
        this.underGround_rows = rows;
        this.underGround_cols = cols;
        this.underGround_isBlock = underGround_isBlock;

        elevator_1 = new Node(6,4);
        stair_1 = new Node(2,6);
    }

    @Override
    public Node better_Means_Transportation(Node initalNode, Node finalNode) {
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

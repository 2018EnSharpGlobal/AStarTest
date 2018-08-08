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

    public UnderGround_2(int rows,int cols,int[][] underGround_isBlock){
        this.underGround_rows = rows;
        this.underGround_cols = cols;
        this.underGround_isBlock = underGround_isBlock;
    }

    @Override
    public Node better_Means_Transportation(Node initalNode, Node finalNode) {
        return super.better_Means_Transportation(initalNode, finalNode);
    }
}

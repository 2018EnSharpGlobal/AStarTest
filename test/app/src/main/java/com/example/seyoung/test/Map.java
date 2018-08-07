package com.example.seyoung.test;

public class Map {
    int underGround1_rows;
    int underGround1_cols;

    int underGround2_rows;
    int underGround2_cols;

    int underGround3_rows;
    int underGround3_cols;

    //길이 막혀 있는 위치 설정
    int[][] underGround1_isBlock;
    int[][] underGround2_isBlock;
    int[][] underGround3_isBlock;

    //특정 정보를 가지고 있는 위치 설정
    Node elevatorNode = new Node(6,4);
    Node stairNode = new Node(2,6);

    int checkElevator = 0;
    int checkStair = 0;
    public Map(){
        underGround1_rows = 7;
        underGround1_cols = 7;

        underGround2_rows = 7;
        underGround2_cols = 7;

        underGround3_rows = 7;
        underGround3_cols = 7;

        underGround1_isBlock = new int[][] {{0,0},{1,0},{1,1},{2,1},{3,1},{4,1},{5,0},{5,1},{6,0},{3,4},{4,4},{5,3},{5,4},{6,3},{3,6},{4,6},{5,6},{6,6}};
        underGround2_isBlock = new int[][] {{3,4},{4,4},{5,3},{5,4},{6,3},{3,6},{4,6},{5,6},{6,6}};
        underGround3_isBlock = new int[][] {{0,3}, { 1, 3 }, {2,3}, {3 ,3} ,{3,4},{3,5}};
    }

    //이동수단 비교하여 더 나은 이동수단 제공하는 함수
    public Node better_Means_Transportation(Node initalNode, Node finalNode){
        AStar aStar = new AStar(underGround1_rows,underGround1_cols,initalNode,stairNode);
        for(Node node : aStar.findPath()){
            checkStair += node.getF();
        }
        aStar = new AStar(underGround2_rows,underGround2_cols,stairNode,finalNode);
        for(Node node : aStar.findPath()){
            checkStair += node.getF();
        }
        aStar = new AStar(underGround2_rows,underGround2_cols,initalNode,elevatorNode);
        for(Node node : aStar.findPath()){
            checkElevator += node.getF();
        }
        aStar = new AStar(underGround2_rows,underGround2_cols,elevatorNode,finalNode);
        for(Node node : aStar.findPath()){
            checkElevator += node.getF();
        }

        if(checkStair >= checkElevator){
            return elevatorNode;
        } else{
            return stairNode;
        }
    }
}

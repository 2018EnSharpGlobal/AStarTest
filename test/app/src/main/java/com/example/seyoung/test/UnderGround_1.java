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

    int[][] underGround1_information = new int[][]{
            {0,1,2,3,4,5,6},
            {0,1,2,3,4,5,6},
            {0,1,2,3,4,5,6},
            {0,1,2,3,4,5,6},
            {0,1,2,3,4,5,6},
            {0,1,2,3,4,5,6},
            {0,1,2,3,4,5,6}};

    private int[] check_means_Transportation;

    private int[] check_Elevator;
    private int[] check_Stair;

    public UnderGround_1(){
        this.underGround_information = underGround1_information;

        elevator_1 = new Node(6,4);
        elevator_2 = new Node(0,0);
        elevator_3 = new Node(6,4);
        elevator_4 = new Node(0,0);

        stair_1 = new Node(2,6);
        stair_2 = new Node(2,6);

        exit_1 = new Node(0,0);
        exit_2 = new Node(0,0);
        exit_3 = new Node(0,0);
        exit_4 = new Node(0,0);
        exit_5 = new Node(0,0);
        exit_6 = new Node(0,0);

        check_means_Transportation= new int[6];
        check_Elevator = new int[4];
        check_Stair = new int[2];

        for(int i=0;i<check_means_Transportation.length;i++){
            check_means_Transportation[i] = 0;
        }

        for(int i=0;i< check_Elevator.length;i++){
            check_Elevator[i] = 0;
        }

        for(int i=0; i<check_Stair.length;i++){
            check_Stair[i] = 0;
        }
    }

    @Override
    public Node better_Means_Transportation(Node initalNode, Node finalNode){
        int find_means_Transportation;

        //엘레베이터
        Navigation better_find_path = new Navigation(underGround_rows,underGround_cols,initalNode,elevator_1,MapInfo.B1);

        for(Node node : better_find_path.findPath()){
            check_means_Transportation[0]  += node.getF();
        }
        set_Initail_Final_Node(better_find_path,elevator_1,finalNode);
        for(Node node : better_find_path.findPath()){
            check_means_Transportation[0] += node.getF();
        }

        set_Initail_Final_Node(better_find_path,initalNode,elevator_2);
        for(Node node : better_find_path.findPath()){
            check_means_Transportation[1] += node.getF();
        }
        set_Initail_Final_Node(better_find_path,elevator_2,finalNode);
        for(Node node : better_find_path.findPath()){
            check_means_Transportation[1] += node.getF();
        }

        set_Initail_Final_Node(better_find_path,initalNode,elevator_3);
        for(Node node : better_find_path.findPath()){
            check_means_Transportation[2] += node.getF();
        }
        set_Initail_Final_Node(better_find_path,elevator_3,finalNode);
        for(Node node : better_find_path.findPath()){
            check_means_Transportation[2] += node.getF();
        }

        set_Initail_Final_Node(better_find_path,initalNode,elevator_4);
        for(Node node : better_find_path.findPath()){
            check_means_Transportation[3] += node.getF();
        }
        set_Initail_Final_Node(better_find_path,elevator_4,finalNode);
        for(Node node : better_find_path.findPath()){
            check_means_Transportation[3] += node.getF();
        }

        //계단
        set_Initail_Final_Node(better_find_path,initalNode,stair_1);
        for(Node node : better_find_path.findPath()){
            check_means_Transportation[4]  += node.getF();
        }
        set_Initail_Final_Node(better_find_path,stair_1,finalNode);
        for(Node node : better_find_path.findPath()){
            check_means_Transportation[4] += node.getF();
        }

        set_Initail_Final_Node(better_find_path,initalNode,stair_2);
        for(Node node : better_find_path.findPath()){
            check_means_Transportation[5] += node.getF();
        }
        set_Initail_Final_Node(better_find_path,stair_2,finalNode);
        for(Node node : better_find_path.findPath()){
            check_means_Transportation[5] += node.getF();
        }

        find_means_Transportation = compare_Minimum(check_means_Transportation);

        if(find_means_Transportation==0){
            return elevator_1;
        }
        else if(find_means_Transportation == 1){
            return elevator_2;
        }
        else if(find_means_Transportation == 2){
            return elevator_3;
        }
        else if(find_means_Transportation == 3){
            return elevator_4;
        }
        else if(find_means_Transportation == 4){
            return stair_1;
        }
        else{
            return stair_2;
        }
    }

    @Override
    public Node stair_Means_Transportation(Node initalNode, Node finalNode) {
        int find_stair;

        Navigation better_find_path = new Navigation(underGround_rows,underGround_cols,initalNode,stair_1,MapInfo.B1);
        for(Node node : better_find_path.findPath()){
            check_Stair[0]  += node.getF();
        }
        set_Initail_Final_Node(better_find_path,stair_1,finalNode);
        for(Node node : better_find_path.findPath()){
            check_Stair[0] += node.getF();
        }

        set_Initail_Final_Node(better_find_path,initalNode,stair_2);
        for(Node node : better_find_path.findPath()){
            check_Stair[1] += node.getF();
        }
        set_Initail_Final_Node(better_find_path,stair_2,finalNode);
        for(Node node : better_find_path.findPath()){
            check_Stair[1] += node.getF();
        }

        find_stair = compare_Minimum(check_Stair);

        if(find_stair == 0 ){
            return stair_1;
        }
        else{
            return stair_2;
        }
    }

    @Override
    public Node elevator_Means_Transportation(Node initalNode, Node finalNode) {
        int find_elevator;

        Navigation better_find_path = new Navigation(underGround_rows,underGround_cols,initalNode,elevator_1,MapInfo.B1);
        for(Node node : better_find_path.findPath()){
            check_Elevator[0]  += node.getF();
        }
        set_Initail_Final_Node(better_find_path,elevator_1,finalNode);
        for(Node node : better_find_path.findPath()){
            check_Elevator[0] += node.getF();
        }

        set_Initail_Final_Node(better_find_path,initalNode,elevator_2);
        for(Node node : better_find_path.findPath()){
            check_Elevator[1] += node.getF();
        }
        set_Initail_Final_Node(better_find_path,elevator_2,finalNode);
        for(Node node : better_find_path.findPath()){
            check_Elevator[1] += node.getF();
        }

        set_Initail_Final_Node(better_find_path,initalNode,elevator_3);
        for(Node node : better_find_path.findPath()){
            check_Elevator[2] += node.getF();
        }
        set_Initail_Final_Node(better_find_path,elevator_3,finalNode);
        for(Node node : better_find_path.findPath()){
            check_Elevator[2] += node.getF();
        }

        set_Initail_Final_Node(better_find_path,initalNode,elevator_4);
        for(Node node : better_find_path.findPath()){
            check_Elevator[3] += node.getF();
        }
        set_Initail_Final_Node(better_find_path,elevator_4,finalNode);
        for(Node node : better_find_path.findPath()){
            check_Elevator[3] += node.getF();
        }

        find_elevator = compare_Minimum(check_Elevator);

        if(find_elevator==0){
            return elevator_1;
        }
        else if(find_elevator == 1){
            return elevator_2;
        }
        else if(find_elevator == 2){
            return elevator_3;
        }
        else{
            return elevator_4;
        }
    }
}

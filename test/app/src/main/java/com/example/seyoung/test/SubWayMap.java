package com.example.seyoung.test;


import android.util.Log;

public class SubWayMap {

    int underGround_rows;
    int underGround_cols;

    int find_Minimum;

    int[][] underGround_information;

    public SubWayMap(){
        underGround_rows = 200;
        underGround_cols = 30;
    }

    //사용자 위치에서 가장 가까운 이동수단까지의 경로 제공
    protected Node better_Means_Transportation(Node initalNode, Node finalNode){
        return initalNode;
    }

    //사용자가 계단을 이동수단으로 이용하고 싶을 때 가장 가까운 계단까지의 경로 제공
    protected Node stair_Means_Transportation(Node initalNode, Node finalNode){
        return initalNode;
    }

    //사용자가 엘레베이터를 이동수단으로 이용하고 싶을 때 가장 가까운까지의 엘레베이터 경로 제공
    protected Node elevator_Means_Transportation(Node initalNode, Node finalNode){
        return initalNode;
    }

    //최소 F 값 배열 요소 찾기
    protected  int compare_Minimum(int[] means_transportation){
        find_Minimum = means_transportation[0];
        for(int i=1;i<means_transportation.length;i++){
            Log.e(String.valueOf(i),String.valueOf(means_transportation[i]));
            if(find_Minimum >= means_transportation[i]){
                find_Minimum = means_transportation[i];
            }
        }

        int find_component;

        for(find_component=0;find_component<means_transportation.length;find_component++){
            if(find_Minimum == means_transportation[find_component])
                break;
        }

        return find_component;
    }

    protected void set_Initail_Final_Node(Navigation navi,Node initialNode,Node finalNode){
        navi.setInitialNode(initialNode);
        navi.setFinalNode(finalNode);
    }
}

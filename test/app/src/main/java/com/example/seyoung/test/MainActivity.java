package com.example.seyoung.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SubWayMap map_underGround_1;
    SubWayMap map_underGround_2;
    SubWayMap map_underGround_3;

    //위도 경도
    double user_latitude;
    double user_longitude;

    int user_floor;

    boolean use_elevator;
    boolean use_stair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        use_elevator = false;
        use_stair = false;

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

        Navigation underGround_1= new Navigation(MapInfo.map_row, MapInfo.map_col,MapInfo.B1);
        Navigation underGround_2 = new Navigation(MapInfo.map_row,MapInfo.map_col,MapInfo.B2);
        Navigation underGround_3 = new Navigation(MapInfo.map_row,MapInfo.map_col,MapInfo.B3);

        if(initialNode.getFloor() != finalNode.getFloor()){
            Node better_means_transportation = null;
            Navigation initial_Navi = null;
            Navigation final_Navi = null;

            switch (initialNode.getFloor()){
                case MapInfo.B1:
                    initial_Navi = underGround_1;
                    break;
                case MapInfo.B2:
                    initial_Navi = underGround_2;
                    break;
                case MapInfo.B3:
                    initial_Navi = underGround_3;
                    break;
            }

            switch (finalNode.getFloor()){
                case MapInfo.B1:
                    final_Navi = underGround_1;
                    break;
                case MapInfo.B2:
                    final_Navi = underGround_2;
                    break;
                case MapInfo.B3:
                    final_Navi = underGround_3;
                    break;
            }

            //엘레베이터 선호 할 때
            if(use_elevator) {
                int[] check_Elevator = new int[4];
                for(int i=0;i<check_Elevator.length;i++){
                    check_Elevator[i] = 0;
                }
                switch (initialNode.getFloor()) {
                    case MapInfo.B1:
                        check_Elevator[0] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][1]);
                        check_Elevator[1] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][2]);
                        check_Elevator[2] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][3]);
                        check_Elevator[3] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][4]);
                        break;
                    case MapInfo.B2:
                        check_Elevator[0] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][1]);
                        check_Elevator[1] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][2]);
                        check_Elevator[2] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][3]);
                        check_Elevator[3] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][4]);
                        break;
                    case MapInfo.B3:
                        check_Elevator[0] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][1]);
                        check_Elevator[1] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][2]);
                        check_Elevator[2] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][3]);
                        check_Elevator[3] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][4]);
                        break;
                    default:
                        break;
                }

                switch(finalNode.getFloor()){
                    case MapInfo.B1:
                        check_Elevator[0] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][1], finalNode);
                        check_Elevator[1] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][2], finalNode);
                        check_Elevator[2] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][3], finalNode);
                        check_Elevator[3] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][4], finalNode);
                        break;
                    case MapInfo.B2:
                        check_Elevator[0] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][1], finalNode);
                        check_Elevator[1] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][2], finalNode);
                        check_Elevator[2] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][3], finalNode);
                        check_Elevator[3] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][4], finalNode);
                        break;
                    case MapInfo.B3:
                        check_Elevator[0] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][1], finalNode);
                        check_Elevator[1] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][2], finalNode);
                        check_Elevator[2] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][3], finalNode);
                        check_Elevator[3] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][4], finalNode);
                        break;
                     default:
                         break;
                }

                int find_component = compare_Minimum(check_Elevator);

                switch (find_component+7){  //여기서 더하는 7 값은 보정값
                    case MapInfo.elevator_1:
                        better_means_transportation = underGround_1.getSearchArea()[5][1];
                        break;
                    case MapInfo.elevator_2:
                        better_means_transportation = underGround_1.getSearchArea()[5][2];
                        break;
                    case MapInfo.elevator_3:
                        better_means_transportation = underGround_1.getSearchArea()[5][3];
                        break;
                    case MapInfo.elevator_4:
                        better_means_transportation = underGround_1.getSearchArea()[5][4];
                        break;
                        default:
                            break;
                }

                initial_Navi.set_Initail_Final_Node(initialNode,better_means_transportation);
                floor = initial_Navi.findPath();
                final_Navi.set_Initail_Final_Node(better_means_transportation,finalNode);
                other_floor = final_Navi.findPath();

                path = floor;
                path.addAll(other_floor);
            }


            else if(use_stair){

            }
            else if(!use_elevator && !use_stair){

            }


//            Node better_transportation = null;

/*
            switch(initialNode.getFloor()){
                case MapInfo.B1:
                    better_transportation = map_underGround_1.better_Means_Transportation(initialNode,finalNode);
                    underGround_1 = new Navigation(map_underGround_1.underGround_rows, map_underGround_1.underGround_cols, initialNode, better_transportation,MapInfo.B1);
                    //underGround_1.setBlocks(map_underGround_1.underGround_isBlock);
                    underGround_1.setInformations(map_underGround_1.underGround_information);
                    floor = underGround_1.findPath();
                    break;
                case MapInfo.B2:
                    better_transportation = map_underGround_2.better_Means_Transportation(initialNode,finalNode);
                    underGround_2 = new Navigation(map_underGround_2.underGround_rows, map_underGround_2.underGround_cols, initialNode, better_transportation,MapInfo.B2);
                    //underGround_2.setBlocks(map_underGround_2.underGround_isBlock);
                    floor = underGround_2.findPath();
                    break;
                case MapInfo.B3:
                    better_transportation =map_underGround_3.better_Means_Transportation(initialNode,finalNode);
                    underGround_3 = new Navigation(map_underGround_3.underGround_rows, map_underGround_3.underGround_cols, initialNode, better_transportation,MapInfo.B3);
                    //underGround_3.setBlocks(map_underGround_3.underGround_isBlock);
                    floor = underGround_3.findPath();
                    break;
                default:
                    break;
            }

            switch(finalNode.getFloor()){
                case MapInfo.B1:
                    underGround_1 = new Navigation(map_underGround_1.underGround_rows, map_underGround_1.underGround_cols, better_transportation, finalNode,MapInfo.B1);
                    //underGround_1.setBlocks(map_underGround_1.underGround_isBlock);
                    other_floor = underGround_1.findPath();
                    break;
                case MapInfo.B2:
                    underGround_2 = new Navigation(map_underGround_2.underGround_rows, map_underGround_2.underGround_cols, better_transportation, finalNode,MapInfo.B2);
                    //underGround_2.setBlocks(map_underGround_2.underGround_isBlock);
                    other_floor = underGround_2.findPath();
                    break;
                case MapInfo.B3:
                    underGround_3 = new Navigation(map_underGround_3.underGround_rows, map_underGround_3.underGround_cols, better_transportation, finalNode,MapInfo.B3);
                    //underGround_3.setBlocks(map_underGround_3.underGround_isBlock);
                    other_floor = underGround_3.findPath();
                    break;
                default:
                    break;
            }
*/
            path = floor;
            path.addAll(other_floor);
        }
        else{
            switch (initialNode.getFloor()){
                case MapInfo.B1:
                    underGround_1 = new Navigation(map_underGround_1.underGround_rows,map_underGround_1.underGround_cols,initialNode,finalNode,MapInfo.B1);
                    //underGround_1.setBlocks(map_underGround_1.underGround_isBlock);
                    path = underGround_1.findPath();
                    break;
                case MapInfo.B2:
                    underGround_2 = new Navigation(map_underGround_2.underGround_rows,map_underGround_2.underGround_cols,initialNode,finalNode,MapInfo.B2);
                    //underGround_2.setBlocks(map_underGround_1.underGround_isBlock);
                    path = underGround_2.findPath();
                    break;
                case MapInfo.B3:
                    underGround_3 = new Navigation(map_underGround_3.underGround_rows,map_underGround_3.underGround_cols,initialNode,finalNode,MapInfo.B3);
                    //underGround_3.setBlocks(map_underGround_1.underGround_isBlock);
                    path = underGround_3.findPath();
                    break;
                default:
                    break;
            }
        }

        //경로 로그에 출력하기
        for (Node node : path){
            Log.e("Floor",String.valueOf(node.getFloor())+node.toString());
        }


        setContentView(R.layout.activity_main);
    }

    //노드가 가는 경로 체크하는 함수
    public boolean user_CheckPosition(List<Node> path,int user_row,int user_col){
        boolean check_Position = false;
        for(Node node : path) {
            if (node.getRow() - 2 >= 0 &&
                    node.getCol() - 2 >= 0 &&
                    node.getRow() + 2 <= map_underGround_1.underGround_rows &&
                    node.getCol() + 2 <= map_underGround_1.underGround_cols) {
                if (node.getRow() - 2 <= user_row ||
                        user_row <= node.getRow() + 2 ||
                        node.getCol() - 2 <= user_col ||
                        user_col <= node.getCol() + 2) {
                    check_Position = true;
                }
            }
        }
        if(check_Position){
            return true;
        }
        else{
            return false;
        }
    }

    protected  int compare_Minimum(int[] means_transportation){
        int find_Minimum = means_transportation[0];
        for(int i=1;i<means_transportation.length;i++){
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
}

package com.sxt;

import java.awt.*;

/**
 * 顶层地图类
 * 绘制顶层组件
 * 判断逻辑
 */
public class MapTop {

    //格子位置
    int temp_x;
    int temp_y;

    //重置游戏
    void reGame(){
        for (int i = 1; i <=GameUtil.MAP_W ; i++) {
            for (int j = 1; j <=GameUtil.MAP_H ; j++) {
                GameUtil.DATA_TOP[i][j]=0;
            }
        }
    }

    //判断逻辑
    void logic(){

        temp_x=0;
        temp_y=0;
        if(GameUtil.MOUSE_X>GameUtil.OFFSET && GameUtil.MOUSE_Y>3*GameUtil.OFFSET){
            temp_x = (GameUtil.MOUSE_X - GameUtil.OFFSET)/GameUtil.SQUARE_LENGTH+1;
            temp_y = (GameUtil.MOUSE_Y - GameUtil.OFFSET * 3)/GameUtil.SQUARE_LENGTH+1;
        }

        if(temp_x>=1 && temp_x<=GameUtil.MAP_W
                && temp_y>=1 && temp_y<=GameUtil.MAP_H){
            if(GameUtil.LEFT){
                //覆盖,则翻开
                if(GameUtil.DATA_TOP[temp_x][temp_y]==0){
                    GameUtil.DATA_TOP[temp_x][temp_y]=-1;
                }
                spaceOpen(temp_x,temp_y);
                GameUtil.LEFT=false;
            }
            if(GameUtil.RIGHT){
                //覆盖则插旗
                if(GameUtil.DATA_TOP[temp_x][temp_y]==0){
                    GameUtil.DATA_TOP[temp_x][temp_y]=1;
                    GameUtil.FLAG_NUM++;
                }
                //插旗则取消
                else if(GameUtil.DATA_TOP[temp_x][temp_y]==1){
                    GameUtil.DATA_TOP[temp_x][temp_y]=0;
                    GameUtil.FLAG_NUM--;
                }
                else if(GameUtil.DATA_TOP[temp_x][temp_y]==-1){
                    numOpen(temp_x,temp_y);
                }
                GameUtil.RIGHT=false;
            }
        }
        boom();
        victory();
    }
    //数字翻开
    void numOpen(int x,int y){
        //记录旗数
        int count=0;
        if(GameUtil.DATA_BOTTOM[x][y]>0){
            for (int i = x-1; i <=x+1 ; i++) {
                for (int j = y-1; j <=y+1 ; j++) {
                    if(GameUtil.DATA_TOP[i][j]==1){
                        count++;
                    }
                }
            }
            if(count==GameUtil.DATA_BOTTOM[x][y]){
                for (int i = x-1; i <=x+1 ; i++) {
                    for (int j = y-1; j <=y+1 ; j++) {
                        if(GameUtil.DATA_TOP[i][j]!=1){
                            GameUtil.DATA_TOP[i][j]=-1;
                        }
                        //必须在雷区当中
                        if(i>=1&&j>=1&&i<=GameUtil.MAP_W&&j<=GameUtil.MAP_H){
                            spaceOpen(i,j);
                        }
                    }
                }
            }
        }
    }
    //失败判定  t 表示失败 f 未失败
    boolean boom(){
        if(GameUtil.FLAG_NUM==GameUtil.RAY_MAX){
            for (int i = 1; i <=GameUtil.MAP_W ; i++) {
                for (int j = 1; j <=GameUtil.MAP_H ; j++) {
                    if(GameUtil.DATA_TOP[i][j]==0){
                        GameUtil.DATA_TOP[i][j]=-1;
                    }
                }
            }
        }
        for (int i = 1; i <=GameUtil.MAP_W ; i++) {
            for (int j = 1; j <=GameUtil.MAP_H ; j++) {
                if(GameUtil.DATA_BOTTOM[i][j]==-1&&GameUtil.DATA_TOP[i][j]==-1){
                   GameUtil.state = 2;
                    seeBoom();
                    return true;
                }
            }
        }
        return false;
    }
    //失败显示
    void seeBoom(){
        for (int i = 1; i <=GameUtil.MAP_W ; i++) {
            for (int j = 1; j <=GameUtil.MAP_H ; j++) {
                //底层是雷,顶层不是旗,显示
                if(GameUtil.DATA_BOTTOM[i][j]==-1&&GameUtil.DATA_TOP[i][j]!=1){
                    GameUtil.DATA_TOP[i][j]=-1;
                }
                //底层不是雷,顶层是旗,显示差错旗
                if(GameUtil.DATA_BOTTOM[i][j]!=-1&&GameUtil.DATA_TOP[i][j]==1){
                    GameUtil.DATA_TOP[i][j]=2;
                }
            }
        }
    }
    //胜利判断  t 表示胜利 f 未胜利
    boolean victory(){
        //统计未打开格子数
        int count=0;
        for (int i = 1; i <=GameUtil.MAP_W ; i++) {
            for (int j = 1; j <=GameUtil.MAP_H ; j++) {
                if(GameUtil.DATA_TOP[i][j]!=-1){
                    count++;
                }
            }
        }
        if(count==GameUtil.RAY_MAX){
            GameUtil.state=1;
            for (int i = 1; i <=GameUtil.MAP_W ; i++) {
                for (int j = 1; j <=GameUtil.MAP_H ; j++) {
                    //未翻开,变成旗
                    if(GameUtil.DATA_TOP[i][j]==0){
                        GameUtil.DATA_TOP[i][j]=1;
                    }
                }
            }
            return true;
        }
        return false;
    }

    //打开空格
    void spaceOpen(int x,int y){
        if(GameUtil.DATA_BOTTOM[x][y]==0){
            for (int i = x-1; i <=x+1 ; i++) {
                for (int j = y-1; j <=y+1 ; j++) {
                    //覆盖,才递归
                    if(GameUtil.DATA_TOP[i][j]!=-1){
                        if(GameUtil.DATA_TOP[i][j]==1){GameUtil.FLAG_NUM--;}
                        GameUtil.DATA_TOP[i][j]=-1;
                        //必须在雷区当中
                        if(i>=1&&j>=1&&i<=GameUtil.MAP_W&&j<=GameUtil.MAP_H){
                            spaceOpen(i,j);
                        }
                    }
                }
            }
        }
    }
    //绘制方法
    void paintSelf(Graphics g){
        logic();
        for (int i = 1; i <= GameUtil.MAP_W ; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                //覆盖
                if (GameUtil.DATA_TOP[i][j] == 0) {
                    g.drawImage(GameUtil.top,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }
                //插旗
                if (GameUtil.DATA_TOP[i][j] == 1) {
                    g.drawImage(GameUtil.flag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }
                //差错旗
                if (GameUtil.DATA_TOP[i][j] == 2) {
                    g.drawImage(GameUtil.noflag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }


            }
        }
    }
}

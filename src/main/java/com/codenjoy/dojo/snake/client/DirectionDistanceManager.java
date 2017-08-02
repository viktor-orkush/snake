package com.codenjoy.dojo.snake.client;

import java.util.ArrayList;

/**
 * Created by Viktor on 02.08.2017.
 */
public class DirectionDistanceManager {
    DirectionDistance[] arrDD;
    public DirectionDistanceManager(DirectionDistance[] arrDD){
        this.arrDD = arrDD;
    }

    public int getDtByDirection (String direction){
        for(DirectionDistance dd : arrDD){
            if(dd.getDirection().equals(direction)){
                return dd.getDt();
            }

        }
        return 0;
    }

    public int getIdElementByDirection (String direction){
        for(int i =0; i < arrDD.length; i++){
            if(arrDD[i].getDirection().equals(direction)){
                return i;
            }

        }
        return -1;
    }
}

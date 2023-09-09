package com.mygdx.game;


public class Utils {

	public static boolean isPowerUp(GameObjTypes type){
		
		if(type == GameObjTypes.ARROWCOUNTPOWERUP || type == GameObjTypes.SPEEDPOWERUP || type == GameObjTypes.DRAWPOWERPOWERUP){
			return true;
		}	
		return false;					
	}
	
}

package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

abstract public class Subject {

	protected Array<IObserver>observers;
	
	public Subject(){
		observers = new Array<IObserver>();
	}
	
	
	public void addObserver(IObserver o){
		
		observers.add(o);
	}
	
	
	
	protected void Notify(Message m, GameObject g){
		for(IObserver o : observers){
			o.onNotify(m, g);
		}
	}
	
	
	protected void Notify(Message m, String s){
		for(IObserver o : observers){
			o.onNotify(m, s);
		}
	}
	
	
}

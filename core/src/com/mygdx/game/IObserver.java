package com.mygdx.game;

public interface IObserver {
	public void onNotify(Message m, GameObject k);
	public void onNotify(Message m, String s);
}

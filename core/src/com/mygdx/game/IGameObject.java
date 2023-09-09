package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface IGameObject {
	void draw(Batch batch);
	void update(float deltaTime);
}

package com.vo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends Game {


	public static final float World_Width = 1024;
	public static final float World_Height = 768;

	public void create(){
		setScreen(new GameScreen());
	}



}

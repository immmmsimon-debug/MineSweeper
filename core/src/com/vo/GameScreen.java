package com.vo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {


    //Object that allows us to draw akk iyr graphics
    private SpriteBatch spriteBatch;

    //Objects that allows us to draw shapes
    private ShapeRenderer shapeRenderer;

    //Camera to view the virtual world
    private Camera camera;

    //control how the camera view the world
    //zoom in/out? Keep everything scaled?
    private Viewport viewport;


    private GameBoard myGameBoard;
    public GameScreen(){
        myGameBoard = new GameBoard();
    }





    // run one time at the very beginning all setup should have been here
    @Override
    public void show() {
        camera = new OrthographicCamera(); //2D Camera
        camera.position.set(MyGdxGame.World_Width/2,
                            MyGdxGame.World_Height/2,0);
        camera.update();

        //freeze my view to WORLD_WITDTHxWORLD_HEIGHT, no
        //matter the resolution of the window
        viewport = new FitViewport(MyGdxGame.World_Width,
                MyGdxGame.World_Height,
                camera);

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        //??????, i just know that this was the solution to an anoying issue
        shapeRenderer.setAutoShapeType(true);



    }




    public void clearScreen(){
        Gdx.gl.glClearColor(255,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /*
    this method runs as fast as it can (or to a set FPS)
    repeatedly constantly looped
    things to include in this method:
    process user input
    A.I
    Draw all graphic

     */
    @Override
    public void render(float delta) {
        clearScreen();


        //all drawing of shape must go between begin/end
        shapeRenderer.begin();
        shapeRenderer.setColor(1,1,0,1);
        shapeRenderer.circle(300,300,30);
        shapeRenderer.end();


        //all graphic must go between begin/end
        spriteBatch.begin();
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

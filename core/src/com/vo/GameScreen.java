package com.vo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    private BitmapFont defaultFont = new BitmapFont();

    private boolean debug = true;




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


        //process user input
        handleMouseClick();


        //all drawing of shape must go between begin/end
        shapeRenderer.begin();
        shapeRenderer.end();



        //all graphic must go between begin/end
        spriteBatch.begin();
        myGameBoard.draw(spriteBatch);
        if(debug){
            drawDebugText();
            drawGUI();
        }

        spriteBatch.end();
    }

    private void drawGUI() {
        defaultFont.draw(spriteBatch,"Flag Left: " + myGameBoard.getNumFlags(), 400, 200);
    }

    private void handleMouseClick() {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            myGameBoard.leftClicked(Gdx.input.getX(), Gdx.input.getY());
        }
        else if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT))
        myGameBoard.rightClicked(Gdx.input.getX(), Gdx.input.getY());
    }

    private void drawDebugText() {
        int mouse_x = Gdx.input.getX();
        int mouse_y = Gdx.input.getY();
        defaultFont.draw(spriteBatch, "("+ mouse_x+ "," + mouse_y+ ")", 6,700);
        Location currentLoc = myGameBoard.getTileAt(mouse_x, mouse_y);
        defaultFont.draw(spriteBatch, "[row][col] ", 6,650);
        defaultFont.draw(spriteBatch, "["+(int)(MyGdxGame.World_Width) + "]", 6, 600);
        defaultFont.draw(spriteBatch, "["+myGameBoard.xoffset + "]", 6, 550);
        if(currentLoc != null){
            defaultFont.draw(spriteBatch, "[" + currentLoc.getRow() + "]" + "[" + currentLoc.getCol() + "]", 6, 680);
        }
        else {
            defaultFont.draw(spriteBatch, "[null]" + "[null]", 6, 680);
        }
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

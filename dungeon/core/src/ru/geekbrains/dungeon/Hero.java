package ru.geekbrains.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private ProjectileController projectileController;
    private Vector2 position;
    private float velocity;
    private TextureRegion texture;
    private boolean shootingMode;


    public Hero(TextureAtlas atlas, ProjectileController projectileController) {
        this.position = new Vector2(100, 100);
        this.velocity = 100f;
        this.texture = atlas.findRegion("tank");
        this.projectileController = projectileController;
        this.shootingMode = false;
    }

    public void update(float dt) {

        //System.out.println(position.x + " " + position.y);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            projectileController.activate(position.x, position.y, 200, 0);
            if (shootingMode) projectileController.activate(position.x, position.y, 210, 0);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            if(!shootingMode) shootingMode = true;
            else shootingMode = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)&&!Gdx.input.isKeyPressed(Input.Keys.A)&&!Gdx.input.isKeyPressed(Input.Keys.D)){
            checkBounds(position);
            position.y +=velocity*dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)&&!Gdx.input.isKeyPressed(Input.Keys.A)&&!Gdx.input.isKeyPressed(Input.Keys.D)){
            checkBounds(position);
            position.y -=velocity*dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            checkBounds(position);
            position.x -=velocity*dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            checkBounds(position);
            position.x +=velocity*dt;
        }

    }
    private void checkBounds(Vector2 position){
        if(position.x > 780 ) position.x = 779;
        if(position.x < 20 ) position.x = 19;
        if(position.y > 700 ) position.y = 699;
        if(position.y < 20 ) position.y = 19;

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 20, position.y - 20);
    }
}

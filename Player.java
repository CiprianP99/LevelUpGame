package com.myGame.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject {
    Random r = new Random();
    Handler handler;
    private BufferedImage player_image;
    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        player_image = ss.grabImage(1, 1, 32, 32);
    }
    @Override
    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x, 0, Game.WIDTH - 47);
        y = Game.clamp(y, 0, Game.HEIGHT - 71);
        handler.addObject(new Trail(x, y, ID.Trail, Color.white , 32, 32,0.09f, handler));
        collision();
    }
    private void collision(){
        for(int i = 0; i < handler.object.size(); ++i){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy)
            {
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                    HUD.HEALTH -= 2;
                }
            }
            else if(tempObject.getId() == ID.HardEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                    HUD.HEALTH -= 3;
                }

            }
            else if(tempObject.getId() == ID.EnemyBoss) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    HUD.HEALTH -= 5;
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {
       // g.setColor(Color.white);
      //  g.fillRect((int)x, (int)y, 32, 32);
        g.drawImage(player_image, (int)x, (int)y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
}

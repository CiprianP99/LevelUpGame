package com.myGame.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class HardEnemy extends GameObject {

    private Handler handler;
    private Random r = new Random();
    private BufferedImage hard_enemy_image;
    public HardEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 5;
        velY = 5;
        this.handler = handler;
        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        hard_enemy_image = ss.grabImage(1, 4, 16, 16);

    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
       if (y <= 0 || y >= Game.HEIGHT - 32){
            if(y < 0)
                velY = -(r.nextInt(7) + 1) * -1;
            else
                velY = (r.nextInt(7) + 1) * -1;
        }
        if (x <= 0 || x >= Game.WIDTH - 32) {
            if (x < 0)
                velX = -(r.nextInt(7) + 1) * -1;
            else
                velX = (r.nextInt(7) + 1) * -1;
        }

        handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 16, 16,0.02f, handler));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(hard_enemy_image, (int)x, (int)y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

}

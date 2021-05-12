package com.myGame.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.myGame.main.Game.STATE;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;
    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }
    public void render(Graphics g){
        if(game.gameState == STATE.Menu) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Wave", 240, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);
        }else if(game.gameState == STATE.Help){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 70);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies", 50, 200);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200,64);
            g.drawString("Back", 270, 390);
        }else if(game.gameState == STATE.End){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 180, 70);

            g.setFont(fnt3);
            g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200,64);
            g.drawString("Try Again", 245, 390);
        } if(game.gameState == STATE.Select) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("SELECT DIFFICULTY", 60, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 270, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 270, 290);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }

    }
    public void tick(){

    }
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == STATE.Menu){
            //play button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = STATE.Select;
                AudioPlayer.getSound("men_sound").play();
                return;
            }
            //help button
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = STATE.Help;
                AudioPlayer.getSound("men_sound").play();
            }
            //quit button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                AudioPlayer.getSound("men_sound").play();
                System.exit(1);
            }

        }
        if(game.gameState == STATE.Select){
            //normal button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                game.diff = 0;

                AudioPlayer.getSound("men_sound").play();
            }
            //hard button
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                game.diff = 1;

                AudioPlayer.getSound("men_sound").play();
            }
            //back button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = STATE.Menu;
                AudioPlayer.getSound("men_sound").play();
                return;
            }

        }
        //back button for help
        if(game.gameState == STATE.Help){
            if(mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = STATE.Menu;
                AudioPlayer.getSound("men_sound").play();
                return;
            }
        }
        //try again button for end
        if(game.gameState == STATE.End){
            if(mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = STATE.Menu;
                hud.setScore(0);
                hud.setLevel(1);
                AudioPlayer.getSound("men_sound").play();
            }
        }

    }
    public void mouseReleased(MouseEvent e){

    }
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }

}

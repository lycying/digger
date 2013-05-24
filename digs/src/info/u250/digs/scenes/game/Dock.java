package info.u250.digs.scenes.game;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Dock extends Actor{

	public Sprite actor ;
	Sprite gold;
	public int number =0;
	public int max ;
	
	public Dock(){
		this.actor = new Sprite(Engine.resource("All",TextureAtlas.class).findRegion("dock"));
		this.gold = new Sprite(Engine.resource("All",TextureAtlas.class).findRegion("color"));
		this.gold.setColor(Color.YELLOW);
		this.setSize(actor.getWidth(), actor.getHeight());
		this.max = (int)(this.getWidth()/this.gold.getWidth());
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		actor.setPosition(getX(), getY());
		actor.draw(batch);
		int row = this.number/this.max+1;
		for(int i=0;i<row;i++){
			for(int j=0;j<this.max-i;j++){
				if(i*this.max+j<this.number){
					gold.setPosition(this.getX()+j*(gold.getWidth()+1), this.getY()+this.getHeight()+(gold.getHeight()+1)*i);
					gold.draw(batch);
				}
			}
		}
	}
	
	
	
}

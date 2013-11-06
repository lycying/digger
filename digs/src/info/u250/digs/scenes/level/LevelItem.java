package info.u250.digs.scenes.level;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.graphic.WebColors;
import info.u250.digs.Digs;
import info.u250.digs.scenes.LevelScene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class LevelItem extends  Group{
	private int pack;
	private int level;
	public LevelItem(final LevelScene levelScene,final int pack,final int level ,String levelName){
		this.pack = pack;
		this.level = level;
		
		this.setSize(680, 80);
		TextureAtlas atlas = Engine.resource("All");
		Image bg = new Image( atlas.createPatch("level-item-bg-5"));
		switch(pack){
		case 0:
			bg.setColor(new Color(210f/255f,254f/255f,212f/255f,1f));
			break;
		case 1:
			bg.setColor(new Color(210f/255f,242f/255f,254f/255f,1f));
			break;
		case 2:
			bg.setColor(new Color(254f/255f,238f/255f,210f/255f,1f));
			break;
		}
		bg.setSize(this.getWidth(), this.getHeight());
		Label title = new Label(levelName,new LabelStyle(
				Engine.resource("MenuFont",BitmapFont.class),Color.YELLOW));
		title.setPosition(90, 25);
		
		BitmapFont font = Engine.resource("Font");
		
		Table t = new Table();
		t.setBackground(new NinePatchDrawable(atlas.createPatch("ui-label-bg")));
		t.add(new Image(atlas.findRegion("award")));
		t.add(new Image(atlas.findRegion("npc"))).spaceRight(5);
		t.add(new Label(Digs.RND.nextInt(2000)+"",new LabelStyle(font, Color.WHITE))).spaceRight(5);
		t.add(new Image(atlas.findRegion("dead"))).spaceRight(5);
		t.add(new Label(Digs.RND.nextInt(2000)+"",new LabelStyle(font, Color.WHITE))).spaceRight(5);
		t.add(new Image(atlas.findRegion("time"))).spaceRight(5);
		t.add(new Label(Digs.RND.nextInt(2000)+"",new LabelStyle(font, Color.WHITE))).spaceRight(5);
		t.add(new Image(atlas.findRegion("flag-gold-many"))).spaceRight(5);
		t.add(new Label(Digs.RND.nextInt(2000)+"",new LabelStyle(font, Color.WHITE))).spaceRight(5);
		t.pack();
		t.setPosition(180, 10);
		
		
		
		Image menu_play = new Image(atlas.findRegion("menu_play"));
		menu_play.setPosition(this.getWidth()-menu_play.getWidth()-20,(this.getHeight()-menu_play.getHeight())/2);
		menu_play.setColor(new Color(0.8f,0.8f,0.8f,0.8f));
		
		menu_play.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Engine.getSoundManager().playSound("SoundClick");
				levelScene.startLevel(pack,level);
				super.clicked(event, x, y);
			}
		});
		
		Image lock = new Image(atlas.findRegion("lock"));
		Image pass = new Image(atlas.findRegion("pass"));
		Table t2 = new Table();
		t2.setBackground(new NinePatchDrawable(atlas.createPatch("ui-label-bg")));
		t2.add(new Image(atlas.findRegion("char")));
		t2.add(new Label(Digs.RND.nextInt(2000)+"",new LabelStyle(font, Color.WHITE)));
		t2.pack();
		t2.setPosition(100, 10);
		
		lock.setPosition(45, 15);
		pass.setPosition(45, 15);
		this.addActor(bg);
		this.addActor(title);
		Label levelNumber = new Label(""+(level+1),new LabelStyle(Engine.resource("BigFont",BitmapFont.class),Color.WHITE));
		NinePatch  patch = atlas.createPatch("level-item-bg-4");
		levelNumber.getStyle().background = new NinePatchDrawable(patch);
		levelNumber.pack();
		switch(pack){
		case 0:
			patch.setColor(Color.WHITE);
			break;
		case 1:
			patch.setColor(WebColors.LIME.get());
			break;
		case 2:
			patch.setColor(WebColors.INDIGO.get());
			break;
		}
		this.addActor(levelNumber);
		
		this.addActor(t2);
		
		if(level<10){
			this.addActor(pass);
			this.addActor(menu_play);
			this.addActor(t);
		}else{
			this.addActor(lock);
		}
		
	} 
	public void refresh(){
		String key = "au"+pack+level;
		Engine.getPreferences().getString(key);
	}
}

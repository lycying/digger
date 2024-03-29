package info.u250.digs.scenes.level.pack.guide;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.graphic.WebColors;
import info.u250.c2d.graphic.parallax.ParallaxGroup;
import info.u250.c2d.graphic.parallax.ParallaxLayer;
import info.u250.digs.Digs;
import info.u250.digs.PolygonTable;
import info.u250.digs.scenes.game.HookLevelConfig;
import info.u250.digs.scenes.game.Level;
import info.u250.digs.scenes.game.LevelMakeCallBack;
import info.u250.digs.scenes.game.entity.GoldTowerEntity;
import info.u250.digs.scenes.game.entity.KillCircleEntity;
import info.u250.digs.scenes.game.entity.Npc;
import info.u250.digs.scenes.ui.HintOnScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
/*
 * the killRay will kill the NPC,the KA,the enemy, but the boss can not die
 * when all the npc die, game over
 */
public class Tour3 extends HookLevelConfig {
	public Tour3(){
		this.surface = "002";
		this.width = (int)Engine.getWidth() ;
		this.height = (int)Engine.getHeight();
		this.bottomColor = WebColors.BLACK.get();
		this.topColor = WebColors.DODGER_BLUE.get();
		this.lineHeight = 300;
		this.segment = 10;
		this.gold = 5;
		this.time = 3*60;
		this.npc = 5;
		
		levelMakeCallback = new LevelMakeCallBack() {
			@Override
			public void before(Level level) {
				TextureAtlas atlas = Engine.resource("All");
				final ParallaxGroup pbg = new ParallaxGroup(Engine.getWidth(), Engine.getHeight(), new Vector2(50,0));
				pbg.addActor(new ParallaxLayer(pbg, new Image(atlas.findRegion("cloud")), new Vector2(1,1), new Vector2(500,1000), new Vector2(300,350)));
				level.addActor(pbg);
				
				GoldTowerEntity dock = new GoldTowerEntity();
				dock.setY(lineHeight);
				level.addGoldDock(dock);
				
				{
					HintOnScreen hint = new HintOnScreen(Engine.getLanguagesManager().getString("java.level3.hint"),"hint2",Color.WHITE,250);
					hint.pack();
					hint.setPosition(350, 350);
					level.addActor(hint);
				}
			}
			
			@Override
			public void mapMaker(Pixmap terr, Pixmap gold) {
				gold.setColor(Color.YELLOW);
				Polygon polygon =  PolygonTable.IMG_ISLAND6();
				polygon.setScale(0.5f, 0.5f);
				polygon.setPosition(650, 400);
				drawPolygon(polygon, gold);
			}

			@Override
			public void after(Level level) {
				
				KillCircleEntity ray = new KillCircleEntity(400, 150, 100,Color.WHITE);
				level.addKillCircle(ray);
				
				for(int i=0;i<npc;i++){
					Npc e = new Npc();
					e.init(level);
					e.setDirection(1);
					e.setPosition(100,height + Digs.RND.nextFloat()*300);
					level.addNpc(e);
				}
			}
		};
	}
}

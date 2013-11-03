package info.u250.digs.scenes.level.pack.guide;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.graphic.WebColors;
import info.u250.digs.Digs;
import info.u250.digs.PolygonTable;
import info.u250.digs.scenes.game.Level;
import info.u250.digs.scenes.game.LevelConfig;
import info.u250.digs.scenes.game.entity.EnemyMiya;
import info.u250.digs.scenes.game.entity.GoldTowerEntity;
import info.u250.digs.scenes.game.entity.Npc;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Polygon;

public class Tour9 extends LevelConfig {
	public Tour9(){
		this.surface = "texs/pink029.jpg";
		this.width = (int)Engine.getWidth() ;
		this.height = (int)Engine.getHeight();
		this.bottomColor = WebColors.LIGHT_CORAL.get();
		this.topColor = WebColors.AQUA.get();
		this.lineHeight = 300;
		this.segment = 1;
		
		
		callback = new LevelCallBack() {
			@Override
			public void after(Level level) {
				for(int i=0;i<10;i++){
					Npc e = new Npc();
					e.init(level);
					e.setPosition(100, Engine.getHeight() + Digs.RND.nextFloat()*500);
					level.addNpc(e);
				}
				for(int i=0;i<10;i++){
					EnemyMiya e = new EnemyMiya();
					e.init(level);
					e.setPosition(430 + Digs.RND.nextFloat()*100, 100);
					level.addEnemyMiya(e);
				}
			}
			
			@Override
			public void mapMaker(Pixmap terr, Pixmap gold) {
				gold.setColor(Color.CLEAR);
				Polygon polygon =  PolygonTable.IMG_ISLAND2;
				polygon.setScale(3f,1f);
				polygon.setPosition(350, 50);
				drawPolygon(polygon, terr);
			}

			@Override
			public void before(Level level) {
				GoldTowerEntity dock = new GoldTowerEntity();
				dock.setPosition(0,lineHeight);
				level.addGoldDock(dock);
			}
		};
	}
}
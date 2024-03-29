package info.u250.digs.scenes.level.pack.guide;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.graphic.WebColors;
import info.u250.digs.Digs;
import info.u250.digs.PolygonTable;
import info.u250.digs.scenes.game.Level;
import info.u250.digs.scenes.game.LevelMakeCallBack;
import info.u250.digs.scenes.game.LineLevelConfig;
import info.u250.digs.scenes.game.entity.GoldTowerEntity;
import info.u250.digs.scenes.game.entity.Npc;
import info.u250.digs.scenes.game.entity.StepladderEntity;
import info.u250.digs.scenes.ui.HintOnScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
/*
 * this tour show the lager map
 *
 */
public class Tour9 extends LineLevelConfig {
	public Tour9(){
		this.surface = "008";
		this.width = (int)Engine.getWidth()  -SCROLL_WIDTH;
		this.height = (int)Engine.getHeight()+400;
		this.bottomColor = WebColors.SEA_GREEN.get();
		this.topColor = WebColors.BLACK.get();
		this.lineHeight = 300+400;
		
		this.gold = 10;
		this.time = 3*60;
		this.npc = 10;
		
		levelMakeCallback = new LevelMakeCallBack() {
			@Override
			public void after(Level level) {
				{
					HintOnScreen hint = new HintOnScreen(Engine.getLanguagesManager().getString("java.level9.hint"),"hint5",Color.BLACK,150);
					hint.pack();
					hint.setColor(new Color(1,1,1,0.8f));
					hint.addAction(Actions.forever(Actions.sequence(Actions.moveBy(10, 0,0.2f),Actions.moveBy(-10, 0,0.2f))));
					hint.setPosition(600, 200);
					level.addActor(hint);
				}
				{
					StepladderEntity ladder = new StepladderEntity(40, 200,50);
					level.addStepladder(ladder);
				}
				
				for(int i=0;i<npc;i++){
					Npc e = new Npc();
					e.init(level);
					e.setPosition(100, Engine.getHeight()+ 400 + Digs.RND.nextFloat()*500);
					level.addNpc(e);
				}
			}
			
			@Override
			public void mapMaker(Pixmap terr, Pixmap gold) {
				gold.setColor(Color.YELLOW);
				Polygon polygon =  PolygonTable.DIALOG_OK();
				polygon.setScale(1f,1f);
				polygon.setPosition(750, 200);
				drawPolygon(polygon, gold);
				drawPixmapDeco(terr, "tree5", 700, lineHeight-2);
			}

			@Override
			public void before(Level level) {
				GoldTowerEntity dock = new GoldTowerEntity();
				dock.setY(lineHeight);
				level.addGoldDock(dock);
			}
		};
	}
}

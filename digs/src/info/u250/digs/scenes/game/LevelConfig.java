package info.u250.digs.scenes.game;

import info.u250.c2d.engine.Engine;
import info.u250.digs.PolygonTable;
import info.u250.digs.scenes.game.entity.GoldTowerEntity;
import info.u250.digs.scenes.game.entity.TeleportEntity;
import info.u250.digs.scenes.game.entity.KillCircleEntity;
import info.u250.digs.scenes.game.entity.Npc;
import info.u250.digs.scenes.game.entity.StepladderEntity;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.ShortArray;

public class LevelConfig {
	//for level information auto make
	public int pack = 0;
	public int idx  = 0;
	public String idxName  = "";
	//auto information auto make end
	
	public int aim = 10;
	public String surface = "vg/1.svg";
	public Color bottomColor = null;
	public Color topColor = null;
	
	public int width = (int)Engine.getWidth();
	public int height = (int)Engine.getHeight();
	
	public int lineHeight = 450;
	public int segment = 8;
	
	public LevelCallBack callback = new LevelCallBack() {
		Random random = new Random();
		@Override
		public void after(Level level) {
			KillCircleEntity ray = new KillCircleEntity(550, 50, 100,Color.WHITE);
			level.addKillCircle(ray);
			KillCircleEntity ray2 = new KillCircleEntity(300, 0,50,Color.BLUE);
			level.addKillCircle(ray2);
			KillCircleEntity ray3 = new KillCircleEntity(100, 0,76,Color.GREEN);
			level.addKillCircle(ray3);
			KillCircleEntity ray4 = new KillCircleEntity(800, -20,88,Color.CYAN);
			level.addKillCircle(ray4);
			
			StepladderEntity ladder = new StepladderEntity(15, 200,100);
			level.addStepladder(ladder);
			
			for(int i=0;i<200;i++){
				Npc e = new Npc();
				e.init(level);
				e.setPosition(200+random.nextFloat()*200, Engine.getHeight() + random.nextFloat()*100);
				level.addNpc(e);
			}
			
			TeleportEntity inout = new TeleportEntity(300,150,500,250);
			level.addInOutTrans(inout);
			
		}
		
		@Override
		public void mapMaker(Pixmap terr, Pixmap gold) {
			gold.setColor(Color.YELLOW);
			Polygon polygon =  PolygonTable.WALLACE_128;
			polygon.setPosition(200, 50);
			drawPolygon(polygon, gold);
			
			gold.setColor(Color.CYAN);
			Polygon bomb =  PolygonTable.ADIUM;
			bomb.setPosition(400, 50);
			bomb.setRotation(50);
			drawPolygon(bomb, gold);
		}

		@Override
		public void before(Level level) {
			GoldTowerEntity dock = new GoldTowerEntity();
			dock.setY(LevelConfig.this.lineHeight);
			level.addGoldDock(dock);
		}
	};
	
	public abstract class LevelCallBack{
		public abstract void before(Level level);
		public abstract void after(Level level);
		public abstract void mapMaker(Pixmap terr,Pixmap gold);
		public void drawPolygon(Polygon polygon,Pixmap pixmap){
			polygon.setPosition(polygon.getX(),pixmap.getHeight()-polygon.getY()-polygon.getBoundingRectangle().height);
			float[] polygonVertices = polygon.getTransformedVertices();
			ShortArray array = ear.computeTriangles(polygonVertices);
			for(int i=0;i<array.size;i+=3){
				int x1 = (int)polygonVertices[2*array.get(i)];
				int y1 = (int)polygonVertices[2*array.get(i)+1];
				int x2 = (int)polygonVertices[2*array.get(i+1)];
				int y2 = (int)polygonVertices[2*array.get(i+1)+1];
				int x3 = (int)polygonVertices[2*array.get(i+2)];
				int y3 = (int)polygonVertices[2*array.get(i+2)+1];
				pixmap.fillTriangle(x1, y1, x2, y2, x3, y3);
			}
		}
		EarClippingTriangulator ear = new EarClippingTriangulator();
	}
}

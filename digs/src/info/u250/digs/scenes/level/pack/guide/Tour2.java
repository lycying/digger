package info.u250.digs.scenes.level.pack.guide;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.graphic.WebColors;
import info.u250.digs.PolygonTable;
import info.u250.digs.scenes.game.Level;
import info.u250.digs.scenes.game.LevelConfig;
import info.u250.digs.scenes.game.LevelMakeCallBack;
import info.u250.digs.scenes.game.Level.FingerMode;
import info.u250.digs.scenes.game.callback.DefaultLevelCompleteCallback;
import info.u250.digs.scenes.game.entity.GoldTowerEntity;
import info.u250.digs.scenes.game.entity.Npc;
import info.u250.digs.scenes.ui.HintOnScreen;
import info.u250.digs.scenes.ui.InnerMask;
import info.u250.digs.scenes.ui.LineActor;
import info.u250.digs.scenes.ui.ParticleEffectActor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
/*
 * this tour will guide you to make a path for your npc to the sky
 * then carry golds to home
 */
public class Tour2 extends LevelConfig {
	final int TYPE_INF_GOLD = 1;
	final int TYPE_INF_BUTTON = 2;
	final int TYPE_INF_PATH = 3;
	final int TYPE_INF_DONE = 4;
	final int TYPE_INF_YOURTURN = 5;
	int type = TYPE_INF_GOLD;
	public Tour2(){
		this.surface = "qvg/001.jpg";
		this.width = (int)Engine.getWidth() ;
		this.height = (int)Engine.getHeight();
		this.bottomColor = WebColors.BLACK.get();
		this.topColor = WebColors.KHAKI.get();
		this.lineHeight = 300;
		this.segment = 1;
		this.gold = 1;
		this.time = 3*60;
		this.npc = 1;
		levelCompleteCallback = new DefaultLevelCompleteCallback(){
			@Override
			public Actor infoBorad(Level level) {
				return new Group();
			}
		};
		
		levelMakeCallback = new LevelMakeCallBack() {
			final TextureAtlas atlas = Engine.resource("All");
			final Image finger = new Image(atlas.findRegion("finger"));
			final LineActor line = new LineActor(200, 300, 685, 416);
			final BitmapFont font = Engine.resource("FxFont");
			final Label lblText = new Label("", new LabelStyle(font, Color.YELLOW));
			public void upAndDown(){
				finger.clearActions();
				finger.addAction(Actions.forever(Actions.sequence(Actions.moveBy(0, -20,0.2f),Actions.moveBy(0, 20,0.2f))));
			}
			public void drawLine(){
				finger.clearActions();
				finger.addAction(Actions.forever(Actions.sequence(Actions.moveTo(200, lineHeight-60),Actions.moveTo(700, 380,2f))));
			}
			@Override
			public void after(final Level level) {
				{
					HintOnScreen hint = new HintOnScreen("By using this tool(left top),make road ! Not too high...","level-item-bg-5",Color.BLACK,300);
					Image image = new Image(Engine.resource("All",TextureAtlas.class).findRegion("btn-fill-1"));
					image.setSize(100, 100);
					hint.add(image);
					hint.pack();
					hint.setPosition(400, 40);
					level.addActor(hint);
					}
				
				final InnerMask mask = new InnerMask(width, height);
				mask.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						if(type == TYPE_INF_GOLD){
							mask.setRect(new Rectangle(650,390,110,110));
							level.getGame().reallyStartLevel();
							level.getGame().addActor(finger);
							finger.setPosition(680, 340);
							upAndDown();
							type = TYPE_INF_BUTTON;
							lblText.setPosition(570, 290);
							lblText.setText("This is gold");
							level.getGame().addActor(lblText);
						}else if(type==TYPE_INF_BUTTON){
							mask.setRect(new Rectangle(80,460,80,80));
							type = TYPE_INF_PATH;
							level.getGame().addActor(finger);
							finger.setPosition(100, 400);
							upAndDown();
							lblText.setPosition(280, 460);
							lblText.setText("This tool used to fill");
							level.getGame().addActor(lblText);
							level.getGame().setFingerMode(FingerMode.Fill);//note we switch the button function here.
						}else if(type==TYPE_INF_PATH){
							mask.setRect(new Rectangle(80,240,700,250));
							type=TYPE_INF_DONE;
							level.getGame().addActor(line);
							line.setColor(new Color(199/255f,140/255f,50f/255,1.0f));
							level.getGame().addActor(finger);
							drawLine();
							lblText.setPosition(180, 160);
							lblText.setText("Fill using with your finger");
							level.getGame().addActor(lblText);
						}else if(type==TYPE_INF_DONE){
							mask.remove();
							finger.remove();
							line.remove();
							type = TYPE_INF_YOURTURN;
							lblText.setPosition(100, 120);
							lblText.setText("Ok it's your turn");
							level.getGame().addActor(lblText);
							lblText.getStyle().fontColor = Color.BLUE;
						}
						super.clicked(event, x, y);
					}
				});
				
				level.getGame().addActor(mask);
				
				final Npc e = new Npc(){
					@Override
					public void draw(Batch batch, float parentAlpha) {
						if(type==TYPE_INF_DONE){
							this.direction = 1;
							this.setPosition(100, 300);
						}
						super.draw(batch, parentAlpha);
					}
				};
				e.init(level);
				e.setPosition(200, 450);
				e.setDirection(1);
				level.addNpc(e);
			}
			
			@Override
			public void mapMaker(Pixmap terr, Pixmap gold) {
				gold.setColor(Color.YELLOW);
				Polygon polygon =  PolygonTable.WONDER_PART_8();
				polygon.setScale(1f, 1f);
				polygon.setPosition(650, 400);
				drawPolygon(polygon, gold);
			}

			@Override
			public void before(Level level) {
				ParticleEffect e = Engine.resource("Effect");
				ParticleEffectActor p = new ParticleEffectActor(e,"snow");
				p.setPosition(0, Engine.getHeight());
				level.addActor(p);
				
				GoldTowerEntity dock = new GoldTowerEntity();
				dock.setY(lineHeight);
				level.addGoldDock(dock);
				
				
			}
		};
	}
}

package info.u250.digs.scenes;

import info.u250.c2d.engine.CoreProvider.CoreEvents;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.SceneStage;
import info.u250.c2d.engine.events.Event;
import info.u250.c2d.engine.events.EventListener;
import info.u250.c2d.graphic.WebColors;
import info.u250.c2d.graphic.background.SimpleMeshBackground;
import info.u250.c2d.graphic.surfaces.SurfaceData;
import info.u250.c2d.graphic.surfaces.TriangleSurfaces;
import info.u250.digs.Digs;
import info.u250.digs.DigsEngineDrive;
import info.u250.digs.IO;
import info.u250.digs.scenes.game.Level;
import info.u250.digs.scenes.game.LevelConfig;
import info.u250.digs.scenes.game.LevelMakeCallBack;
import info.u250.digs.scenes.game.entity.Boss;
import info.u250.digs.scenes.game.entity.GoldTowerEntity;
import info.u250.digs.scenes.game.entity.Npc;
import info.u250.digs.scenes.start.Finger;
import info.u250.digs.scenes.ui.ParticleEffectActor;
import info.u250.digs.scenes.ui.SpineActor;
import info.u250.digs.scenes.ui.Water;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

public class StartUpScene extends SceneStage{
	DigsEngineDrive drive;
	TextureAtlas atlas = null;
	public Level level;
	public Group terrainContainer;
	float deltaAppend;
	SimpleMeshBackground meshBackground ;
	final TriangleSurfaces surface2;
	final TriangleSurfaces surface3;
	final SpineActor wmr;
	float time;

	public Water water = new Water( 201, 130, 
			new Color(151f/255f,196f/255f,188f/255f,0.5f),
			new Color(1,1,1,0.5f));
	public StartUpScene(final DigsEngineDrive drive){
		loadTextures();
		
		this.drive = drive;

		
		atlas = Engine.resource("All");
		
		meshBackground = new SimpleMeshBackground(new Color(1, 1, 1, 1f),WebColors.CADET_BLUE.get());

		
//		final ParallaxGroup pbg = new ParallaxGroup(Engine.getWidth(), Engine.getHeight(), new Vector2(-50,0));
//		pbg.addActor(new ParallaxLayer(pbg, new Image(atlas.findRegion("cloud")), new Vector2(1,1), new Vector2(30,1000), new Vector2(0,320)));
//		this.addActor(pbg);
		
//		final SurfaceData data = new SurfaceData();
//		data.primitiveType = GL10.GL_TRIANGLE_STRIP;
//		data.texture="Texture";
//		data.points = new Array<Vector2>(){{
//			add(new Vector2(-27.005554f,300f));
//			add(new Vector2(-20,-4));
//			add(new Vector2(119,250));
//			add(new Vector2(200.99362f,-14f));
//			add(new Vector2(293.00104f,300));
//			add(new Vector2(356f,-9f));
//			add(new Vector2(360f,310));
//			add(new Vector2(380,-9f));
//			add(new Vector2(400,300));
//			add(new Vector2(458f,-9f));
//			add(new Vector2(510f,250));
//			add(new Vector2(556.0f,-7f));
//			add(new Vector2(593f,250));
//			add(new Vector2(650f,-53f));
//			add(new Vector2(700f,290));
//			add(new Vector2(735f,-53f));
//			add(new Vector2(800f,290));
//			add(new Vector2(850f,0));
//			add(new Vector2(900f,290));
//			add(new Vector2(960f,0));
//			add(new Vector2(1024,350));
//		}};
//		surface  = new TriangleSurfaces(data);
		final SurfaceData data2 = new SurfaceData();
		data2.primitiveType = GL10.GL_TRIANGLE_STRIP;
		data2.texture="Texture2";
		data2.points = new Array<Vector2>(){{
			add(new Vector2(-27.005554f,660f));
			add(new Vector2(0,380));
			add(new Vector2(15,600));
			add(new Vector2(30,370));
			add(new Vector2(60,600));
			add(new Vector2(120,480));
			add(new Vector2(240,620));
			add(new Vector2(280,500));
			add(new Vector2(320,620));
			add(new Vector2(360,480));
			add(new Vector2(480,700));
			add(new Vector2(520,520));
			add(new Vector2(590,700));
			add(new Vector2(620,500));
			add(new Vector2(650,700));
			add(new Vector2(680,400));
			add(new Vector2(730,700));
			add(new Vector2(740,450));
			add(new Vector2(750,700));
			add(new Vector2(770,450));
			add(new Vector2(790,700));
			add(new Vector2(810,420));
			add(new Vector2(820,700));
			add(new Vector2(830,460));
			add(new Vector2(835,700));
			add(new Vector2(850,460));
			add(new Vector2(890,700));
			add(new Vector2(900,520));
			add(new Vector2(930,700));
			add(new Vector2(940,500));
			add(new Vector2(1024,600));
		}};
		surface2  = new TriangleSurfaces(data2);
		
		final SurfaceData data3 = new SurfaceData();
		data3.primitiveType = GL10.GL_TRIANGLE_STRIP;
		data3.texture="Texture2";
		data3.points = new Array<Vector2>(){{
			add(new Vector2(-27.005554f,-100));
			add(new Vector2(0,50));
			add(new Vector2(15,-100));
			add(new Vector2(30,40));
			add(new Vector2(60,-100));
			add(new Vector2(120,30));
			add(new Vector2(240,-100));
			add(new Vector2(280,50));
			add(new Vector2(320,-100));
			add(new Vector2(360,45));
			add(new Vector2(480,-100));
			add(new Vector2(520,30));
			add(new Vector2(590,-100));
			add(new Vector2(620,25));
			add(new Vector2(650,-100));
			add(new Vector2(680,30));
			add(new Vector2(730,-100));
			add(new Vector2(740,35));
			add(new Vector2(750,-100));
			add(new Vector2(770,45));
			add(new Vector2(790,-100));
			add(new Vector2(810,20));
			add(new Vector2(820,-100));
			add(new Vector2(830,24));
			add(new Vector2(835,-100));
			add(new Vector2(850,12.5f));
			add(new Vector2(890,-100));
			add(new Vector2(900,50));
			add(new Vector2(930,-100));
			add(new Vector2(940,50));
			add(new Vector2(945,-100));
			add(new Vector2(950,640));
			add(new Vector2(1024,-100));
		}};
		surface3  = new TriangleSurfaces(data3);
		
		wmr = new SpineActor("null", atlas,"idle",0.2f);
		wmr.setColor(WebColors.DARK_GREEN.get());
		wmr.setX(68);
		wmr.setY(305);
		
		terrainContainer = new Group();
		this.addActor(terrainContainer);
		genTerrain();
		
		Image logo = new Image(atlas.findRegion("logo"));
		logo.setPosition(300, 320);
		logo.addAction(Actions.forever(Actions.sequence(
				Actions.moveBy(0,-80,0.5f,Interpolation.swingIn),
				Actions.moveBy(0,80,0.5f,Interpolation.swingOut)
		)));
		this.addActor(logo);
		
		Button play = new Button(new TextureRegionDrawable(atlas.findRegion("btn-play")), new TextureRegionDrawable(atlas.findRegion("btn-play")));
		play.setSize(play.getPrefWidth(), play.getPrefHeight());
		play.setPosition(Engine.getWidth()-play.getWidth(), 260);

		
		Finger finger = new Finger(atlas.findRegion("finger"), this);
		this.addActor(finger);
	
		
		
		play.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				StartUpScene.this.drive.setToLevelScene();
				Engine.getSoundManager().playSound("SoundClick");
				super.clicked(event, x, y);
			}
		});
		
		
		final TextureRegionDrawable sound_flag_on = new TextureRegionDrawable(atlas.findRegion("sound-on"));
		final TextureRegionDrawable sound_flag_off = new TextureRegionDrawable(atlas.findRegion("sound-off"));
		final Image sound_flag ; 
		if(IO.soundOn()){
			sound_flag = new Image(sound_flag_on);
			IO.enableSound();
		}else{
			sound_flag = new Image(sound_flag_off);
			IO.disableSound();
		}
		sound_flag.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(sound_flag.getDrawable() == sound_flag_off){
					sound_flag.setDrawable(sound_flag_on);
					Engine.getSoundManager().playSound("SoundClick");
					IO.enableSound();
				}else{
					sound_flag.setDrawable(sound_flag_off);
					Engine.getSoundManager().playSound("SoundClick");
					IO.disableSound();
				}
				super.clicked(event, x, y);
			}
		});
		sound_flag.setPosition(20, 130);
		this.addActor(sound_flag);
		
		final Image about = new Image(atlas.findRegion("about"));
		about.setPosition(860, 440);
		about.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.6f,0.5f),Actions.alpha(1f,0.5f))));
		about.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Engine.getSoundManager().playSound("SoundClick");
				drive.setToAboutScene();
				super.clicked(event, x, y);
			}
		});
		this.addActor(about);
		
		
		
		
		
		this.addActor(play);
		
		ParticleEffect e = Engine.resource("Effect");
		ParticleEffectActor p = new ParticleEffectActor(e,"thousand");
		p.setPosition(50, Engine.getHeight());
		this.addActor(p);
		
		
		Engine.getEventManager().register(CoreEvents.SystemResume, new EventListener(){
			@Override
			public void onEvent(Event event) {
				if(null!=level)level.reload();
			}
		});
	}
	
	List<String> texs = new ArrayList<String>();
	void loadTextures(){
//		java.io.File file = new java.io.File("assets/texs");
//		for(String s:file.list()){
//			System.out.println("texs.add(\"texs/"+s+"\");");
//		}
		texs.add("qvg/000.png");
	}
	int texs_index = -1;
	void genTerrain(){
		terrainContainer.clear();
		if(null!=level){
			level.setTouchable(Touchable.disabled);
			level.dispose();
		}
		level = null;
		wmr.remove();
		final LevelConfig config = new LevelConfig();
		config.levelMakeCallback = new LevelMakeCallBack() {
			Random random = new Random();
			@Override
			public void after(Level level) {
				for(int i=0;i<1;i++){
					Boss e = new Boss();
					e.setBossLandHeight(50);
					e.init(level);
					e.setPosition(500+Digs.RND.nextFloat()*200, Engine.getHeight() + Digs.RND.nextFloat()*100);
					level.addBoss(e);
				}
				for(int i=0;i<20;i++){
					Npc e = new Npc();
					e.init(level);
					e.setPosition(400+random.nextFloat()*50, Engine.getHeight() + random.nextFloat()*300);
					level.addNpc(e);
				}
				
			}
			@Override
			public void before(Level level) {
				GoldTowerEntity dock = new GoldTowerEntity();
				dock.setY(config.lineHeight);
				level.addGoldDock(dock);
				StartUpScene.this.addActor(wmr);
			}
			@Override
			public void mapMaker(Pixmap terr, Pixmap gold) {
				// TODO Auto-generated method stub
				
			}
		};
		if(-1==texs_index){
			config.surface = "qvg/0.jpg";
			texs_index++;
		}else{
			config.surface = texs.get(new java.util.Random().nextInt(texs.size()));
		}		
		if(texs_index>texs.size()-1){
			texs_index = 0;
		}
		config.segment = 8;
		config.lineHeight = 200;
		config.width = (int)Engine.getWidth();
		level = new Level(null,config);
		terrainContainer.addActor(level);
	}
	@Override
	public void act(float delta) {
		deltaAppend += delta;
		if(deltaAppend>30f){
			genTerrain();
			deltaAppend = 0;
		}
		super.act(delta);
		water.update(delta);
	}
	@Override
	public void draw() {
		meshBackground.render(Engine.getDeltaTime());
//		surface.render(Engine.getDeltaTime());
		super.draw();
		water.draw();
		surface2.render(Engine.getDeltaTime());
		surface3.render(Engine.getDeltaTime());
	}
	@Override
	public boolean keyDown(int keycode) {
		if (Gdx.app.getType() == ApplicationType.Android) {
			if (keycode == Keys.BACK) {
				System.exit(0);
			}
		} else {
			if (keycode == Keys.DEL) {
				System.exit(0);
			}
		}
		return super.keyDown(keycode);
	}
	
	@Override
	public void show() {
		super.show();
		Engine.getMusicManager().playMusic("MusicBackground", true);
	}
}

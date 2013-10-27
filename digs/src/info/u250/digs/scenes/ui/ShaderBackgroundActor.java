package info.u250.digs.scenes.ui;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ShaderBackgroundActor extends Actor {
	ShaderProgram shader ;
	FrameBuffer frameBuffer;
	Mesh mesh ;
	
	public ShaderBackgroundActor(){
		shader = createDefaultShader();
		mesh = new Mesh(true,4,6,new VertexAttribute(VertexAttributes.Usage.Position, 2, "position"));
		mesh.setVertices(new float[] { -1, -1,  -1 , 1,  1, -1 , 1 ,1});
		mesh.setIndices(new short[]{0, 1, 2, 2, 1, 3});
		frameBuffer = new FrameBuffer(Format.RGB565, (int)(Engine.getWidth()/4), (int)(Engine.getHeight()/4), false);
		resolution.set(frameBuffer.getWidth(), frameBuffer.getHeight());
	}
	float accum = 0;
	float accum2 = 0;
	@Override
	public void act(float delta) {
		accum+=delta;
		accum2 += delta;
	}
	Vector2 resolution= new Vector2(Engine.getWidth(),Engine.getHeight());
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if(accum2>0.1f){
			accum-=0.1f;
		
		frameBuffer.begin();
		GL20 gl = Gdx.gl20;
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glViewport(0, 0, frameBuffer.getWidth(), frameBuffer.getHeight());
		Gdx.gl20.glClearColor(0f, 1f, 0f, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shader.begin();
		shader.setUniformf("resolution", resolution );
		shader.setUniformf("time", accum);
		mesh.render(shader, GL20.GL_TRIANGLES);
		shader.end();
		frameBuffer.end();
		Gdx.graphics.getGL20().glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Engine.getSpriteBatch().end();
		Engine.getSpriteBatch().begin();
		Engine.getSpriteBatch().draw(frameBuffer.getColorBufferTexture(), 0, 0,Engine.getWidth(),Engine.getHeight());
		}else{
			Engine.getSpriteBatch().draw(frameBuffer.getColorBufferTexture(), 0, 0,Engine.getWidth(),Engine.getHeight());
		}
	}
	public ShaderProgram createDefaultShader () {
		String vertexShader = "attribute vec3 position; void main() { gl_Position = vec4( position, 1.0 ); }";
		String fragmentShader = "#ifdef GL_ES\r\n" + 
				"precision mediump float;\r\n" + 
				"#endif\r\n" + 
				"\r\n" + 
				"uniform float time;\r\n" + 
				"uniform vec2 resolution;\r\n" + 
				"\r\n" + 
				"void main( void ) {\r\n" + 
				"	vec2 position = ( gl_FragCoord.xy / resolution.xy );\r\n" + 
				"\r\n" + 
				"	float color = 1.0;\r\n" + 
				"	color += sin( position.x * cos( time / 5.0 ) * 30.0 ) + sin( position.y * cos( time / 100.0 ) * 100.0 );\r\n" + 
				"	color += sin( position.x * cos( time / 100.0 ) * 100.0 ) + cos( position.y * sin( time / 10.0 ) * 15.0 );\r\n" + 
				"		\r\n" + 
				"	gl_FragColor = vec4(color * .2, color * .4, color * .4, 1.0);\r\n" + 
				"}\r\n" + 
				"";

		ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
		if (shader.isCompiled() == false) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
		return shader;
	}
}
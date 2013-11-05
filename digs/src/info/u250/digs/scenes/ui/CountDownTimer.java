package info.u250.digs.scenes.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
/**
 * default 5min
 */
public class CountDownTimer {
	private static final int Duration = 5*60; //5 min
	private int sceonds ;
	private Label label;
	public CountDownTimer(Label label,int duration){
		this.sceonds = duration;
		this.label = label;
	}
	public CountDownTimer(Label label){
		this(label, Duration);
	}
	public void start(){
		Timer.schedule(new Task() {
			@Override
			public void run() {
				try{
					CountDownTimer.this.sceonds--;
					int min = sceonds/60;
					int sce = sceonds%60;
					if(sce<10){
						label.setText(min+":0"+sce);
					}else{
						label.setText(min+":"+sce);
					}
				}catch(Exception ex){
					// do nothing
				}
			}
		}, 0, 1);
	}
	public int getSceonds() {
		return sceonds;
	}
	
}

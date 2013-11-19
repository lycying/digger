package info.u250.digs;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class DigsPacker {

	public static void main(String[] args) {
		String input = "raw/";
		String output = "assets/data/";
		
		TexturePacker2.Settings setting = new TexturePacker2.Settings();
		setting.debug = false;
		setting.stripWhitespaceX = true;
		setting.stripWhitespaceY = true;
		setting.maxWidth = 1024;
		setting.maxHeight= 1024;
		TexturePacker2.process(setting,input, output, "all");
		
	}

}

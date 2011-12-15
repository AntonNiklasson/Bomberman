package com.anwpteuz.bomberman;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

public class AssetsManager {
	// Singleton pattern
	private AssetsManager() {}
	private static AssetsManager instance;
	public static AssetsManager getInstance() {
		if(instance == null) instance = new AssetsManager();
		return instance;
	}
	
	// Data members
	private HashMap<String, Image> images = new HashMap<String, Image>();
	
	// Methods
	public Image loadImage(String key) {
		if(images.containsKey(key) == false) {
			Image image = Toolkit.getDefaultToolkit().getImage("assets/textures/" + key + ".png");
			images.put(key, image);
			return image;
		}
		
		return images.get(key);
	}
}

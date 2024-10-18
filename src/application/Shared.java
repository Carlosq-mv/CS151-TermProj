package application;

import javafx.scene.layout.HBox;

// all references to share between many controllers lie here (uses singleton pattern)
public class Shared {
	private static Shared shared = new Shared();
	private HBox mainBox;
	
	private Shared() {}
	
	
	public static Shared getInstance() {
		return shared;
	}


	public HBox getMainBox() {
		return mainBox;
	}


	public void setMainBox(HBox mainBox) {
		this.mainBox = mainBox;
	}

}

package presentation;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class VBoxWithStyle extends VBox {
	
	private String color = "#191919";

	public VBoxWithStyle() {
		super();
		super.setBackground(new Background(new BackgroundFill(Color.web(color), new CornerRadii(0), Insets.EMPTY)));
	}

	public VBoxWithStyle(double arg0, Node... arg1) {
		super(arg0, arg1);
		super.setBackground(new Background(new BackgroundFill(Color.web(color), new CornerRadii(0), Insets.EMPTY)));
	}

	public VBoxWithStyle(double arg0) {
		super(arg0);
		super.setBackground(new Background(new BackgroundFill(Color.web(color), new CornerRadii(0), Insets.EMPTY)));
	}

	public VBoxWithStyle(Node... arg0) {
		super(arg0);
		super.setBackground(new Background(new BackgroundFill(Color.web(color), new CornerRadii(0), Insets.EMPTY)));
	}

}

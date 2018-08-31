package br.lry.components.pdv.linx;

import com.borland.silktest.jtf.BaseState;
import org.junit.Before;
import org.junit.Test;
import com.borland.silktest.jtf.Control;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import sun.security.util.KeyUtil;

import com.borland.silktest.jtf.Desktop;

public class AUTPDVLogin extends AUTPDVBaseComponent{

	private Desktop desktop = new Desktop();



	@Before
	public void baseState() {
		BaseState baseState = new BaseState();
		baseState.execute(desktop);
	}

	@Test
	public void autLogin() {
		Control pdv = desktop.<Control>find("PDV");
		System.out.println(pdv.getText());
	
		//autStartLogin("951028487", "951028487");
		//autPDVLogout("51028487", "51028487");

	}

}
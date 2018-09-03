package br.lry.components.pdv.linx;

import com.borland.silktest.jtf.BaseState;
import org.junit.Before;
import org.junit.Test;
import com.borland.silktest.jtf.Control;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;
import com.microfocus.silktest.jtf.sap.common.types.VKey;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import sun.security.util.KeyUtil;

import com.borland.silktest.jtf.Desktop;

public class AUTPDVLogin extends AUTPDVBaseComponent{

	@Test
	public void autLogin() {
		autStartLogin("951028487", "951028487");
		autPDVLogout("51028487", "51028487");
		autPDVFecharAplicacao();		
	}

}
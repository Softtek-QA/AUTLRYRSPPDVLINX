/**
 * 
 */
package br.lry.components.pdv.linx;

import org.junit.Test;

/**
 * 
 * Executa procedimentos de logout no PDV
 * 
 * @author Softtek-QA
 *
 */
public class AUTPDVLogout extends AUTPDVBaseComponent {
	@Test
	public void autTestLogin() {
		String usuario = "51028487";
		String pwd = "51028487";
		
		autPDVLogout(usuario, pwd);
	}
}

/**
 * 
 */
package br.lry.components.pdv.linx;

import com.borland.silktest.jtf.BaseState;

/**
 * Classe base de para componentes automatizados PDV-LINX
 * 
 * @author Softtek-QA
 *
 */
public class AUTPDVBaseComponent {
	com.borland.silktest.jtf.Desktop AUT_AGENT_SILK4J = new com.borland.silktest.jtf.Desktop();
	com.borland.silktest.jtf.BaseState AUT_AGENT_SILK4J_CONFIGURATION = new com.borland.silktest.jtf.BaseState();
	
	
	/**
	 * Inicia a aplicação pdv
	 * 
	 * @return boolean - True caso o processo seja finalizado sem erros, false caso contrário
	 * 
	 */
	public boolean autStartPDV() {
		try {
			System.out.println("PDV: AUT INFO: INICIALIZANDO LINX-PDV");
			AUT_AGENT_SILK4J.executeBaseState(AUT_AGENT_SILK4J_CONFIGURATION);			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV : AUT ERROR: INICIALIZAÇÃO DO PDV");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Verifica se o PDV se encontra em status : Fechado parcial e disponível para entrada de novo usuário
	 * 
	 * @return boolean - True o PDV esteja fechado parcialmente e disponível, false caso contrário
	 * 
	 */
	public boolean autPDVFechadoParcial() {
		try {
						
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0001");
			
			System.out.println("PDV: AUT INFO : VERIFICANDO STATUS : FECHADO PARCIAL");

			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: VERIFICAÇÃO DE STATUS PDV");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	public AUTPDVBaseComponent() {
		
	}
}

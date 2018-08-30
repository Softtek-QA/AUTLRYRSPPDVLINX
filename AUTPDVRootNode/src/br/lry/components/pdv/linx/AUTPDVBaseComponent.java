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
	
	public interface AUTPDVFunctionsSyncronized{
		public boolean autStartPDVFunction();
	}
	
	
	public enum AUT_PDV_OPTIONS{
		ENTER,
		ENTRADA_OPERADOR,
		TEMPO_ENTRE_INTERACOES_LOOPS;
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			switch(this) {
			case ENTER:{
				return "<#Enter>";
				}
			case ENTRADA_OPERADOR:{
				return "1";
			}
			case TEMPO_ENTRE_INTERACOES_LOOPS:{
				return "6000";
			}
			}
			return super.toString();
		}
	}
	
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
	public boolean autPDVStatusFechadoParcial() {
		try {
			
			
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0001");
			
			System.out.println("PDV: AUT INFO : STATUS ATUAL : CAIXA FECHADO PARCIAL");

			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: VERIFICAÇÃO DE STATUS PDV");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	public <TPDVFunction extends AUTPDVFunctionsSyncronized> void autPDVExecFuncSincronizada(TPDVFunction functionPDV) {
		try {
			
			boolean bOk = false;
			bOk = functionPDV.autStartPDVFunction();
			while(!bOk) {
				bOk = functionPDV.autStartPDVFunction();
				com.borland.silktest.jtf.Utils.sleep(3000);
			}
		}
		catch(com.borland.silktest.jtf.common.VerificationFailedException e) {
			
		}
	}
	
	
	
	/**
	 * Envia dados de entrada para PDV
	 * 
	 * @param dados - Dados de entrada do caixa
	 */
	public void autPDVEntradaDados(Object dados) {
		String strFrt = "<#%s>";		
		for(java.lang.Character c : dados.toString().toCharArray()) {
			AUT_AGENT_SILK4J.<com.borland.silktest.jtf.Control>find("PDV").typeKeys(String.format(strFrt, c));			
		}
	}

	/**
	 * 
	 * Envia dados para PDV
	 * 
	 * @param dados - Dados de entrada para PDV
	 * @param tempoDelayEntreTeclas - Tempo de espera entre cada caractere da string de dados
	 * 
	 */
	public void autPDVEntradaDados(Object dados,Integer tempoDelayEntreTeclas) {
		String strFrt = "<#%s>";		
		for(java.lang.Character c : dados.toString().toCharArray()) {
			AUT_AGENT_SILK4J.<com.borland.silktest.jtf.Control>find("PDV").typeKeys(String.format(strFrt, c),tempoDelayEntreTeclas * 1000);
		}		
	}
	
	/**
	 * Envia comandos pré configurados para PDV
	 * 
	 * @param comandoPDV - Comando definido previamente
	 */
	public <TComando extends java.lang.Enum<AUT_PDV_OPTIONS>> void autPDVEnviarComando(TComando comandoPDV) {
		AUT_AGENT_SILK4J.<com.borland.silktest.jtf.Control>find("PDV").typeKeys(comandoPDV.toString());
	}
	
	/**
	 * Verifica se o caixa está aguardando o código de entrada do operador 
	 * 
	 * @return boolean - True caso o caixa esteja exibindo a mensagem solicitando a entrada do operador
	 * 
	 */
	public boolean autPDVStatusEntradaOperador() {
		try {
			
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0002");
			
			System.out.println("PDV : AUT INFO: VALIDACAO STATUS  ENTRADA OPERADOR: OK");
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: VALIDACAO DE STATUS ENTRADA OPERADOR");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Verifica se o caixa está aguardando senha do operador 
	 * 
	 * @return boolean - True caso o caixa esteja exibindo a mensagem solicitando a senha do operador
	 * 
	 */
	public boolean autPDVStatusSenhaOperador() {
		try {
			
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0003");
			
			System.out.println("PDV : AUT INFO: VALIDACAO STATUS  SENHA OPERADOR: OK");
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: VALIDACAO DE STATUS SENHA OPERADOR");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Verifica se o caixa está aguardando um valor para o fundo de troco 
	 * 
	 * @return boolean - True caso o caixa esteja exibindo a mensagem solicitando um fundo de troco
	 * 
	 */
	public boolean autPDVStatusFundoDeTroco() {
		try {
			
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0004");
			
			System.out.println("PDV : AUT INFO: VALIDACAO STATUS AG.FUNDO DE TROCO: OK");
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: VALIDACAO DE STATUS AG.FUNDO DE TROCO");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	
	
	/**
	 * Verifica se o caixa está exindo a mensagem caixa disponível
	 * 
	 * @return boolean - True caso o caixa esteja exibindo a mensagem caixa disponível
	 * 
	 */
	public boolean autPDVStatusCaixaDisponível() {
		try {
			
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0005");
			
			System.out.println("PDV : AUT INFO: VALIDACAO STATUS CAIXA DISPONIVEL: OK");
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: VALIDACAO DE STATUS CAIXA DISPONIVEL");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	public AUTPDVBaseComponent() {
		
	}
}

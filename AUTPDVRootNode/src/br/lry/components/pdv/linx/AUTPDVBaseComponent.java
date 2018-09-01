/**
 * 
 */
package br.lry.components.pdv.linx;

import com.borland.silktest.jtf.BaseState;
import com.borland.silktest.jtf.Control;
import com.borland.silktest.jtf.Desktop;

import br.lry.components.pdv.linx.AUTPDVBaseComponent.AUTPDVFunctionsSyncronized;
import br.lry.components.pdv.linx.AUTPDVBaseComponent.AUT_PDV_OPTIONS;

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
		VOLTAR_CANCELAR,
		ENTER,
		ENTRADA_OPERADOR,
		SAIDA_OPERADOR,
		TEMPO_ENTRE_INTERACOES_LOOPS,
		MENU_PRINCIPAL_SAIR_APLICACAO_PDV,
		CODIGO_MENU_SAIR_APLICACAO;
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			switch(this) {
			case ENTER:{
				return "<#Enter>";
			}
			case ENTRADA_OPERADOR:{
				return "11111111111111111111";
			}
			case TEMPO_ENTRE_INTERACOES_LOOPS:{
				return "6";
			}
			case SAIDA_OPERADOR:{
				return "3";
			}
			case VOLTAR_CANCELAR:{
				Byte keyEscape = 27;
				return new String(new byte[] {keyEscape});
			}
			case MENU_PRINCIPAL_SAIR_APLICACAO_PDV:{
				return "gggggggggg";
			}
			case CODIGO_MENU_SAIR_APLICACAO:{
				return "<#9><#9><#9> <#9><#9><#9> <#9><#9><#9> <#9><#9><#9>  <#9><#9><#9>  <#9><#9><#9> <#9><#9><#9> <#9><#9><#9>";
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
			AUT_AGENT_SILK4J = new Desktop();
			AUT_AGENT_SILK4J_CONFIGURATION = new BaseState();
			
			System.out.println("PDV: AUT INFO: INICIALIZANDO LINX-PDV");
			//AUT_AGENT_SILK4J_CONFIGURATION.setExecutable("C:/p2k/bin/pdv.bat");
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
			com.borland.silktest.jtf.Utils.sleep(7000);
			AUT_AGENT_SILK4J.<Control>find("PDV").click();
			com.borland.silktest.jtf.Utils.sleep(2000);
			AUT_AGENT_SILK4J.<Control>find("PDV").click();
			com.borland.silktest.jtf.Utils.sleep(40000);
			AUT_AGENT_SILK4J.<Control>find("PDV").click();
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: VERIFICAÇÃO DE STATUS PDV");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return false;
		}
	}

	/**
	 * 
	 * Verifica se o caixa está em status de fechamento
	 * 
	 * @return boolean - True caso o caixa esteja exibindo mensagem de fechamento, false caso contrário
	 *
	 */
	public boolean autPDVStatusFechamentoCaixa() {
		try {
			
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0006");
			
			System.out.println("PDV : AUT INFO: STATUS FECHAMENTO DE CAIXA : OK");
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: STATUS FECHAMENTO DE CAIXA");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Verifica o se status atual do caixa é de entrada de coordenador
	 * 
	 * @param coordenador - Coordenador aprovador
	 * 
	 * @return boolean - True caso esteja sendo exibida a mensagem solicitando entrada do coordenador aprovador, false caso contrário
	 * 
	 */
	public boolean autPDVStatusFechamentoCXEntradaCoordenador() {
		try {	
			
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0007");			
			System.out.println("PDV : AUT INFO: STATUS FECHAMENTO ENTRADA COORDENADOR : OK");
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT INFO : STATUS FECHAMENTO ENTRADA COORDENADOR");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return false;
		}
	}
	
	/**
	 * 
	 * Verifica se o PDV ESTÁ EM STATUS DE CONFERENCIA DE CAIXA
	 * 
	 * @return boolean - True caso sistema esteja exibindo mensagem solicitando conferência de valores do caixa, false caso contrário
	 * 
	 */
	public boolean autPDVStatusRealizarConferencia() {
		try {
			
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0008");
			
			System.out.println("PDV: AUT INFO: STATUS REALIZAR CONFERENCIA : OK");
			
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV : AUT ERROR: STATUS REALIZAR CONFERENCIA");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	/**
	 * 
	 * Executa procedimentos de logout no sistema
	 * 
	 * 
	 * @param superUsuario - Usuário com super acesso
	 * @param senha - Senha de super usuário associado a conta
	 * 
	 * @return boolean - True o processo seja finalizado com sucesso,false caso contrário
	 * 
	 */
	public boolean autPDVLogout(String superUsuario,String senha) {
		try {
			
			System.out.println("PDV: AUT INFO : EXECUTANDO PROCEDIMENTOS DE LOGOUT NO SISTEMA");
			autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {			
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autPDVStatusCaixaDisponível();
				}
			});
			
			autPDVEnviarComando(AUT_PDV_OPTIONS.SAIDA_OPERADOR);
						
			autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {			
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autPDVStatusFechamentoCaixa();
				}
			});
			
			autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);
			
			autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {			
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autPDVStatusFechamentoCXEntradaCoordenador();
				}
			});			
			
			autPDVEntradaDados(superUsuario);
			autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);
			
			autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {			
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autPDVStatusSenhaOperador();
				}
			});			
			
			autPDVEntradaDados(senha);
			autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);

			autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {			
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autPDVStatusRealizarConferencia();
				}
			});			

			autPDVEnviarComando(AUT_PDV_OPTIONS.VOLTAR_CANCELAR);
			
			autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {			
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autPDVStatusFechadoParcial();
				}
			});			
			
			System.out.println("PDV : AUT INFO : SAIDA DE OPERADOR REALIZADA FINALIZADA");
			
			return true;
		}
		catch(java.lang.Exception e) {
			
			System.out.println("PDV : AUT ERROR: LOGOUT PDV");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}

	/**
	 * 
	 * Verifica se o sistema está exibindo a tela do PDV mostrando o menu principal de finalização do sistema
	 * 
	 * @return boolean - True caso o processo seja finalizado com sucesso, false caso contrário
	 * 
	 */
	public boolean autPDVStatusSairAplicacao() {	
		try {			
			
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0009");
			
			System.out.println("PDV : AUT INFO: STATUS MEU PRINCIPAL PARA SAIR PDV : OK");

			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: STATUS MENU PRINCIPAL PARA  SAIR APLICACAO PDV");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	/**
	 * Verifica se o PDV se encontra online e ativo na area de trabalho
	 * 
	 * @return boolean - Caso o processo seja finalizado com sucesso, false caso contrário
	 * 
	 */
	public boolean autPDVStatusOnline() {
		try {
			System.out.println("PDV: AUT INFO: STATUS ONLINE - ATIVO NA TELA");
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0000");		
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: STATUS ONLINE - ATIVO NA TELA");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * Verifica se o PDV se encontra online e ativo na area de trabalho
	 * 
	 * @return boolean - Caso o processo seja finalizado com sucesso, false caso contrário
	 * 
	 */
	public boolean autPDVStatusConfirmarOpcaoSaidaAplicacao() {
		try {
			System.out.println("PDV: AUT INFO: STATUS CONFIRMAR SAIDA APLICACAO");
			AUT_AGENT_SILK4J.verifyAsset("PDV-STATUS-0010");		
			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: STATUS CONFIRMAR SAIDA APLICACAO");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	/**
	 * Executa procedimentos para fechar aplicação PDV
	 * 
	 * 
	 * @return boolean - True caso o procedimento seja executado com sucesso, false caso contrário
	 * 
	 */
	public boolean autPDVFecharAplicacao() {
		try {
			
			autPDVStatusOnline();
			
			autPDVExecFuncSincronizada(AUT_PDV_OPTIONS.MENU_PRINCIPAL_SAIR_APLICACAO_PDV, 4, new AUTPDVFunctionsSyncronized() {			
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autPDVStatusSairAplicacao();
				}
			});
			
			autPDVStatusSairAplicacao();
			
			autPDVExecFuncSincronizada("(\\<\\#\\d{1}\\>)+",AUT_PDV_OPTIONS.CODIGO_MENU_SAIR_APLICACAO, 4, new AUTPDVFunctionsSyncronized() {
				
				public boolean autValidacao() {
					autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);
					return autPDVStatusConfirmarOpcaoSaidaAplicacao();
				}
				
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autValidacao();
				}
			});
			
			autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);
			
			System.out.println("PDV : AUT INFO: EXECUTA PROCEDIMENTOS PARA FECHAR APLICAÇÃO");
			
			return true;
		}
		catch(java.lang.Exception e) {
		
			System.out.println("PDV : AUT ERROR: EXECUTA PROCEDIMENTO PARA FECHAR APLICAÇÃO");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}	
	}
	
	/**
	 * 
	 * Executa uma funçao PDV em modo sincronizado
	 * 
	 * 
	 * @param functionPDV - funçao para execução
	 * 
	 */
	public <TPDVFunction extends AUTPDVFunctionsSyncronized> void autPDVExecFuncSincronizada(TPDVFunction functionPDV) {
		try {

			boolean bOk = false;
			bOk = functionPDV.autStartPDVFunction();
			while(!bOk) {
				bOk = functionPDV.autStartPDVFunction();
				com.borland.silktest.jtf.Utils.sleep(Integer.parseInt(AUT_PDV_OPTIONS.TEMPO_ENTRE_INTERACOES_LOOPS.toString()) * 1000);
			}
		}
		catch(com.borland.silktest.jtf.common.VerificationFailedException e) {

		}
	}

	
	/**
	 * 
	 * Executa uma funçao PDV em modo sincronizado
	 * 
	 * 
	 * @param functionPDV - funçao para execução
	 * 
	 */
	public <TPDVFunction extends AUTPDVFunctionsSyncronized> void autPDVExecFuncSincronizada(Object dadosEntrada,Integer tempoDelay,TPDVFunction functionPDV) {
		try {

			for(java.lang.Character crt: dadosEntrada.toString().toCharArray()) {
				boolean bOk = false;
				AUT_AGENT_SILK4J.<Control>find("PDV").click();
				AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(crt.toString());				
				bOk = functionPDV.autStartPDVFunction();
				if(bOk) {					
					break;
				}
				com.borland.silktest.jtf.Utils.sleep(tempoDelay * 1000);
			}			
		}
		catch(com.borland.silktest.jtf.common.VerificationFailedException e) {

		}
	}

	/**
	 * 
	 * Executa uma funçao PDV em modo sincronizado
	 * 
	 * 
	 * @param functionPDV - funçao para execução
	 * 
	 */
	public <TPDVFunction extends AUTPDVFunctionsSyncronized> void autPDVExecFuncSincronizada(String expressaoRegularSepadorValores,Object dadosEntrada,Integer tempoDelay,TPDVFunction functionPDV) {
		try {

			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(expressaoRegularSepadorValores);
			java.util.regex.Matcher analise = padrao.matcher(dadosEntrada.toString());
			
			while(analise.find()) {
				boolean bOk = false;
				System.out.println(analise.group());
				AUT_AGENT_SILK4J.<Control>find("PDV").click();
				AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(analise.group());				
				bOk = functionPDV.autStartPDVFunction();
				if(bOk) {					
					break;
				}
				com.borland.silktest.jtf.Utils.sleep(tempoDelay * 1000);				
			}
		}
		catch(com.borland.silktest.jtf.common.VerificationFailedException e) {

		}
	}

	
	/**
	 * 
	 * Executa os procedimentos de login no PDV
	 * 
	 * @param usuario - Usuário pdv
	 * @param senha - senha
	 * @return boolean - True caso o processo seja finalizado com sucesso, false caso contrário
	 * 
	 */
	public boolean autStartLogin(String usuario,String senha) {
		try {
			autStartPDV();
			autPDVStatusFechadoParcial();
			
			autPDVExecFuncSincronizada(AUT_PDV_OPTIONS.ENTRADA_OPERADOR,5,new AUTPDVFunctionsSyncronized() {				
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autPDVStatusEntradaOperador();
				}
			});	
			
			autPDVStatusEntradaOperador();
			autPDVEntradaDados(usuario);
			autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);		
			autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {				
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autPDVStatusSenhaOperador();
				}
			});			
			autPDVStatusSenhaOperador();
			autPDVEntradaDados(senha);
			autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);	
			autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {				
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autPDVStatusFundoDeTroco();
				}
			});			
			autPDVStatusFundoDeTroco();			
			autPDVEntradaDados("10020");
			autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);		
			autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {				
				@Override
				public boolean autStartPDVFunction() {
					// TODO Auto-generated method stub
					return autPDVStatusCaixaDisponível();
				}
			});	
			
			
			autPDVStatusCaixaDisponível();

			return true;
		}
		catch(java.lang.Exception e) {
			System.out.println("PDV: AUT ERROR: LOGIN PDV");
			System.out.println(e.getMessage());
			e.printStackTrace();		
			return false;
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
		AUT_AGENT_SILK4J.<com.borland.silktest.jtf.Control>find("PDV").click();
		AUT_AGENT_SILK4J.<com.borland.silktest.jtf.Control>find("PDV").click();
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
			
			com.borland.silktest.jtf.Utils.sleep(5000);

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

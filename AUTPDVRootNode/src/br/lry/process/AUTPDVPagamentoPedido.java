/**
 * 
 */
package br.lry.process;

import br.lry.components.pdv.linx.AUTPDVBaseComponent;
import br.lry.components.pdv.linx.AUTPDVBaseComponent.AUTPDVFunctionsSyncronized;
import br.lry.components.pdv.linx.AUTPDVBaseComponent.AUT_PDV_OPTIONS;
import br.lry.components.pdv.linx.AUTPDVLogout;

import org.junit.Test;

import com.borland.silktest.jtf.Control;
import com.borland.silktest.jtf.common.VerificationFailedException;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;

/**
 * 
 * Classe executa procedimentos de pagamento de pedido no PDV
 * 
 * @author Softtek-QA
 *
 */
public class AUTPDVPagamentoPedido extends AUTPDVBaseComponent {
	public boolean AUT_STATUS_EXECUTION = false;
	/**
	 * 
	 * Executa procedimento de paramento padrão no PDV
	 * 
	 * @param parametrosConfiguracao - Parametros de configuração do processo de pagamento
	 * 
	 */
	public void autStartProcess(java.util.HashMap<String, Object> parametrosConfiguracao) {
		AUT_STATUS_EXECUTION = false;
		//autResetStartPDV();
		autSetHostExecutionService("127.0.0.1");
		
		if(AUT_AGENT_SILK4J.<Control>find("PDV").exists("Formulario", 5000)) {			
			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.VOLTAR_CANCELAR.toString());
			com.borland.silktest.jtf.Utils.sleep(4000);
			AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
			com.borland.silktest.jtf.Utils.sleep(1000);
			AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.VOLTAR_CANCELAR.toString());
			com.borland.silktest.jtf.Utils.sleep(1000);
			AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.VOLTAR_CANCELAR.toString());
		}		
		
		autPDVAguardaTela("PDV-STATUS-0005");
		autPDVEnviarComando(AUT_PDV_OPTIONS.VOLTAR_CANCELAR);
		autPDVEnviarComando(AUT_PDV_OPTIONS.VOLTAR_CANCELAR);
		
		autPDVEnviarComando(AUT_PDV_OPTIONS.PAGAR_PEDIDO);
		
		autPDVAguardaTela("PDV-STATUS-0013");
		autPDVEntradaDados(parametrosConfiguracao.get("AUT_PEDIDO"));
		com.borland.silktest.jtf.Utils.sleep(2000);
		autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);
	
		autPDVAguardaTela("PDV-STATUS-0014");
		autPDVEnviarComando(AUT_PDV_OPTIONS.VOLTAR_CANCELAR);
		com.borland.silktest.jtf.Utils.sleep(2000);

		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT,new Point(321, 168));
		com.borland.silktest.jtf.Utils.sleep(5000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys("<Enter>");
		com.borland.silktest.jtf.Utils.sleep(5000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys("<Enter>");
		autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);

		if (parametrosConfiguracao.get("AUT_TIPO_PESSOA").toString().contains("ESTRANGEIRO")){
			autPDVAguardaTela("PDV-STATUS-0018");
			autPDVEntradaDados(parametrosConfiguracao.get("AUT_MATERIAL"));
			autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);	
			
			autPDVAguardaTela("PDV-STATUS-0019");
			AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys("<Space>");

		} else {
			autPDVAguardaTela("PDV-STATUS-0017");	
			AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys("<Enter>");

			autPDVAguardaTela("PDV-STATUS-0018");

			if(parametrosConfiguracao.get("AUT_FLUXO_SAIDA").toString().contains("RETIRA_EXTERNA_IMEDIATA")) 
				AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys("<Space>");
			else {
				autPDVEntradaDados(parametrosConfiguracao.get("AUT_MATERIAL"), 2);
				autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);	
				
				autPDVAguardaTela("PDV-STATUS-0019");
				AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys("<Space>");
			}	
		}

		//autPDVAguardaTela("PDV-STATUS-0020");	
		com.borland.silktest.jtf.Utils.sleep(5000);
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys("<F1>");
		com.borland.silktest.jtf.Utils.sleep(110 * 1000);
		
		AUT_STATUS_EXECUTION = true;
	}
		
}

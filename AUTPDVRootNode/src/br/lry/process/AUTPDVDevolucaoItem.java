/**
 * 
 */
package br.lry.process;

import com.borland.silktest.jtf.Control;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;

import br.lry.components.pdv.linx.AUTPDVBaseComponent;
import br.lry.components.pdv.linx.AUTPDVBaseComponent.AUTPDVFunctionsSyncronized;
import br.lry.components.pdv.linx.AUTPDVBaseComponent.AUT_PDV_OPTIONS;
import com.borland.silktest.jtf.Window;

/**
 * @author Administrador
 *
 */
public class AUTPDVDevolucaoItem extends AUTPDVBaseComponent {
	public boolean AUT_STATUS_EXECUTION = true;
	
	/**
	 * 
	 * Executa procedimento de paramento padrão no PDV
	 * 
	 * @param parametrosConfiguracao - Parametros de configuração do processo de pagamento
	 * 
	 */
	public void autStartProcess(java.util.HashMap<String, Object> parametrosConfiguracao) {
		AUT_STATUS_EXECUTION = false;

		autSetHostExecutionService("127.0.0.1");
		autPDVEnviarComando(AUT_PDV_OPTIONS.VOLTAR_CANCELAR);
		com.borland.silktest.jtf.Utils.sleep(2000);
		autPDVEnviarComando(AUT_PDV_OPTIONS.VOLTAR_CANCELAR);
		com.borland.silktest.jtf.Utils.sleep(2000);
		
		autPDVAguardaTela("PDV-STATUS-0005");
		autPDVEnviarComando(AUT_PDV_OPTIONS.DEVOLUCAO_PEDIDO);

		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.DEVOLUCAO_OPCAO_POR_PEDIDO.toString());
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		autPDVAguardaTela("PDV-STATUS-0022");
		autPDVEntradaDados(parametrosConfiguracao.get("AUT_LOJA_DEVOLUCAO"));
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		autPDVAguardaTela("PDV-STATUS-0023");
		autPDVEntradaDados(parametrosConfiguracao.get("AUT_PEDIDO"));
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		autPDVAguardaTela("PDV-STATUS-0024");
		//AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(89, 122));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(22, 117));

		
		if(!parametrosConfiguracao.get("AUT_FLUXO_SAIDA").toString().contains("RETIRA_EXTERNA_IMEDIATA")) {
			autPDVAguardaTela("PDV-STATUS-0025");
			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade").typeKeys("1");

			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade").typeKeys("<Tab>");
			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());		
		}

		autPDVAguardaTela("PDV-STATUS-0026");
		
		if(parametrosConfiguracao.get("AUT_FLUXO_SAIDA").toString().contains("RETIRA_EXTERNA_IMEDIATA")) {
			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").click(MouseButton.LEFT,
					new Point(96, 171));
			com.borland.silktest.jtf.Utils.sleep(5000);			
		} 
			
		//AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.GlassWndClass-GlassWindowClass-18").click(MouseButton.LEFT,
		//		new Point(273, 535));


		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		
//		autPDVAguardaTela("PDV-STATUS-0024");
		com.borland.silktest.jtf.Utils.sleep(3000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		com.borland.silktest.jtf.Utils.sleep(3000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		com.borland.silktest.jtf.Utils.sleep(8000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(356, 366));
		
		
		autPDVAguardaTela("PDV-STATUS-0027");
		AUT_AGENT_SILK4J.<Window>find("Window").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		 	
		if (parametrosConfiguracao.get("AUT_TIPO_PESSOA").toString().contains("ESTRANGEIRO")){

			
			autPDVAguardaTela("PDV-STATUS-0030"); 	
			autPDVEnviarComando(AUT_PDV_OPTIONS.TIPO_CLIENTE_ESTRANGEIRO);
			autPDVEntradaDados("2"); // Opção Passaporte
			AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
			
			autPDVAguardaTela("PDV-STATUS-0031");
			String passaporte = parametrosConfiguracao.get("AUT_NUMERO_DOCUMENTO").toString();
			
			//String passaporteLetras = passaporte.substring(0, 2).toLowerCase();
			//String passaporteLetra2 = passaporte.substring(1, 2).toLowerCase();
			//String passaporteNumeros = passaporte.substring(2);
						
			autPDVEntradaDados2(passaporte.toLowerCase());	
			AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		}

		autPDVAguardaTela("PDV-STATUS-0028");
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(27, 270));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario")
				.typeKeys(autGetStringFormat(parametrosConfiguracao.get("AUT_DDD").toString()));
		com.borland.silktest.jtf.Utils.sleep(2000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(75, 269));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario")
				.typeKeys(autGetStringFormat(parametrosConfiguracao.get("AUT_TELEFONE").toString()));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		
		//autPDVAguardaTela("PDV-STATUS-0029");
		com.borland.silktest.jtf.Utils.sleep(5000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		com.borland.silktest.jtf.Utils.sleep(150 * 1000);

		AUT_STATUS_EXECUTION = true;

	}

}

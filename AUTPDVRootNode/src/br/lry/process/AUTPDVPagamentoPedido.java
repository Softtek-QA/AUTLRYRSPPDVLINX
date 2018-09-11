/**
 * 
 */
package br.lry.process;

import br.lry.components.pdv.linx.AUTPDVBaseComponent;
import br.lry.components.pdv.linx.AUTPDVBaseComponent.AUTPDVFunctionsSyncronized;
import br.lry.components.pdv.linx.AUTPDVBaseComponent.AUT_PDV_OPTIONS;
import br.lry.components.pdv.linx.AUTPDVLogout;

import com.borland.silktest.jtf.Control;
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
	/**
	 * 
	 * Executa procedimento de paramento padrão no PDV
	 * 
	 * @param parametrosConfiguracao - Parametros de configuração do processo de pagamento
	 * 
	 */
	public void autStartProcess(java.util.HashMap<String, Object> parametrosConfiguracao) {
		autSetHostExecutionService("127.0.0.1");
		autStartLogin("951028487", "951028487");
		autPDVStatusCaixaDisponível();
		autPDVEnviarComando(AUT_PDV_OPTIONS.PAGAR_PEDIDO);

		com.borland.silktest.jtf.Utils.sleep(4000);
		if (!autPDVStatusPagamentoPedido()) {
			autPDVExecFuncSincronizada(new AUTPDVBaseComponent.AUTPDVFunctionsSyncronized() {
				@Override
				public boolean autStartPDVFunction() {
					return autPDVStatusPagamentoPedido();
				}
			});
		}

		autPDVEntradaDados(parametrosConfiguracao.get("AUT_PEDIDO"));
		com.borland.silktest.jtf.Utils.sleep(2000);
		autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);
		com.borland.silktest.jtf.Utils.sleep(2000);
		autPDVExecFuncSincronizada(new AUTPDVBaseComponent.AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				return autPDVStatusConfirmaInclusaoPedido();
			}
		});
		
		autPDVEnviarComando(AUT_PDV_OPTIONS.VOLTAR_CANCELAR);
		com.borland.silktest.jtf.Utils.sleep(4000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT,new Point(321, 168));
		com.borland.silktest.jtf.Utils.sleep(2000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys("<Enter>");
		com.borland.silktest.jtf.Utils.sleep(2000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys("<Enter>");
		autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);
		
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusPedidosConfirmarDocumento();
			}
		});
		
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys("<Enter>");
		com.borland.silktest.jtf.Utils.sleep(4000);
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys("<Enter>");
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusPedidosConfNumMaterial();
			}
		});
		autPDVStatusPedidosConfNumMaterial();
		autPDVEntradaDados(parametrosConfiguracao.get("AUT_MATERIAL"), 2);;
		autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);
		
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusPedidosCumpomFiscal();
			}
		});
		
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys("<Space>");
		

		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusPedidosPagDinheiro();
			}
		});
		
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys("<F1>");
		
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusCaixaDisponível();
			}
		});
		
		autPDVLogout("51028487", "51028487");		
	}
		

}

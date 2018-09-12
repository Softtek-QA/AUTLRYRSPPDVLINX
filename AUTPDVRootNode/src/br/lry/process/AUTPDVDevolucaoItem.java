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

		autPDVEnviarComando(AUT_PDV_OPTIONS.DEVOLUCAO_PEDIDO);

		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusMenuInicialDevolucoesPedido();
			}
		});

		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.DEVOLUCAO_OPCAO_POR_PEDIDO.toString());
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoNumeroLoja();
			}
		});

		autPDVEntradaDados(parametrosConfiguracao.get("AUT_LOJA_DEVOLUCAO"));

		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoNumeroPedido();
			}
		});

		autPDVEntradaDados(parametrosConfiguracao.get("AUT_PEDIDO"));
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoFormItensSelect();
			}
		});
		autPDVStatusDevolucaoFormItensSelect();

		//AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(89, 122));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(22, 117));

		com.borland.silktest.jtf.Utils.sleep(5000);

		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoFormQuantItem();
			}
		});

		//autPDVEntradaDados(parametrosConfiguracao.get("AUT_ITEM_QUANTIDADE"));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade")
				.typeKeys(parametrosConfiguracao.get("AUT_ITEM_QUANTIDADE").toString());
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade").typeKeys("<Tab>");
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoFormMotDevolucao();
			}
		});

		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys("<Down>");
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys("<Down>");
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys("<Down>");
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoFormItensSelect();
			}
		});
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(356, 366));
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoMsgBoxConfirmacao();
			}
		});
		
		AUT_AGENT_SILK4J.<Window>find("Window").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoReviewCadastro();
			}
		});
		
		
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(27, 270));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(autGetStringFormat(parametrosConfiguracao.get("AUT_DD").toString()));
			
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(75, 269));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(autGetStringFormat(parametrosConfiguracao.get("AUT_TELEFONE").toString()));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoMsgConfirmacaoValeTroca();
			}
		});
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		
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

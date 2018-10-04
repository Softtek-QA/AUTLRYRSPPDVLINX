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
		
		//autResetStartPDV();
		autSetHostExecutionService("127.0.0.1");
		//autStartLogin(parametrosConfiguracao.get("AUT_OPERADOR").toString(), parametrosConfiguracao.get("AUT_PWD_OPERADOR").toString());
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

		com.borland.silktest.jtf.Utils.sleep(7000);
		
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoNumeroPedido();
			}
		});

		autPDVEntradaDados(parametrosConfiguracao.get("AUT_PEDIDO"));
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		com.borland.silktest.jtf.Utils.sleep(5000);
		
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

		com.borland.silktest.jtf.Utils.sleep(10000);

		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoFormQuantItem();
			}
		});

		//autPDVEntradaDados(parametrosConfiguracao.get("AUT_ITEM_QUANTIDADE"));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade")
				.typeKeys("1");
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade").typeKeys("<Tab>");
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoFormMotDevolucao();
			}
		});

		com.borland.silktest.jtf.Utils.sleep(10000);
		
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys("<Down>");
		com.borland.silktest.jtf.Utils.sleep(3000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys("<Down>");
		com.borland.silktest.jtf.Utils.sleep(3000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys("<Down>");
		com.borland.silktest.jtf.Utils.sleep(3000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoFormItensSelect();
			}
		});
		
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		com.borland.silktest.jtf.Utils.sleep(3000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		com.borland.silktest.jtf.Utils.sleep(8000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(356, 366));
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoMsgBoxConfirmacao();
			}
		});
		
		AUT_AGENT_SILK4J.<Window>find("Window").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		com.borland.silktest.jtf.Utils.sleep(10000);
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoReviewCadastro();
			}
		});
		
		com.borland.silktest.jtf.Utils.sleep(2000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(27, 270));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(autGetStringFormat(parametrosConfiguracao.get("AUT_DD").toString()));
		com.borland.silktest.jtf.Utils.sleep(2000);	
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(75, 269));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(autGetStringFormat(parametrosConfiguracao.get("AUT_TELEFONE").toString()));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		com.borland.silktest.jtf.Utils.sleep(10000);
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoMsgConfirmacaoValeTroca();
			}
		});
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());		
		
		com.borland.silktest.jtf.Utils.sleep(160 * 1000);
		
		AUT_STATUS_EXECUTION = true;
	}

}

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
		autPDVEnviarComando(AUT_PDV_OPTIONS.VOLTAR_CANCELAR);
		com.borland.silktest.jtf.Utils.sleep(2000);
		autPDVEnviarComando(AUT_PDV_OPTIONS.VOLTAR_CANCELAR);
		com.borland.silktest.jtf.Utils.sleep(2000);
		
		autPDVStatusCaixaDisponível();
		autSyncStatusDB();

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
		autSyncStatusDB();
		
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoNumeroLoja();
			}
		});

		autPDVEntradaDados(parametrosConfiguracao.get("AUT_LOJA_DEVOLUCAO"));

		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		autSyncStatusDB();
		com.borland.silktest.jtf.Utils.sleep(3000);

		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoNumeroPedido();
			}
		});

		autPDVEntradaDados(parametrosConfiguracao.get("AUT_PEDIDO"));
		AUT_AGENT_SILK4J.<Control>find("PDV").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		autSyncStatusDB();
		com.borland.silktest.jtf.Utils.sleep(2000);

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
		autSyncStatusDB();
		com.borland.silktest.jtf.Utils.sleep(4000);

		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoFormQuantItem();
			}
		});
		autSyncStatusDB();
		
		if(parametrosConfiguracao.get("AUT_FLUXO_SAIDA").toString().contains("REITRADA_EXTERNA_IMEDIATA")) {
		
		}
		else {
			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade").typeKeys("1");

			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade").typeKeys("<Tab>");
			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormQuantidade").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());		
		}

		autSyncStatusDB();
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoFormMotDevolucao();
			}
		});
		autSyncStatusDB();
		com.borland.silktest.jtf.Utils.sleep(3000);
		if(parametrosConfiguracao.get("AUT_FLUXO_SAIDA").toString().contains("REITRADA_EXTERNA_IMEDIATA")) {
			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").click(MouseButton.LEFT,
					new Point(96, 171));
			com.borland.silktest.jtf.Utils.sleep(3000);			
			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").click(MouseButton.LEFT,
					new Point(96, 171));
			com.borland.silktest.jtf.Utils.sleep(1000);
			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys("<Down>");
			com.borland.silktest.jtf.Utils.sleep(2000);
			autSyncStatusDB();
			/**
			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys("<Down>");
			com.borland.silktest.jtf.Utils.sleep(2000);
			AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys("<Down>");
			com.borland.silktest.jtf.Utils.sleep(2000);		
			**/	
		}
		else {
		
		}		
		
		/*
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.GlassWndClass-GlassWindowClass-18").click(MouseButton.LEFT,
				new Point(273, 535));
*/
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario.FormMotivoDevolucao").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		autSyncStatusDB();
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoFormItensSelect();
			}
		});
		autSyncStatusDB();
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		com.borland.silktest.jtf.Utils.sleep(3000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		com.borland.silktest.jtf.Utils.sleep(4000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(356, 366));
		autSyncStatusDB();
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoMsgBoxConfirmacao();
			}
		});
		autSyncStatusDB();
		
		AUT_AGENT_SILK4J.<Window>find("Window").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		autSyncStatusDB();
		com.borland.silktest.jtf.Utils.sleep(4000);
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoReviewCadastro();
			}
		});
		autSyncStatusDB();
		com.borland.silktest.jtf.Utils.sleep(2000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(27, 270));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario")
				.typeKeys(autGetStringFormat(parametrosConfiguracao.get("AUT_DD").toString()));
		com.borland.silktest.jtf.Utils.sleep(2000);
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").click(MouseButton.LEFT, new Point(75, 269));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario")
				.typeKeys(autGetStringFormat(parametrosConfiguracao.get("AUT_TELEFONE").toString()));
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());
		autSyncStatusDB();
		com.borland.silktest.jtf.Utils.sleep(4000);
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusDevolucaoMsgConfirmacaoValeTroca();
			}
		});
		autSyncStatusDB();
		AUT_AGENT_SILK4J.<Control>find("PDV.Formulario").typeKeys(AUT_PDV_OPTIONS.ENTER.toString());

		com.borland.silktest.jtf.Utils.sleep(30 * 1000);

		AUT_STATUS_EXECUTION = true;

	}

}

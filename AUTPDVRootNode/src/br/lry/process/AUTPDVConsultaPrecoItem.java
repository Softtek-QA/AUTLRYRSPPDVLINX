/**
 * 
 */
package br.lry.process;

import br.lry.components.pdv.linx.AUTPDVBaseComponent;

/**
 * 
 * Classe de consulta preço de item
 * 
 * @author Softtek-QA
 *
 */
public class AUTPDVConsultaPrecoItem extends AUTPDVBaseComponent {
	/**
	 * 
	 * Executa procedimento de paramento padrão no PDV
	 * 
	 * @param parametrosConfiguracao - Parametros de configuração do processo de pagamento
	 * 
	 */
	public void autStartProcess(java.util.HashMap<String,Object> parametrosConfiguracao) {
		autSetHostExecutionService("127.0.0.1");
		autStartLogin("951028487", "951028487");
		autPDVStatusConsultaMaterial();
		autPDVEnviarComando(AUT_PDV_OPTIONS.CONSULTA_PRECO_MATERIAL);
		com.borland.silktest.jtf.Utils.sleep(6000);
		if(!autPDVStatusConsultaMaterial()) {
			autPDVExecFuncSincronizada(new AUTPDVBaseComponent.AUTPDVFunctionsSyncronized() {			
				@Override
				public boolean autStartPDVFunction() {					
					return autPDVStatusConsultaMaterial();
				}
			});
		}
		autPDVEntradaDados(parametrosConfiguracao.get("AUT_MATERIAL"));
		com.borland.silktest.jtf.Utils.sleep(6000);
		autPDVEnviarComando(AUT_PDV_OPTIONS.ENTER);
		autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {
			@Override
			public boolean autStartPDVFunction() {
				// TODO Auto-generated method stub
				return autPDVStatusConsultaMaterialDetalhamento();
			}
		});
		com.borland.silktest.jtf.Utils.sleep(3000);
		autPDVEnviarComando(AUT_PDV_OPTIONS.VOLTAR_CANCELAR);
		autPDVLogout("51028487", "51028487");
	}
		
	/**
	 * 
	 * Construtor padrão da classe
	 * 
	 */
	public AUTPDVConsultaPrecoItem() {
		
	}
}

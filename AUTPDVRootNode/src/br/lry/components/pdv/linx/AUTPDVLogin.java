package br.lry.components.pdv.linx;

import com.borland.silktest.jtf.BaseState;
import org.junit.Before;
import org.junit.Test;
import com.borland.silktest.jtf.Control;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;
import com.borland.silktest.jtf.Desktop;

public class AUTPDVLogin extends AUTPDVBaseComponent{

	private Desktop desktop = new Desktop();

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
			autPDVEnviarComando(AUT_PDV_OPTIONS.ENTRADA_OPERADOR);
			autPDVExecFuncSincronizada(new AUTPDVFunctionsSyncronized() {				
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

	@Before
	public void baseState() {
		BaseState baseState = new BaseState();
		baseState.execute(desktop);
	}

	@Test
	public void autLogin() {
		Control pdv = desktop.<Control>find("PDV");
		System.out.println(pdv.getText());
		autStartLogin("951028487", "951028487");

	}

}
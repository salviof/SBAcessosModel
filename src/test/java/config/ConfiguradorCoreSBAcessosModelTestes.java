/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package config;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ComoContextoExecCustomizavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorCoreDeProjetoJarAbstrato;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ControleDeSessaoPadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.FabTipoProjeto;

/**
 *
 * @author desenvolvedor
 */
public class ConfiguradorCoreSBAcessosModelTestes extends ConfiguradorCoreDeProjetoJarAbstrato {

    public ConfiguradorCoreSBAcessosModelTestes() {
        setIgnorarConfiguracaoPermissoes(false);

    }

    @Override
    public void defineFabricasDeACao(ComoContextoExecCustomizavel pConfig) {
        pConfig.setClasseConfigPermissao(ConfigPermissoesAcessosModel.class);
        pConfig.setFabricaDeAcoes(new Class[]{FabAcoesSBAcessosModelTestes.class});
        pConfig.setControleDeSessao(ControleDeSessaoPadrao.class);
        pConfig.setTipoProjeto(FabTipoProjeto.MODEL);
    }

}

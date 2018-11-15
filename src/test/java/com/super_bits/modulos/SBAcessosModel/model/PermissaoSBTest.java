/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import aux.ConfiguradorCoreSBAcessosModelTestes;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import config.ConfigPersistenciaTestesAcesso;
import org.junit.Test;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 * @author desenvolvedor
 */
public class PermissaoSBTest extends TesteJunitSBPersistencia {

    public PermissaoSBTest() {
    }

    @Test
    public void testgetEntidadesVinvuladas() {

        //AcaoDoSistema acaoDoSistema = FabAcaoSeguranca.GRUPO_FRM_NOVO.getAcaoDoSistema();
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreSBAcessosModelTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());
    }

}

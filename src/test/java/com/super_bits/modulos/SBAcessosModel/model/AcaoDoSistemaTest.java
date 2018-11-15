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
import org.junit.Before;
import org.junit.Test;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 * @author desenvolvedor
 */
public class AcaoDoSistemaTest extends TesteJunitSBPersistencia {

    public AcaoDoSistemaTest() {
    }

    @Before
    public void setUp() {

    }

    @Test
    public void testGetNomeAcao() {

        // ControllerAppAbstratoSBCore.reloadAcessos();
        //  System.out.println("Acoes encontradas=" + acoes.size());
        //   for (AcaoGenerica ac : acoes) {
        //       System.out.println(ac.getNomeAcao());
        //   }
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreSBAcessosModelTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());
    }
}

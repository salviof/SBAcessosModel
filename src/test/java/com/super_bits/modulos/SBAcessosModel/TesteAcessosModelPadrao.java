/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel;

import aux.ConfiguradorCoreSBAcessosModelTestes;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import javax.persistence.EntityManager;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 *
 * @author desenvolvedor
 */
public class TesteAcessosModelPadrao extends TesteJunitSBPersistencia {

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreSBAcessosModelTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

    }

    public void teste() {
        EntityManager em = getEmTeste();

    }

}

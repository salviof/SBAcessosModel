/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ComoFabricaModulo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoFabricaMenu;

/**
 *
 * @author desenvolvedor
 */
public enum FabModulosTestes implements ComoFabricaModulo {
    @InfoObjetoDaFabrica(nomeObjeto = "Modulo teste")
    MODULO_TESTE;

    @Override
    public ItfModuloAcaoSistema getModulo() {
        ModuloAcaoSistema modulo = new ModuloAcaoSistema();
        modulo.setNome("Modulo teste");
        modulo.setDescricao("Modulo criado para testes");
        return modulo;
    }

    @Override
    public ComoFabricaMenu getMenuPadrao() {
        return null;
    }

}

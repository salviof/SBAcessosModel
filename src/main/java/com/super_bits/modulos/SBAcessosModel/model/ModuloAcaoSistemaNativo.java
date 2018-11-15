/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ItfFabricaModulo;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Módulo nativo"}, plural = "Módulos nativos")
public class ModuloAcaoSistemaNativo extends ModuloAcaoSistema {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    private FabModulosSistemaSB enumVinculado;

    @Override
    public ItfFabricaModulo getEnumVinculado() {
        return enumVinculado;
    }

    @Override
    public void setEnumVinculado(ItfFabrica pFabrica) {
        enumVinculado = (FabModulosSistemaSB) pFabrica;
    }

}

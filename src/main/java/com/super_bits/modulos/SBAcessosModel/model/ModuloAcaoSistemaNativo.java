/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ComoFabricaModulo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Módulo nativo"}, plural = "Módulos nativos")
public class ModuloAcaoSistemaNativo extends ModuloAcaoSistema {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    @Enumerated(EnumType.ORDINAL)
    private FabModulosSistemaSB enumVinculado;

    @Override
    public ComoFabricaModulo getEnumVinculado() {
        return enumVinculado;
    }

    @Override
    public void setEnumVinculado(ComoFabrica pFabrica) {
        enumVinculado = (FabModulosSistemaSB) pFabrica;
    }

}

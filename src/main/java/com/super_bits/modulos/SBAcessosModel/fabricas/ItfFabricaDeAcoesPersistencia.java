/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.fabricas;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
public interface ItfFabricaDeAcoesPersistencia extends ItfFabricaAcoes {

    @Override
    public default AcaoDoSistema getRegistro() {
        return (AcaoDoSistema) UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
    }

    @Override
    public default String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

}

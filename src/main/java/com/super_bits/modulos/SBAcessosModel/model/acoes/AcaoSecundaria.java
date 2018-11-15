/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = "Ação secundária", plural = "Ações secundarias")
public class AcaoSecundaria extends AcaoDeEntidade implements ItfAcaoSecundaria {

    @Transient
    private AcaoGestaoEntidade acaoPrincipal;

    @Deprecated
    public AcaoSecundaria() {
        super();

    }

    public AcaoSecundaria(Class classeRelacionada, FabTipoAcaoSistema pTipoAcao, ItfFabricaAcoes pFabricaAcao) {
        super(classeRelacionada, pTipoAcao, pFabricaAcao);

    }

    public AcaoSecundaria(Class classeRelacionada, FabTipoAcaoSistema pTipoAcao, ItfFabricaAcoes pFabricaAcao, FabTipoAcaoSistemaGenerica pFabricaAcaoGenerica) {
        super(classeRelacionada, pTipoAcao, pFabricaAcao, pFabricaAcaoGenerica);

    }

    @Override
    public AcaoGestaoEntidade getAcaoPrincipal() {
        return acaoPrincipal;
    }

    public void setAcaoPrincipal(AcaoGestaoEntidade acaoPrincipal) {
        this.acaoPrincipal = acaoPrincipal;
    }

    @Override
    public final void setAcaoPrincipal(ItfAcaoGerenciarEntidade pAcaoPrincipal) {
        acaoPrincipal = (AcaoGestaoEntidade) pAcaoPrincipal;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoControllerAutoExecucao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.acoesAutomatizadas.FabTipoAutoExecucaoAcao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import javax.persistence.Transient;

public class AcaoControllerAutoExecucao extends AcaoController implements ItfAcaoSecundaria, ItfAcaoControllerAutoExecucao {

    @Transient
    private AcaoGestaoEntidade acaoPrincipal;
    @Transient
    private Class classeRelacionada;

    @Transient
    private FabTipoAutoExecucaoAcao tipoAutoExecucao;

    @Deprecated
    public AcaoControllerAutoExecucao() {
        super();

    }

    public AcaoControllerAutoExecucao(ItfAcaoGerenciarEntidade pAcaoPrincipal,
            FabTipoAcaoSistemaGenerica pAcaoGenerica, ItfFabricaAcoes pFabAcao) {
        super(pFabAcao);
        setAcaoPrincipal(pAcaoPrincipal);
        classeRelacionada = pAcaoPrincipal.getClasseRelacionada();
        setTipoAcaoGenerica(FabTipoAcaoSistemaGenerica.CONTROLLER_AUTO_EXECUCAO);

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

    @Override
    public Class getClasseRelacionada() {
        return classeRelacionada;
    }

    @Override
    public void setClasseRelacionada(Class pClasseRelacionada) {
        classeRelacionada = pClasseRelacionada;
    }

    @Override
    public String getNomeTipoObjetoClasseRelacionada() {
        return UtilSBCoreReflexaoObjeto.getNomeObjeto(classeRelacionada);
    }

    @Override
    public String getNomeTipoObjetoClasseRelacionadaPlural() {
        return UtilSBCoreReflexaoObjeto.getNomeObjetoPlural(classeRelacionada);
    }

    @Override
    public FabTipoAutoExecucaoAcao getTipoAutoExecucao() {
        return tipoAutoExecucao;
    }

    @Override
    public void setTipoAutoExecucao(FabTipoAutoExecucaoAcao pTipo) {
        tipoAutoExecucao = pTipo;
    }

}

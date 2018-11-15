/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.fabricas;

import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.PAGINAS_DO_SISTEMA)
public enum FabAcaoProjetoSB implements ItfFabricaAcoes {

    /**
     *
     * Managed Bean responsável por exibir o projeto e seu status de
     * desenvolvimento
     *
     */
    @InfoTipoAcaoGestaoEntidade(xhtmlDaAcao = "/resources/SBComp/projeto/gerenciarProjeto.xhtml",
            icone = "fa fa-codepen", nomeAcao = "Gestão do Projeto", codigoJira = "SK-94")
    PROJETO_MB_GERENCIAR,
    /**
     * Formulário que exibe todas as ações do sistema
     */
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/projeto/pontosDeFuncao.xhtml",
            nomeAcao = "Ver Ações", icone = "fa fa-bars")
    PROJETO_FRM_VISUALIZAR_ACOES,
    /**
     *
     * Formulário para exibição do banco no formato UML
     *
     */
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/projeto/umlBancoDeDados.xhtml",
            nomeAcao = "Visualizar Banco de Dados", icone = "fa fa-bars")
    PROJETO_FRM_VISUALIZAR_BANCO_DE_DADOS,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/projeto/infoTabela.xhtml",
            nomeAcao = "Visualizar Tabela", icone = "fa fa-bars")
    PROJETO_FRM_VISUALIZAR_TABELA,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/projeto/visaoGeral.xhtml",
            nomeAcao = "Visão geral", icone = "fa fa-bars")
    PROJETO_FRM_VISAO_GERAL;

    @Override
    public AcaoDoSistema getRegistro() {
        return (AcaoDoSistema) UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
    }

    @Override
    public Class getEntidadeDominio() {
        return ProjetoAtual.class;
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

}

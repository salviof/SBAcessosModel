/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoParametroAcaoController;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 *
 * Uma ação de entidade Da camada controller, é uma ação que executa alguma
 * alteração no sistema
 *
 * Em geral , uma ação de entidade possui apenas a entidade como parametro, mas
 * ela pode conter também parametros extras que devem ser especificados
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Ação de controller relacionada a entidade"}, plural = "Ações de controller relacionada")
public class AcaoDeEntidadeController extends AcaoSecundaria implements ComoAcaoController,
        ComoAcaoSecundaria, ComoAcaoControllerEntidade {

    @Transient
    private ItfAcaoGerenciarEntidade acaoPrincipal;

    @Transient
    private final AcaoController acaoControllerVinculado;

    @Deprecated
    public AcaoDeEntidadeController() {
        super();
        acaoControllerVinculado = new AcaoController();
    }

    public AcaoDeEntidadeController(ItfAcaoGerenciarEntidade pAcaoPrincipal,
            FabTipoAcaoSistemaGenerica pAcaoGenerica, ComoFabricaAcoes pFabAcao) {

        super(pAcaoPrincipal.getClasseRelacionada(), FabTipoAcaoSistema.ACAO_ENTIDADE_CONTROLLER, pFabAcao, pAcaoGenerica);
        setAcaoPrincipal(pAcaoPrincipal);
        acaoControllerVinculado = new AcaoController(pFabAcao);

    }

    @Override
    public Long getIdMetodo() {
        return acaoControllerVinculado.getIdMetodo();
    }

    @Override
    public boolean isTemParametroExtra() {
        return acaoControllerVinculado.isTemParametroExtra();
    }

    @Override
    public void setIdMetodo(Method pMetodo) {
        acaoControllerVinculado.setIdMetodo(pMetodo);
    }

    @Override
    public List<ComoParametroAcaoController> getParametros() {
        return acaoControllerVinculado.getParametros();
    }

    @Override
    public boolean isTemLogExecucao() {
        return acaoControllerVinculado.isTemLogExecucao();
    }

    @Override
    public void setTemLogExecucao(boolean pPrecisaJustificativa) {
        acaoControllerVinculado.setTemLogExecucao(pPrecisaJustificativa);
    }

    @Override
    public boolean isPrecisaJustificativa() {
        return acaoControllerVinculado.isPrecisaJustificativa();
    }

    @Override
    public void setPrecisaJustificativa(boolean pPrecisaJustificativa) {
        acaoControllerVinculado.setPrecisaJustificativa(pPrecisaJustificativa);
    }

    @Override
    public boolean isPrecisaComunicacao() {
        return acaoControllerVinculado.isPrecisaComunicacao();
    }

    @Override
    public void setPrecisaComunicacao(boolean pPrecisaComunicacao) {
        acaoControllerVinculado.setPrecisaComunicacao(pPrecisaComunicacao);
    }

    @Override
    public String getXhtmlModalVinculado() {
        return acaoControllerVinculado.getXhtmlModalVinculado();
    }

    @Override
    public void setXhtmlModalVinculado(String pModal) {
        acaoControllerVinculado.setXhtmlModalVinculado(pModal);
    }

    @Override
    public boolean isTemXHTMLModalVinculado() {
        return acaoControllerVinculado.isTemXHTMLModalVinculado();
    }

    @Override
    public void setTipoComunicacao(ItfTipoComunicacao pTipoComunicacao) {
        acaoControllerVinculado.setTipoComunicacao(pTipoComunicacao);
    }

    @Override
    public ItfTipoComunicacao getTipoComunicacao() {
        return acaoControllerVinculado.getTipoComunicacao();
    }

    @Override
    public String getTextoComunicacaoPersonalizadado() {
        return acaoControllerVinculado.getTextoComunicacaoPersonalizadado();
    }

    @Override
    public void setTextoComunicacaoPersonalizadado(String pTextocomunicacaoPersonalizado) {
        acaoControllerVinculado.setTextoComunicacaoPersonalizadado(pTextocomunicacaoPersonalizado);
    }

    @Override
    public String getCampoJustificativa() {
        return acaoControllerVinculado.getCampoJustificativa();
    }

    @Override
    public void setCampoJustificativa(String pCampoJustificativa) {
        acaoControllerVinculado.setCampoJustificativa(pCampoJustificativa);
    }

    @Override
    public String getNomeAcao() {
        return acaoControllerVinculado.getNomeAcao(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNome() {
        return acaoControllerVinculado.getNome(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getId() {
        return acaoControllerVinculado.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNome(String pNome) {
        acaoControllerVinculado.setNome(pNome); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNomeAcao(String nomeAcao) {
        acaoControllerVinculado.setNomeAcao(nomeAcao); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfCampoInstanciado getCampoByNomeOuAnotacao(String pNomeOuANotacao) {
        return acaoControllerVinculado.getCampoByNomeOuAnotacao(pNomeOuANotacao); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAcaoTemModal() {
        return acaoControllerVinculado.isAcaoTemModal();
    }

}

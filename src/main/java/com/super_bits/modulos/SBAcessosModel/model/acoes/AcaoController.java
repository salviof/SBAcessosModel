/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfParametroAcaoController;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * Uma ação de controller é uma ação do sistema, que executa alguma alteração
 * definitiva no banco de dados,
 *
 * Toda ação controller deve ter um método estatico de execução, vinculado a ela
 * via anotaçaõ
 *
 *
 * ela pode ou não conter parametros, e deve retornar algo.
 *
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Ação da cadamada de controller "}, plural = "Ações Controllers")
public class AcaoController extends AcaoDoSistema implements ItfAcaoController {

    private Long idMetodo;
    private boolean temLogExecucao;

    private boolean precisaComunicacao;
    private boolean precisaJustificativa;
    private boolean acaoTemModal;
    private String textoComunicacaoPersonalizado;
    private String campoJustificativa;
    private String xhtmlModalVinculado;
    @Transient
    private ItfTipoComunicacao tipoComunicacao;

    @Transient
    private List<ItfParametroAcaoController> parametros;

    @Deprecated
    public AcaoController() {

    }

    public AcaoController(ItfFabricaAcoes pAcao) {
        super(FabTipoAcaoSistema.ACAO_CONTROLLER, pAcao);

    }

    @Override
    public Long getIdMetodo() {
        return idMetodo;
    }

    @Override
    public void setIdMetodo(Method pMetodo) {

        idMetodo = UtilSBController.gerarIDMetodoAcaoDoSistema(pMetodo);
    }

    @Override
    public boolean isTemParametroExtra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfParametroAcaoController> getParametros() {
        return parametros;
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return getComoSecundaria().getAcaoPrincipal();
    }

    @Override
    public boolean isTemLogExecucao() {
        return temLogExecucao;
    }

    @Override
    public void setTemLogExecucao(boolean temLogExecucao) {
        this.temLogExecucao = temLogExecucao;
    }

    @Override
    public boolean isPrecisaJustificativa() {
        if (precisaJustificativa) {
            return precisaJustificativa;
        }
        if (campoJustificativa == null || campoJustificativa.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isPrecisaComunicacao() {

        if (isPrecisaJustificativa()) {
            return false;
        }

        if (getTipoComunicacao() == null) {
            return false;
        }

        if (precisaComunicacao) {
            return precisaComunicacao;
        }
        switch (getTipoComunicacao().getFabTipoComunicacao()) {

            case PERSONALIZADA:
                if (getTextoComunicacaoPersonalizadado() == null || getTextoComunicacaoPersonalizadado().isEmpty()) {
                    return false;

                } else {
                    return true;
                }
            default:
                return true;

        }
    }

    @Override
    public String getXhtmlModalVinculado() {
        return xhtmlModalVinculado;
    }

    @Override
    public boolean isTemXHTMLModalVinculado() {
        if (xhtmlModalVinculado == null || xhtmlModalVinculado.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ItfTipoComunicacao getTipoComunicacao() {
        return tipoComunicacao;
    }

    @Override
    public void setPrecisaComunicacao(boolean pPrecisaComunicacao) {

        precisaComunicacao = pPrecisaComunicacao;

    }

    @Override
    public void setXhtmlModalVinculado(String pModal) {
        xhtmlModalVinculado = pModal;
    }

    @Override
    public void setTipoComunicacao(ItfTipoComunicacao pTipoComunicacao) {
        tipoComunicacao = pTipoComunicacao;
    }

    @Override
    public String getTextoComunicacaoPersonalizadado() {
        return textoComunicacaoPersonalizado;
    }

    @Override
    public void setTextoComunicacaoPersonalizadado(String pTextocomunicacaoPersonalizado) {
        textoComunicacaoPersonalizado = pTextocomunicacaoPersonalizado;
    }

    @Override
    public String getCampoJustificativa() {
        return campoJustificativa;
    }

    @Override
    public void setCampoJustificativa(String pCampoJustificativa) {
        campoJustificativa = pCampoJustificativa;
    }

    @Override
    public void setPrecisaJustificativa(boolean pPrecisaJustificativa) {
        precisaJustificativa = pPrecisaJustificativa;
    }

    @Override
    public boolean isAcaoTemModal() {
        if (acaoTemModal) {
            return true;
        }

        if (isPrecisaComunicacao() || isPrecisaJustificativa()) {
            acaoTemModal = true;
        }
        return acaoTemModal;
    }

}

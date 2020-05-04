/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanGenerico;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;
import org.apache.commons.text.similarity.LongestCommonSubsequence;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Açao de Gestão de Dominio da Entidade"}, plural = "Ações de Dominio")
public class AcaoGestaoEntidade extends AcaoFormularioEntidade implements ItfAcaoGerenciarEntidade {

    @Transient
    private boolean utilizarMesmoFormEditarInserir = true;

    @Transient
    private List<ItfFabricaAcoes> enumAcoesVinculadas;

    public AcaoGestaoEntidade() {
        super();

    }

    public AcaoGestaoEntidade(ItfFabricaAcoes pFabrica, Class pClasse) {

        super(pClasse, FabTipoAcaoSistema.ACAO_ENTIDADE_GERENCIAR, pFabrica);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.GERENCIAR_DOMINIO;
        try {
            Field campo = pFabrica.getClass().getField(pFabrica.toString());
            InfoTipoAcaoGestaoEntidade acaoGestao = campo.getAnnotation(InfoTipoAcaoGestaoEntidade.class);
            setUtilizarMesmoFormEditarInserir(acaoGestao.utilizarMesmoFormEdicao());
        } catch (Throwable t) {

        }
    }

    public ItfAcaoDoSistema criarAcaoSecundaria(FabTipoAcaoSistemaGenerica pAcaoGenerica) {

        if (true) {
            throw new UnsupportedOperationException("Aind não implementado");
        }

        return null;
    }

    public boolean isUtilizarMesmoFormEditarInserir() {
        return utilizarMesmoFormEditarInserir;
    }

    public final void setUtilizarMesmoFormEditarInserir(boolean utilizarMesmoFormEditarInserir) {
        this.utilizarMesmoFormEditarInserir = utilizarMesmoFormEditarInserir;
    }

    @Override
    public boolean isUmaAcaoGestaoDominio() {
        return true;
    }

    @Override
    public void setAcoesVinculadas(List<ItfAcaoSecundaria> acoesVinculadas) {
        throw new UnsupportedOperationException("As ações vinculadas serão injetas, este acesso não é permitido");
    }

    @Override
    public List<ItfFabricaAcoes> getEnumAcoesVinculadas() {
        return enumAcoesVinculadas;
    }

    public void setEnumAcoesVinculadas(List<ItfFabricaAcoes> enumAcoesVinculadas) {
        this.enumAcoesVinculadas = enumAcoesVinculadas;
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return this;
    }

    @Override
    public ItfAcaoDoSistema[] getAcoesVinculadasExcetoListarEnovoArray() {

        ItfAcaoDoSistema[] acoesVinculadasExcetoListarEnovoArray = new ItfAcaoDoSistema[getAcoesVinculadasExcetoListarEnovo().size()];
        getAcoesVinculadasExcetoListarEnovo().toArray(acoesVinculadasExcetoListarEnovoArray);

        return acoesVinculadasExcetoListarEnovoArray;
    }

    @Override
    public boolean isTemPesquisa() {
        return false;
    }

    @Override
    public boolean isTemEditar() {
        return getAcaoFormularioEditar() != null;
    }

    @Override
    public boolean isTemVisualizar() {
        return getAcaoFormularioVisualizar() != null;
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoFormularioListarPadrao() {
        return ItfAcaoGerenciarEntidade.super.getAcaoFormularioListarPadrao(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoDoSistema getAcaoExectarFormulario() {
        return super.getAcaoExectarFormulario(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoController getAcaoRemover() {
        return ItfAcaoGerenciarEntidade.super.getAcaoRemover(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoControllerEntidade getAcaoSalvarMerge() {
        return ItfAcaoGerenciarEntidade.super.getAcaoSalvarMerge(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoControllerEntidade getAcaoSalvarNovo() {
        return ItfAcaoGerenciarEntidade.super.getAcaoSalvarNovo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoController getAcaoAlterarStatus() {
        return ItfAcaoGerenciarEntidade.super.getAcaoAlterarStatus(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoFormularioNovo() {
        return ItfAcaoGerenciarEntidade.super.getAcaoFormularioNovo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoFormularioEditar() {
        return ItfAcaoGerenciarEntidade.super.getAcaoFormularioEditar(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfAcaoFormularioEntidade> getAcoesFormularioListagem() {
        return ItfAcaoGerenciarEntidade.super.getAcoesFormularioListagem(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoFormularioVisualizar() {
        return ItfAcaoGerenciarEntidade.super.getAcaoFormularioVisualizar(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfAcaoFormulario> getAcoesFormulario() {
        return ItfAcaoGerenciarEntidade.super.getAcoesFormulario(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfAcaoSecundaria> getAcoesVinculadas() {
        return ItfAcaoGerenciarEntidade.super.getAcoesVinculadas(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfAcaoSecundaria> getAcoesVinculadasExcetoListarEnovo() {
        return ItfAcaoGerenciarEntidade.super.getAcoesVinculadasExcetoListarEnovo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public List<ItfAcaoSecundaria> getAcoesVinculadasDoContexto(ItfBeanGenerico pBean) {
        List<ItfAcaoSecundaria> acoesAutorizadas = new ArrayList<>();
        try {

            getAcoesVinculadas().stream()
                    .filter(pAcao -> SBCore.getCentralDeSessao().getSessaoAtual().isAcessoPermitido(pAcao))
                    .forEach(acoesAutorizadas::add);
            //if (pBean instanceof ItfBeanComStatus) {
            //    ItfBeanComStatus beanComStatus = (ItfBeanComStatus) pBean;
            //    acoesAutorizadas.stream().filter(acao -> !beanComStatus.getStatusEnum().isAcaoLiberada(acao))
            //            .forEach(acoesAutorizadas::remove);
            // }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro identificando ações permitidas do usuário", t);
        }
        return acoesAutorizadas;
    }

    @Override
    public boolean isAcaoDeRegistroExistente() {

        return false;
    }

    @Override
    public ItfAcaoDoSistema getAcaoCompativelEntidadeDivergente(ItfAcaoDoSistema pAcao, Class pEntidade) {
        FabTipoAcaoSistemaGenerica tipoAcao = pAcao.getTipoAcaoGenerica();
        if (pAcao.isUmaAcaoDeEntidade() && pAcao.getComoAcaoDeEntidade().getClasseRelacionada().getSimpleName().equals(pEntidade.getSimpleName())) {
            return null;
        }
        List<ItfAcaoDoSistema> acoesDoTipoDaEntidade = getAcoesVinculadasDesteTipoComEstaEntidade(tipoAcao, pEntidade);
        if (acoesDoTipoDaEntidade.isEmpty()) {
            return null;
        }
        if (acoesDoTipoDaEntidade.size() == 1) {
            return acoesDoTipoDaEntidade.get(0);
        }

        LongestCommonSubsequence calculoSimiliariedade = new LongestCommonSubsequence();
        Comparator<ItfAcaoDoSistema> comparadorAcaoPorSimilariedade = new Comparator<ItfAcaoDoSistema>() {
            @Override
            public int compare(ItfAcaoDoSistema o1, ItfAcaoDoSistema o2) {
                int scoreUm = calculoSimiliariedade.apply(pAcao.getNome(), o1.getNome());
                int scoreDois = calculoSimiliariedade.apply(pAcao.getNome(), o2.getNome());
                if (scoreUm == scoreDois) {
                    return 0;
                }
                if (scoreUm > scoreDois) {
                    return -1;
                }
                return 1;
            }
        };

        Collections.sort(acoesDoTipoDaEntidade, comparadorAcaoPorSimilariedade);
        return acoesDoTipoDaEntidade.get(0);
    }

    @Override
    public ItfAcaoDoSistema getSubAcaoByString(String pString) {
        try {

            List<ItfAcaoSecundaria> acoesVinc = getAcoesVinculadas();
            for (ItfAcaoDoSistema acao : acoesVinc) {
                String nomeUrlDaAcao = UtilSBCoreStringFiltros.gerarUrlAmigavel(acao.getNomeAcao().toLowerCase());

                if (nomeUrlDaAcao
                        .equals(UtilSBCoreStringFiltros.gerarUrlAmigavel(pString.toLowerCase()))) {
                    //equals ou .endsWith se confiar na programação holística
                    return acao;
                }
            }
            for (ItfAcaoDoSistema acao : acoesVinc) {
                String textoReferencia = acao.getEnumAcaoDoSistema().toString().toLowerCase();
                String textoParametro = pString.toLowerCase();
                if (textoReferencia.contains(textoParametro)) {
                    return acao;
                }
            }
            throw new UnsupportedOperationException("Sub ação [" + pString + "] não foi localizada em [" + getNomeUnico() + "]");
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro localizando ação por string na ação de gestão" + getEnumAcaoDoSistema().toString() + pString, t);
            throw new UnsupportedOperationException("Ação " + pString + " não localizada em" + getEnumAcaoDoSistema().toString());

        }
    }

}

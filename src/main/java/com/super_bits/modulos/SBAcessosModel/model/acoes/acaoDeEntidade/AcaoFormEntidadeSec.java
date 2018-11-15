/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoFormulario;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoSecundaria;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringNomeArquivosEDiretorios;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.estadoFormulario.FabEstadoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidadeSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfGrupoCampos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualSB;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Ação de formulário para entidade"}, plural = "Ações de entidade")
public class AcaoFormEntidadeSec extends AcaoSecundaria implements ItfAcaoFormularioEntidadeSecundaria, ItfAcaoFormularioEntidade {

    private String xhtml = AcaoFormulario.VARIAVEIS_ACAO_DO_SISTEMA.VIEW_NAO_IMPLEMENTADA.toString();
    @Transient
    private ItfAcaoDoSistema acaoExectarFormulario;
    @Transient
    private List<CaminhoCampoReflexao> campos = new ArrayList<>();
    @Transient
    private List<GrupoCampos> grupos;
    @Enumerated(EnumType.ORDINAL)
    private FabEstadoFormulario estadoFormulario;

    public AcaoFormEntidadeSec(ItfAcaoGerenciarEntidade pAcaoPrincipal, ItfFabricaAcoes pFabricaAcao, String pXhtml) {
        super(pAcaoPrincipal.getClasseRelacionada(), FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO, pFabricaAcao);
        xhtml = pXhtml;

    }

    public AcaoFormEntidadeSec() {
        super();
    }

    public AcaoFormEntidadeSec(ItfAcaoGerenciarEntidade pAcaoPrincipal, ItfFabricaAcoes pFabricaAcao) {
        super(pAcaoPrincipal.getClasseRelacionada(), FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO, pFabricaAcao);

    }

    public AcaoFormEntidadeSec(ItfAcaoGerenciarEntidade pAcaoPrincipal, ItfFabricaAcoes pFabricaAcao, FabTipoAcaoSistemaGenerica acaoGenerica) {
        super(pAcaoPrincipal.getClasseRelacionada(),
                FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO,
                pFabricaAcao,
                acaoGenerica);

        setAcaoPrincipal(pAcaoPrincipal);
    }

    /**
     *
     * Constructor para ações gerenciar entidade
     *
     * @param pclasse
     * @param pFabricaAcao
     * @param pXhtml
     */
    public AcaoFormEntidadeSec(Class pclasse, ItfFabricaAcoes pFabricaAcao, String pXhtml) {
        super(pclasse, FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO, pFabricaAcao);
        if (!this.getClass().isAssignableFrom(AcaoGestaoEntidade.class)) {
            throw new UnsupportedOperationException("Este constructor só deve ser usado por uma acaoGestaoEntidade");
        }
        setXhtml(pXhtml);

    }

    public AcaoFormEntidadeSec(Class pclasse, ItfFabricaAcoes pFabricaAcao) {
        super(pclasse, FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO, pFabricaAcao);

    }

    @Override
    public String getXhtml() {
        return xhtml;
    }

    @Override
    public ItfAcaoDoSistema getAcaoExectarFormulario() {
        return acaoExectarFormulario;
    }

    @Override
    public List<ItfCaminhoCampo> getCampos() {
        return (List) campos;
    }

    @Override
    public void setAcaoExectarFormulario(ItfAcaoDoSistema pAcaoExectarFormulario) {
        acaoExectarFormulario = pAcaoExectarFormulario;
    }

    @Override
    public void setCampos(List<ItfCaminhoCampo> pCampos) {
        campos = (List) pCampos;
    }

    /**
     *
     * Seta o XHTMl, caso tenha um caminho com mais de 2 casas seta o valor,
     * caso contrario seta o diretorio padrão + o nome enviado
     *
     * @param pXhtml
     */
    @Override
    public void setXhtml(String pXhtml) {

        if (pXhtml.split("/").length > 2) {
            this.xhtml = pXhtml;
        } else {
            xhtml = getDiretorioBaseAqrquivos() + "/" + pXhtml;
        }
    }

    @Override
    public boolean isUmaAcaoFormulario() {
        return true;
    }

    @Override
    public List<ItfGrupoCampos> getGruposDeCampos() {
        if (grupos == null) {
            grupos = new ArrayList<>();
        }
        if (grupos.isEmpty()) {
            grupos = UtilSBCoreReflexaoCaminhoCampo.buildAgrupamentoCampos(campos);
        }
        return (List) grupos;
    }

    @Override
    public GrupoCampos getGrupoCampoByID(int pID) {
        if (pID >= getGruposDeCampos().size() - 1) {
            pID = 0;
        }
        if (getGruposDeCampos().isEmpty()) {
            return null;
        }
        return grupos.get(pID);
    }

    @Override
    public String getPastaXhtml() {
        return UtilSBCoreStringNomeArquivosEDiretorios.getDiretorioMenosXCasas(getXhtml(), 1);
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return getComoSecundaria().getAcaoPrincipal();
    }

    @Override
    public FabEstadoFormulario getEstadoFormulario() {

        return this.estadoFormulario;

    }

    @Override
    public void setEstadoFormulario(FabEstadoFormulario pEstadoFormulario) {

        this.estadoFormulario = pEstadoFormulario;

    }

    @Override
    public ComponenteVisualSB getComponenteFormularioPadrao() {
        return (ComponenteVisualSB) getTipoAcaoGenerica().getComponentePadrao().getRegistro();
    }

}

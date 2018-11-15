/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.estadoFormulario.FabEstadoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfGrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Ação de Abertura de Formulário"}, plural = "Ações de abertura formulário")
public class AcaoFormulario extends AcaoDoSistema implements ItfAcaoFormulario {

    private String xhtml;
    @Transient
    private ItfAcaoDoSistema acaoExectarFormulario;
    @Transient
    private List<CaminhoCampoReflexao> campos;

    @Transient
    private List<GrupoCampos> grupos;

    private FabEstadoFormulario estadoFormulario;

    public static enum VARIAVEIS_ACAO_DO_SISTEMA {

        VIEW_NAO_IMPLEMENTADA,;

        @Override
        public String toString() {
            switch (this) {
                case VIEW_NAO_IMPLEMENTADA:
                    return "/resources/SBComp/SBSystemPages/naoimplementado.xhtml";

                default:
                    return super.toString();

            }

        }
    }

    @Deprecated
    public AcaoFormulario() {
        super();
        campos = new ArrayList<>();

    }

    public AcaoFormulario(FabTipoAcaoSistema ptipoAcao, ItfFabricaAcoes pAcao) {
        super(ptipoAcao, pAcao);
    }

    @Override
    public ItfAcaoSecundaria getComoSecundaria() {
        return (ItfAcaoSecundaria) this;
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
    public FabEstadoFormulario getEstadoFormulario() {

        return this.estadoFormulario;

    }

    @Override
    public void setEstadoFormulario(FabEstadoFormulario pEstadoFormulario) {

        this.estadoFormulario = pEstadoFormulario;

    }

    @Override
    public ItfComponenteVisualSB getComponenteFormularioPadrao() {
        return getTipoAcaoGenerica().getComponentePadrao().getRegistro();
    }

    @Override
    public ItfAcaoDoSistema getAcaoExectarFormulario() {
        return acaoExectarFormulario;
    }

    @Override
    public void setAcaoExectarFormulario(ItfAcaoDoSistema acaoExectarFormulario) {
        this.acaoExectarFormulario = acaoExectarFormulario;
    }

    @Override
    public List<ItfCaminhoCampo> getCampos() {
        return (List) campos;
    }

    @Override
    public void setCampos(List<ItfCaminhoCampo> campos) {
        this.campos = (List) campos;
    }

    @Override
    public String getXhtml() {
        return xhtml;
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
            String diretorioBase = getDiretorioBaseAqrquivos();
            xhtml = diretorioBase + "/" + pXhtml;
        }
    }

    @Override
    public boolean isUmaAcaoFormulario() {
        return true;
    }

    @Override
    public List<ItfGrupoCampos> getGruposDeCampos() {
        if (grupos.isEmpty()) {
            grupos = UtilSBCoreReflexaoCaminhoCampo.buildAgrupamentoCampos(campos);
        }
        return (List) grupos;
    }

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDeEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoFormulario;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class AcaoFormularioEntidade extends AcaoDeEntidade implements ItfAcaoFormulario, ItfAcaoSecundaria {

    private ItfAcaoDoSistema acaoPrincipal;
    private List<CaminhoCampoReflexao> camposDoFormulario;
    private String xhtml=AcaoFormulario.VARIAVEIS_ACAO_DO_SISTEMA.VIEW_NAO_IMPLEMENTADA.toString();
    private ItfAcaoDoSistema acaoExectarFormulario;
    private List<CaminhoCampoReflexao> campos;

    public AcaoFormularioEntidade(ItfAcaoGerenciarEntidade pAcaoPrincipal, ItfFabricaAcoes pFabricaAcao,String pXhtml) {
        super(pAcaoPrincipal.getClasseRelacionada(), FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO, pFabricaAcao);
        xhtml = pXhtml;
        camposDoFormulario = new ArrayList<>();
    }

    /**
     *
     * Constructor para ações gerenciar entidade
     * 
     * @param pFabricaAcao
     * @param pXhtml
     */
    public AcaoFormularioEntidade(Class pclasse, ItfFabricaAcoes pFabricaAcao,String pXhtml) {
        super(pclasse ,FabTipoAcaoSistema.ACAO_ENTIDADE_FORMULARIO, pFabricaAcao);
        if (!this.getClass().isAssignableFrom(AcaoGestaoEntidade.class)){
            throw new UnsupportedOperationException("Este constructor só deve ser usado por uma acaoGestaoEntidade");
        }
        xhtml = pXhtml;
    
        camposDoFormulario = new ArrayList<>();
    }

    
    
    
    
    


    public ItfAcaoDoSistema getAcaoPrincipal() {
        return acaoPrincipal;
    }

    public List<CaminhoCampoReflexao> getCamposDoFormulario() {
        return camposDoFormulario;
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
    public List<CaminhoCampoReflexao> getCampos() {
        return campos;
    }

    @Override
    public void setAcaoExectarFormulario(ItfAcaoDoSistema pAcaoExectarFormulario) {
        acaoExectarFormulario = pAcaoExectarFormulario;
    }

    @Override
    public void setCampos(List<CaminhoCampoReflexao> pCampos) {
        campos = pCampos;
    }

    @Override
    public void setXhtml(String pXhtml) {
        xhtml = pXhtml;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico;

import com.super_bits.modulos.SBAcessosModel.model.tokens.TokenAcesso;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(plural = "Token dinamico", tags = "Token acesso dinâmico", icone = "fa fa-key")
public class TokenAcessoDinamico extends TokenAcesso implements ItfTokenAcessoDinamico {

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String slugAcaoFormulario;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String codigoEntidade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String nomeEntidadeDoAcesso;

    @Override
    public String getSlugAcaoFormulario() {
        return slugAcaoFormulario;
    }

    @Override
    public void setSlugAcaoFormulario(String slugAcaoFormulario) {
        this.slugAcaoFormulario = slugAcaoFormulario;
    }

    @Override
    public String getCodigoEntidade() {
        return codigoEntidade;
    }

    @Override
    public void setCodigoEntidade(String codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }

    @Override
    public String getEntidadeDoAcesso() {
        return nomeEntidadeDoAcesso;
    }

    @Override
    public void setEntidadeDoAcesso(String entidadeDoAcesso) {
        this.nomeEntidadeDoAcesso = entidadeDoAcesso;
    }

}

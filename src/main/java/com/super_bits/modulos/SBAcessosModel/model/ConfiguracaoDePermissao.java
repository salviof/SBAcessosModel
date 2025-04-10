/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(plural = "Configurações de Permissao", tags = {"Configuração de Permissão"})
public class ConfiguracaoDePermissao extends EntidadeSimples {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nomeConfig;
    private String ultimaVersaoBanco;
    private boolean permitirCriacaoDeGrupos;
    private boolean permitirPermissaoDeUsuario;
    @ManyToOne(targetEntity = GrupoUsuarioSB.class)
    private GrupoUsuarioSB grupoUsuarioPadrao;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeConfig() {
        return nomeConfig;
    }

    public void setNomeConfig(String nomeConfig) {
        this.nomeConfig = nomeConfig;
    }

    public String getUltimaVersaoBanco() {
        return ultimaVersaoBanco;
    }

    public void setUltimaVersaoBanco(String ultimaVersaoBanco) {
        this.ultimaVersaoBanco = ultimaVersaoBanco;
    }

    public boolean isPermitirCriacaoDeGrupos() {
        return permitirCriacaoDeGrupos;
    }

    public void setPermitirCriacaoDeGrupos(boolean permitirCriacaoDeGrupos) {
        this.permitirCriacaoDeGrupos = permitirCriacaoDeGrupos;
    }

    public boolean isPermitirPermissaoDeUsuario() {
        return permitirPermissaoDeUsuario;
    }

    public void setPermitirPermissaoDeUsuario(boolean permitirPermissaoDeUsuario) {
        this.permitirPermissaoDeUsuario = permitirPermissaoDeUsuario;
    }

    public GrupoUsuarioSB getGrupoUsuarioPadrao() {
        return grupoUsuarioPadrao;
    }

    public void setGrupoUsuarioPadrao(GrupoUsuarioSB grupoUsuarioPadrao) {
        this.grupoUsuarioPadrao = grupoUsuarioPadrao;
    }

}

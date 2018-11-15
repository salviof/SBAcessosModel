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
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Salvio
 */
@Entity
@InfoObjetoSB(tags = {"Permissão por Grupos"}, plural = "Permissões por Grupo")
public class Permitido_Grupos extends EntidadeSimples {

    @Id
    @GenericGenerator(
            name = "geradorIdPermitidoGrupo",
            strategy = "com.super_bits.modulos.SBAcessosModel.model.GeradorIDPermitidoGrupo"
    )
    @GeneratedValue(generator = "geradorIdPermitidoGrupo")
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = GrupoUsuarioSB.class)
    private GrupoUsuarioSB grupo;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PermissaoSB.class)
    private PermissaoSB acesso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GrupoUsuarioSB getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoUsuarioSB grupo) {
        this.grupo = grupo;
    }

    public PermissaoSB getAcesso() {
        return acesso;
    }

    public void setAcesso(PermissaoSB acesso) {
        this.acesso = acesso;
    }

    @Override
    public String getNome() {
        if (nome == null) {
            nome = getAcesso().getNome() + " permitido para " + getGrupo().getNome();
        }
        return nome;
    }

    public int defineIdPermitidoGrupo() {
        GeradorIDPermitidoGrupo gerador = new GeradorIDPermitidoGrupo();
        id = (int) gerador.gerar(this);
        return id;
    }

}

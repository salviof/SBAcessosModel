/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.tokens;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenRecuperacaoEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author desenvolvedorninja01
 * @since 19/09/2019
 * @version 1.0
 */
@Entity(name = "Token")
@InfoObjetoSB(plural = "Tokens Acesso", tags = {"Token de Acesso"}, descricao = "Tokens de acesso", icone = "fa fa-key")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoToken")
public class TokenAcesso extends ItemSimples implements ItfTokenRecuperacaoEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String codigo;
    @InfoCampo(tipo = FabTipoAtributoObjeto.EMAIL)
    private String email;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    @Temporal(TemporalType.TIMESTAMP)
    private Date validade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCriacao;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Date getValidade() {
        return validade;
    }

    @Override
    public void setValidade(Date validade) {
        this.validade = validade;
    }

    @Override
    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    @Override
    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

}

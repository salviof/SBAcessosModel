/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ItfFabricaModulo;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanEstatico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * Representa o modulo da aplicação
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 19/12/2015
 * @version 1.0
 *
 */
@Entity
@InfoObjetoSB(tags = {"Modulo do Sistema"}, plural = "Modulos do Sistema")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoModulo")
public class ModuloAcaoSistema extends EntidadeSimples implements ItfModuloAcaoSistema, ItfBeanEstatico {

    @Id
    @GenericGenerator(
            name = "geradorIdModulo",
            strategy = "com.super_bits.modulosSB.Persistencia.geradorDeId.GeradorIDObjVinculadoEnum"
    )
    @GeneratedValue(generator = "geradorIdModulo")
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_DESCRITIVO)
    private String descricao;

    @Temporal(TemporalType.DATE)
    private Date dataHoraCriacao;
    private boolean umModuloNativo = false;
    private String iconeDaClasse = "fa fa-puzzle-piece";
    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoModulo;

    @Transient
    private List<AcaoDoSistema> selecaoAcoes;

    public List<AcaoDoSistema> getSelecaoAcoes() {
        return selecaoAcoes;
    }

    public ModuloAcaoSistema() {
        super();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     *
     * @return
     */
    @Override
    public List<ItfAcaoDoSistema> getAcoes() {

        return new ArrayList<>();
    }

    @PrePersist
    public void configuracoesInsert() {
        dataHoraCriacao = new Date();
    }

    @PreUpdate
    public void configuracoesMerge() {
        if (dataHoraCriacao == null) {
            dataHoraCriacao = new Date();
        }
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public void setSelecaoAcoes(List<AcaoDoSistema> SelecaoAcoes) {
        this.selecaoAcoes = SelecaoAcoes;
    }

    @Override
    public List<ItfAcaoDoSistema> getAcoesGestaoMB() {
        List<ItfAcaoDoSistema> listaacaoGestao = new ArrayList<>();

        for (ItfAcaoDoSistema acao : getAcoes()) {
            if (acao.isUmaAcaoGestaoDominio()) {
                listaacaoGestao.add((AcaoDoSistema) acao);
            }
        }
        return listaacaoGestao;
    }

    @Override
    public boolean isUmModuloNativo() {
        return umModuloNativo;
    }

    public void setUmModuloNativo(boolean umModuloNativo) {
        this.umModuloNativo = umModuloNativo;
    }

    @Override
    public String getIconeDaClasse() {
        return iconeDaClasse;
    }

    public void setIconeDaClasse(String iconeDaClasse) {
        this.iconeDaClasse = iconeDaClasse;
    }

    public String getTipoModulo() {
        return tipoModulo;
    }

    public void setTipoModulo(String tipoModulo) {
        this.tipoModulo = tipoModulo;
    }

    @Override
    public void setEnumVinculado(ItfFabrica pFabrica) {
        if (isTemCampoAnotado(FabTipoAtributoObjeto.ENUM_FABRICA)) {
            setValorByTipoCampoEsperado(FabTipoAtributoObjeto.ENUM_FABRICA, pFabrica);
        }

    }

    @Override
    public ItfFabrica getFabricaObjeto() {
        return getEnumVinculado();
    }

    @Override
    public ItfFabricaModulo getEnumVinculado() {
        if (isTemCampoAnotado(FabTipoAtributoObjeto.ENUM_FABRICA)) {
            return (ItfFabricaModulo) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.ENUM_FABRICA);
        } else {
            if (!SBCore.isEmModoProducao()) {
                return FabModulosSistemaSB.ADMIN_TOOLS;
            } else {
                return FabModulosSistemaSB.PAGINAS_DO_SISTEMA;
            }
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeNormal;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.LocalizacaoPostavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocalPostagem;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 *
 *
 * @author Salvio
 */
@Entity
@InfoObjetoSB(tags = {"Usuário"}, plural = "Usuários", icone = "fa fa-user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoUsuario")
@EntityListeners(ListenerEntidadePadrao.class)
public class UsuarioSB extends EntidadeNormal implements ItfUsuario, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME, label = "Nome", descricao = "Nome do Usuário")
    @NotNull
    private String nome;

    @Column(length = 255, unique = true)
    @NotNull
    @InfoCampo(tipo = FabTipoAtributoObjeto.EMAIL, label = "Email", descricao = "Email que o usuário irá utilizar para acessar o sistema")
    private String email;

    @NotNull
    @Column(unique = true)
    @InfoCampo(label = "Usuário")
    private String apelido;

    @InfoCampo(tipo = FabTipoAtributoObjeto.SENHA, label = "Senha", descricao = "Senha que o usuário irá utilizar para acessar o sistema")
    @Column(length = 256)
    @NotNull
    private String senha;

    private String complemento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_LOCALIZACAO, label = "localização", descricao = "Localização do usuário", obrigatorio = false)
    @ManyToOne(targetEntity = LocalizacaoPostavel.class, optional = true)
    private LocalizacaoPostavel localizacao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TELEFONE_CELULAR, label = "Celular", descricao = "Telefone celular de contato do usuário")
    private String telefone;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoUsuario;

    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_DESCRITIVO, label = "Data Cadastro", descricao = "Data de cadastramento do usuário")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;//TODO REMOVER DUPLICADO

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO, label = "Status", descricao = "Status do usuário (ativo/inativo)", somenteLeitura = true)
    @InfoCampoVerdadeiroOuFalso()
    private boolean ativo = Boolean.TRUE;

    @ManyToOne(targetEntity = GrupoUsuarioSB.class, fetch = FetchType.EAGER, optional = false)
    @NotNull
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Grupo", descricao = "Grupo de usuário que irá permitir acesso as funcionalidades")
    private GrupoUsuarioSB grupo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Grupos Adicionais", descricao = "Grupos do usuário")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
    private List<GrupoUsuarioSB> gruposAdicionais;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO, label = "Data/Hora Alteração", descricao = "Data e hora de alteração do perfil do usuário")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAlteracao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO, label = "Data/Hora Inserção", descricao = "Data e hora de inserção do perfil do usuário")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraInsersao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, label = "Usuário Inserção", descricao = "Usuário que fez a inserção de outro na base de dados")
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioInsercao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO, label = "Usuário Alteração", descricao = "Usuário que fez a alteração de outro na base de dados")
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioAlteracao;

    public UsuarioSB() {
        super();
        grupo = new GrupoUsuarioSB();
    }

    @Override
    public Date getDataHoraAlteracao() {
        return dataHoraAlteracao;
    }

    public void setDataHoraAlteracao(Date dataHoraAlteracao) {
        this.dataHoraAlteracao = dataHoraAlteracao;
    }

    public Date getDataHoraInsersao() {
        return dataHoraInsersao;
    }

    public void setDataHoraInsersao(Date dataHoraInsersao) {
        this.dataHoraInsersao = dataHoraInsersao;
    }

    public UsuarioSB getUsuarioInsercao() {
        return usuarioInsercao;
    }

    public void setUsuarioInsercao(UsuarioSB usuarioInsercao) {
        this.usuarioInsercao = usuarioInsercao;
    }

    @Override
    public UsuarioSB getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(UsuarioSB usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String getComplemento() {
        return complemento;
    }

    @Override
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
    public ItfGrupoUsuario getGrupo() {
        return grupo;
    }

    @Override
    public void setGrupo(ItfGrupoUsuario grupo) {
        this.grupo = (GrupoUsuarioSB) grupo;
    }

    @Override
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int pId) {
        id = pId;
    }

    @Override
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setGrupo(GrupoUsuarioSB grupo) {
        this.grupo = grupo;
    }

    @PrePersist
    public void configuracoesInsert() {
        dataCadastro = new Date();
    }

    @PreUpdate
    public void ajustaConfiguracoes() {
        System.out.println("Ajustando Configuracoes para salvar");
        if (dataCadastro == null) {
            dataCadastro = new Date();
        }
        if (email == null) {
            if (getGrupo() != null) {
                email = apelido + "@" + getGrupo().getNome();

            }
        }
        if (apelido == null) {
            if (email != null) {
                apelido = email;
            }
        }

        if (apelido != null) {
            if (nome == null) {
                nome = apelido;
            }
        }

        if (nome != null) {
            if (apelido == null) {
                apelido = nome;
            }
        }
        if (getGruposAdicionais() != null && getGrupo() != null) {

            if (!getGruposAdicionais().contains(getGrupo())) {
                getGruposAdicionais().add(grupo);
            }
        }

    }

    @Override
    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public List<ItfGrupoUsuario> getGruposAdicionais() {
        return (List) gruposAdicionais;
    }

    public void setGruposAdicionais(List<GrupoUsuarioSB> gruposAdicionais) {
        this.gruposAdicionais = gruposAdicionais;
    }

    public boolean isGrupoPrincipal(GrupoUsuarioSB pGrupo) {
        return (pGrupo == grupo);
    }

    @Override
    public ItfLocalPostagem getLocalizacao() {
        return localizacao;
    }

    @Override
    public void setLocalizacao(ItfLocal pLocal) {
        setValorByTipoCampoEsperado(FabTipoAtributoObjeto.LC_LOCALIZACAO, pLocal);
    }

    @Override
    public void instanciarNovoEndereco() {
        localizacao = new LocalizacaoPostavel();
    }

}

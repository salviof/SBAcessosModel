/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao.TIPO_AUTENTICACAO;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.hibernate.annotations.GenericGenerator;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 *
 *
 * @author Salvio
 */
@Entity
@InfoObjetoSB(tags = {"Informações de Permissão"}, plural = "Informações de Permissão")
public class PermissaoSB extends EntidadeSimplesORM implements ItfPermissao, Serializable {

    @Id
    @GenericGenerator(name = "geradorIdPermissao", strategy = "com.super_bits.modulos.SBAcessosModel.model.GeradorIdPermissao")
    @GeneratedValue(generator = "geradorIdPermissao")
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nomeAcesso;

    private Long idacaoDoSistema;

    private TIPO_AUTENTICACAO tipoAutenticacao;

    @Transient
    private List<ComoUsuario> listaTodosUsuarios;

    @Transient
    private List<ComoGrupoUsuario> listaTodosGruposUsuarios;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Permitido_Usuarios",
            joinColumns = @JoinColumn(name = "acesso_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<UsuarioSB> usuariosPermitidos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Negado_Usuarios",
            joinColumns = @JoinColumn(name = "acesso_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<UsuarioSB> usuariosNegados;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Permitido_Grupos",
            joinColumns = @JoinColumn(name = "acesso_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<GrupoUsuarioSB> gruposPermitidos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Negado_Grupos",
            joinColumns = @JoinColumn(name = "acesso_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<GrupoUsuarioSB> gruposNegados;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private Long idAcaoGestao;

    @Deprecated
    public PermissaoSB() {
        super();

    }

    public void setIDPermissao() {
        configIDACAO(getAcao());
    }

    /**
     *
     * O acesso deve ser criado a partir de um método.
     *
     * O sistema irá analizar as anotações
     *
     *
     * @param pMetodo
     */
    public PermissaoSB(Method pMetodo) {

        this(UtilSBController.getFabricaAcaoByMetodo(pMetodo));

    }

    public PermissaoSB(ComoFabricaAcoes fabricaAcoes) {
        this((AcaoDoSistema) fabricaAcoes.getRegistro());
    }

    public final long configIDACAO(AcaoDoSistema pAcaoDoSistema) {
        id = UtilSBController.gerarIDAcaoDoSistema(pAcaoDoSistema.getEnumAcaoDoSistema());
        return id;

    }

    public PermissaoSB(AcaoDoSistema pAcaoDoSistema) {
        super();
        configIDACAO(pAcaoDoSistema);
        nomeAcesso = pAcaoDoSistema.getNomeUnico();
        idacaoDoSistema = pAcaoDoSistema.getId();
        if (pAcaoDoSistema.getAcaoDeGestaoEntidade() != null) {
            idAcaoGestao = UtilSBController.gerarIDAcaoDoSistema(pAcaoDoSistema.getAcaoDeGestaoEntidade().getEnumAcaoDoSistema());
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return getAcao().getDescricao();
    }

    public void setGruposPermitidos(List<GrupoUsuarioSB> gruposPermitidos) {
        this.gruposPermitidos = gruposPermitidos;
    }

    public void setGruposNegados(List<GrupoUsuarioSB> gruposNegados) {
        this.gruposNegados = gruposNegados;
    }

    public void addUsuarioPermitido(UsuarioSB pUsuario) {
        usuariosPermitidos.add(pUsuario);
    }

    public void addGrupoPermitido(GrupoUsuarioSB pGrupoPermitido) {
        gruposPermitidos.add(pGrupoPermitido);
    }

    public void setNomeAcesso(String pNomeAcesso) {
        nomeAcesso = pNomeAcesso;
    }

    @Override
    public String getDescricaoAcesso() {
        return getAcao().getDescricao();
    }

    @Override
    public List<ComoUsuario> getUsuariosPermitidos() {
        return (List) usuariosPermitidos;
    }

    @Override
    public List<ComoGrupoUsuario> getGruposPermitidos() {
        return (List) gruposPermitidos;
    }

    @Override
    public List<ComoUsuario> getUsuariosDisponiveis() {
        try {
            listaTodosUsuarios = (List) UtilSBPersistencia.getListaTodos(UsuarioSB.class
            );

            List<ComoUsuario> usuariosDisponiveis = new ArrayList<>();
            usuariosDisponiveis = listaTodosUsuarios;

            if (getUsuariosNegados()
                    .isEmpty() == false) {
                usuariosDisponiveis.removeAll(getUsuariosNegados());
            }

            if (getUsuariosPermitidos()
                    .isEmpty() == false) {
                usuariosDisponiveis.removeAll(getUsuariosPermitidos());
            }

            return usuariosDisponiveis;
        } catch (Exception exception) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: getUsuariosDisponiveis \n\n", exception);

        }

        return null;
    }

    @Override
    public List<ComoUsuario> getUsuariosNegados() {
        return (List) usuariosNegados;
    }

    @Override
    public List<ComoGrupoUsuario> getGruposNegados() {
        return (List) gruposNegados;
    }

    @Override
    public List<ComoGrupoUsuario> getGruposDisponiveis() {
        try {
            listaTodosGruposUsuarios = (List) UtilSBPersistencia.getListaTodos(GrupoUsuarioSB.class
            );

            List<ComoGrupoUsuario> grupoUsuariosDisponiveis = new ArrayList<>();
            grupoUsuariosDisponiveis = listaTodosGruposUsuarios;

            if (getGruposNegados()
                    .isEmpty() == false) {
                grupoUsuariosDisponiveis.removeAll(getGruposNegados());
            }

            if (getGruposPermitidos()
                    .isEmpty() == false) {
                grupoUsuariosDisponiveis.removeAll(getGruposPermitidos());
            }

            return grupoUsuariosDisponiveis;
        } catch (Exception exception) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: getGruposUsuarioDisponiveis \n\n", exception);

        }

        return null;
    }

    @Override
    public TIPO_AUTENTICACAO getTipoAutenticacao() {
        return tipoAutenticacao;
    }

    @Override
    public AcaoDoSistema getAcao() {
        return (AcaoDoSistema) MapaAcoesSistema.getAcaoDoSistemaById(idacaoDoSistema);
    }

    public Long getIdAcaoGestao() {
        return idAcaoGestao;
    }

    public void setIdAcaoGestao(Long idAcaoGestao) {
        this.idAcaoGestao = idAcaoGestao;
    }

    @Override
    public void setId(Long pID) {
        id = pID;
    }

}

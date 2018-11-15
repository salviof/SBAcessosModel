/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao.TIPO_AUTENTICACAO;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
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

/**
 *
 *
 *
 * @author Salvio
 */
@Entity
@InfoObjetoSB(tags = {"Informações de Permissão"}, plural = "Informações de Permissão")
public class PermissaoSB extends EntidadeSimples implements ItfPermissao, Serializable {

    @Id
    @GenericGenerator(
            name = "geradorIdPermissao",
            strategy = "com.super_bits.modulos.SBAcessosModel.model.GeradorIdPermissao"
    )
    @GeneratedValue(generator = "geradorIdPermissao")
    private int id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nomeAcesso;

    private int idacaoDoSistema;

    private TIPO_AUTENTICACAO tipoAutenticacao;

    @Transient
    private List<ItfUsuario> listaTodosUsuarios;

    @Transient
    private List<ItfGrupoUsuario> listaTodosGruposUsuarios;

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
    private int idAcaoGestao;

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

    public PermissaoSB(ItfFabricaAcoes fabricaAcoes) {
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
    public int getId() {
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
    public List<ItfUsuario> getUsuariosPermitidos() {
        return (List) usuariosPermitidos;
    }

    @Override
    public List<ItfGrupoUsuario> getGruposPermitidos() {
        return (List) gruposPermitidos;
    }

    @Override
    public List<ItfUsuario> getUsuariosDisponiveis() {
        try {
            listaTodosUsuarios = (List<ItfUsuario>) UtilSBPersistencia.getListaTodos(UsuarioSB.class
            );

            List<ItfUsuario> usuariosDisponiveis = new ArrayList<>();
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
    public List<ItfUsuario> getUsuariosNegados() {
        return (List) usuariosNegados;
    }

    @Override
    public List<ItfGrupoUsuario> getGruposNegados() {
        return (List) gruposNegados;
    }

    @Override
    public List<ItfGrupoUsuario> getGruposDisponiveis() {
        try {
            listaTodosGruposUsuarios = (List<ItfGrupoUsuario>) UtilSBPersistencia.getListaTodos(GrupoUsuarioSB.class
            );

            List<ItfGrupoUsuario> grupoUsuariosDisponiveis = new ArrayList<>();
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

    public int getIdAcaoGestao() {
        return idAcaoGestao;
    }

    public void setIdAcaoGestao(int idAcaoGestao) {
        this.idAcaoGestao = idAcaoGestao;
    }

    @Override
    public void setId(int pID) {
        id = pID;
    }

}

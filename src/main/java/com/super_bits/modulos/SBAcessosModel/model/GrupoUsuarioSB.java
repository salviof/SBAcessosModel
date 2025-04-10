/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulos.SBAcessosModel.model.conversores.JpaConversorAcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.grupoUsuario.ListaGrupoUsuario;
import com.super_bits.modulos.SBAcessosModel.model.grupoUsuario.ListasGrupoUsuario;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringSlugs;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuaioEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Salvio
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoGrupoUsuario")
@InfoObjetoSB(tags = {"Grupos de Usuário"}, plural = "Grupos de Usuários", icone = "fa fa-users")
public class GrupoUsuarioSB extends EntidadeSimples implements ItfGrupoUsuaioEditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, label = "Nome", obrigatorio = true)
    @NotNull
    @Column(unique = true)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, label = "Descrição", obrigatorio = true)
    @NotNull
    private String descricao;
    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoGrupoUsuario;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "modulos_grupo", uniqueConstraints = @UniqueConstraint(columnNames = {"grupo_id", "modulo_id"}),
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "modulo_id"))
    private List<ModuloAcaoSistema> modulos;

    @OneToMany(mappedBy = "grupo", targetEntity = Permitido_Grupos.class, cascade = CascadeType.ALL,
            orphanRemoval = true)
    @InfoCampo(label = "Permissão", tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<Permitido_Grupos> permissoesConcedidas = new ArrayList<>();

    @Transient
    @ListaGrupoUsuario(lista = ListasGrupoUsuario.PERMISSOES_DO_GRUPO)
    private List<PermissaoSB> acessos;

    @Transient
    @ListaGrupoUsuario(lista = ListasGrupoUsuario.PERMISSAO_POR_GESTAO)
    private List<Permitido_Grupos> permissaoPorAcao;

    @ManyToOne
    private ModuloAcaoSistema moduloPrincipal;

    private boolean tipoGrupoNativo;

    @Convert(converter = JpaConversorAcaoDoSistema.class)
    private ItfFabricaAcoes paginaInicial;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_grupo",
            uniqueConstraints = @UniqueConstraint(name = "usuarioDuplicado", columnNames = {"usuario_id", "grupo_id"}),
            joinColumns = {
                @JoinColumn(name = "grupo_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "usuario_id",
                        nullable = false, updatable = false)}
    )
    private List<UsuarioSB> usuarios;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAlteracao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraInsersao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioInsercao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO)
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioAlteracao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean ativo = true;

    public GrupoUsuarioSB() {

        modulos = new ArrayList<>();
    }

    public void adcionaUsuario(ItfUsuario pUsuario) {
        usuarios.add((UsuarioSB) pUsuario);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public List<ItfUsuario> getUsuarios() {
        return (List) usuarios;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public ItfFabricaAcoes getPaginaInicial() {
        return paginaInicial;
    }

    public void setPaginaInicial(ItfFabricaAcoes paginaInicial) {
        this.paginaInicial = paginaInicial;
    }

    public boolean isTipoGrupoNativo() {
        return tipoGrupoNativo;
    }

    public void setTipoGrupoNativo(boolean tipoGrupoNativo) {
        this.tipoGrupoNativo = tipoGrupoNativo;
    }

    public List<ModuloAcaoSistema> getModulos() {
        return modulos;
    }

    public void adicionarModulo(ModuloAcaoSistema modulo) {
        if (!modulos.contains(modulo)) {
            modulos.add(modulo);
        }
    }

    @Override
    public ModuloAcaoSistema getModuloPrincipal() {
        if (moduloPrincipal == null) {
            moduloPrincipal = getModulos().get(0);
        }
        return moduloPrincipal;
    }

    public String getTipoGrupoUsuario() {
        return tipoGrupoUsuario;
    }

    public void setTipoGrupoUsuario(String tipoGrupoUsuario) {
        this.tipoGrupoUsuario = tipoGrupoUsuario;
    }

    public List<Permitido_Grupos> getPermissoesConcedidas() {

        return permissoesConcedidas;
    }

    public List<Permitido_Grupos> getPermissaoPorAcao(ItfAcaoGerenciarEntidade pAcao) {
        if (permissaoPorAcao == null) {
            permissaoPorAcao = ListasGrupoUsuario.PERMISSAO_POR_GESTAO.getLista(this, pAcao);
        }

        return permissaoPorAcao;
    }

    public List<PermissaoSB> getAcessos() {
        acessos = ListasGrupoUsuario.PERMISSOES_DO_GRUPO.getLista(this);

        return acessos;
    }

    public void renovarNovasPermissoes(List<PermissaoSB> pm) {

    }

    @Override
    public String getEmail() {
        try {
            Field campo = getCampoReflexaoByAnotacao(FabTipoAtributoObjeto.EMAIL);
            if (campo != null) {

                String valor = (String) campo.get(this);
                if (valor != null) {
                    return valor;
                }
            }
            return UtilSBCoreStringSlugs.gerarSlugSimples(getNome()) + "@" + UtilSBCoreStringSlugs.gerarSlugSimples(getModuloPrincipal().getNome());
        } catch (IllegalAccessException | IllegalArgumentException t) {
            return UtilSBCoreStringSlugs.gerarSlugSimples(getNome()) + "@" + UtilSBCoreStringSlugs.gerarSlugSimples(getModuloPrincipal().getNome());
        }
    }

    public void setModuloPrincipal(ModuloAcaoSistema moduloPrincipal) {
        this.moduloPrincipal = moduloPrincipal;
    }

    @Override
    public boolean isGrupoNativoSistema() {
        return false;
    }

}

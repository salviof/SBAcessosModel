/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.Ips;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Ips"}, plural = "Ips")
public class Ips extends EntidadeNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 15, nullable = false)
    @NotNull
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, label = "Inicial", descricao = "Inicio da faixa de ip's autorizados")
    private String inicialFaixa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, label = "Final", descricao = "Final da faixa de ip's autorizados")
    @Column(length = 15, nullable = false)
    @NotNull
    private String finalFaixa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, label = "DNS", descricao = "DNS para validação")
    @NotNull
    private String dns;

    @ManyToOne(targetEntity = TipoIp.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @InfoCampo(fabricaDeOpcoes = FabTipoIp.class)
    private TipoIp tipo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO, label = "Status", descricao = "Status do ip(ativo/inativo)")
    private boolean ativo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO, label = "Alterado em", descricao = "Data de alteração do ip")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAlteracao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO, label = "Usuário alteração", descricao = "Usuário que alterou o IP")
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioAlteracao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, label = "Usuário inserção", descricao = "Usuário que inseriu o IP")
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioInsercao;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getInicialFaixa() {
        return inicialFaixa;
    }

    public void setInicialFaixa(String inicialFaixa) {
        this.inicialFaixa = inicialFaixa;
    }

    public String getFinalFaixa() {
        return finalFaixa;
    }

    public void setFinalFaixa(String finalFaixa) {
        this.finalFaixa = finalFaixa;
    }

    public Ips() {
        super();
        tipo = new TipoIp();
    }

    public TipoIp getTipo() {
        return tipo;
    }

    public void setTipo(TipoIp tipo) {
        this.tipo = tipo;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

}

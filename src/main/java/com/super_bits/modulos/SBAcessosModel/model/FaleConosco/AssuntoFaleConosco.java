/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.FaleConosco;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Assunto"}, plural = "Assuntos")
@DiscriminatorColumn(name = "tipoAssunto")
public class AssuntoFaleConosco extends EntidadeORMNormal {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID, label = "ID", descricao = "Identificação do assunto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO, label = "Status", descricao = "Status do assunto(ativo/inativo)")
    @NotNull
    private boolean ativo;

    @NotNull
    @Column(length = 200, nullable = false)
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, label = "assunto", descricao = "Assunto que será cadastrado para ser mostrado no fale conosco")
    private String assunto;

    @Temporal(javax.persistence.TemporalType.DATE)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO, label = "Data Inclusão", descricao = "Data de inclusão do assunto")
    private Date dataInclusao;

    @Temporal(javax.persistence.TemporalType.DATE)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO, label = "Data Alteração", descricao = "Data de alteração do assunto")
    private Date dataAlteracao;

    @NotNull
    @Column(length = 100, nullable = false)
    @InfoCampo(tipo = FabTipoAtributoObjeto.EMAIL, label = "E-mail Destino", descricao = "Destinatário do fale-conosco")
    private String destinatario;

    @NotNull
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int tempoResposta;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoAssunto;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return assunto;
    }

    @Override
    public void setNome(String assunto) {
        this.assunto = assunto;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public Integer getTempoResposta() {
        return tempoResposta;
    }

    public void setTempoResposta(Integer tempoResposta) {
        this.tempoResposta = tempoResposta;
    }

    public AssuntoFaleConosco() {
        super();
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

}

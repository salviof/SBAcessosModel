/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.FaleConosco;

import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoComunicacaoPadrao;
import com.super_bits.modulos.SBAcessosModel.controller.InfoAcaoComunicacao;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeNormal;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author cristopher
 */
@Entity
@DiscriminatorColumn(name = "tipoFaleConosco")
@InfoObjetoSB(tags = {"Fale Conosco"}, icone = "fa fa-bullhorn", plural = "Mensagens Fale Conosco")
@InfoAcaoComunicacao(acao = FabAcaoComunicacaoPadrao.FALE_CONOSCO_CAD_CTR_SALVAR_MEGE)
@EntityListeners(ListenerEntidadePadrao.class)
public class FaleConosco extends EntidadeNormal {

    public FaleConosco() {
        super();
    }

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO, label = "Data Do Contato")
    private Date dataInclusao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DATA, label = "Data Resposta")
    @Temporal(TemporalType.DATE)
    private Date dataResposta;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String remetente;
    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoFaleConosco;

    @InfoCampo(tipo = FabTipoAtributoObjeto.EMAIL)
    private String email;

    @InfoCampo(tipo = FabTipoAtributoObjeto.EMAIL)
    private String emailDestinatario;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    @ManyToOne(targetEntity = AssuntoFaleConosco.class)
    @NotNull
    private AssuntoFaleConosco assunto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML)
    private String mensagem;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO)
    private boolean ativo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO, label = "Data alteração", descricao = "Data de alteração do fale-conosco")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAlteracao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, label = "Usuário inserção", descricao = "O remetente da mensagem")
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioInsercao;

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public UsuarioSB getUsuarioInsercao() {
        return usuarioInsercao;
    }

    public void setUsuarioInsercao(UsuarioSB usuarioInsercao) {
        this.usuarioInsercao = usuarioInsercao;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(Date dataResposta) {
        this.dataResposta = dataResposta;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public AssuntoFaleConosco getAssunto() {
        return assunto;
    }

    public void setAssunto(AssuntoFaleConosco assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getTipoFaleConosco() {
        return tipoFaleConosco;
    }

    public void setTipoFaleConosco(String tipoFaleConosco) {
        this.tipoFaleConosco = tipoFaleConosco;
    }

}

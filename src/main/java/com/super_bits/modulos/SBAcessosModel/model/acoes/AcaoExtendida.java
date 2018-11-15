/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampoInvalido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAssistenteDeLocalizacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.TipoOrganizacaoDadosEndereco;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class AcaoExtendida implements ItfAcaoDoSistema {

    private final ItfAcaoDoSistema acaoOrigem;

    private String nomeInicialAcao;
    private String nomeAlternativo;

    public AcaoExtendida(ItfAcaoDoSistema pAcaoDoSistema) {

        acaoOrigem = pAcaoDoSistema;

    }

    public AcaoExtendida(ItfAcaoDoSistema pAcaoDoSistema, String novoNome) {

        this(pAcaoDoSistema);
        nomeInicialAcao = acaoOrigem.getNomeAcao();
        nomeAlternativo = novoNome;

    }

    @Override
    public void setNomeAcao(String pNome) {

        acaoOrigem.setNomeAcao(pNome);
    }

    @Override
    public String getNomeAcao() {

        return nomeAlternativo;

    }

    @Override
    public String getIconeAcao() {

        return acaoOrigem.getIconeAcao();

    }

    @Override
    public String getCor() {

        return acaoOrigem.getCor();

    }

    @Override
    public String getDescricao() {

        return acaoOrigem.getDescricao();

    }

    @Override
    public void setDescricao(String pDescricao) {

        acaoOrigem.setDescricao(pDescricao);

    }

    @Override
    public boolean isPrecisaPermissao() {

        return acaoOrigem.isPrecisaPermissao();

    }

    @Override
    public void setId(int pId) {

        acaoOrigem.setId(pId);

    }

    @Override
    public void setIconeAcao(String pIcone) {

        acaoOrigem.setIconeAcao(pIcone);

    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {

        acaoOrigem.setModuloAcaoSistema(pmodulo);

    }

    @Override
    public ItfModuloAcaoSistema getModulo() {

        return acaoOrigem.getModulo();

    }

    @Override
    public boolean isConfigurado() {

        return acaoOrigem.isConfigurado();

    }

    @Override
    public String getNomeUnico() {

        return acaoOrigem.getNomeUnico();

    }

    @Override
    public String getNomeEnumOriginal() {

        return acaoOrigem.getNomeEnumOriginal();

    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {

        return acaoOrigem.getTipoAcaoSistema();

    }

    @Override
    public ItfFabricaAcoes getEnumAcaoDoSistema() {

        return acaoOrigem.getEnumAcaoDoSistema();

    }

    @Override
    public void configurarPropriedadesBasicas(ItfAcaoDoSistema pAcaoDoSistema) {

        acaoOrigem.configurarPropriedadesBasicas(pAcaoDoSistema);

    }

    @Override
    public String getIdDescritivoJira() {

        return acaoOrigem.getIdDescritivoJira();

    }

    @Override
    public void setIdDescritivoJira(String pIdJira) {
        acaoOrigem.setIdDescritivoJira(pIdJira);
    }

    @Override
    public void setPrecisaPermissao(boolean pPermissao) {

        acaoOrigem.setPrecisaPermissao(pPermissao);

    }

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoGenerica() {

        return acaoOrigem.getTipoAcaoGenerica();

    }

    @Override
    public boolean isUmaAcaoFormulario() {

        return acaoOrigem.isUmaAcaoFormulario();

    }

    @Override
    public boolean isTemAcaoPrincipal() {
        return acaoOrigem.isTemAcaoPrincipal();
    }

    @Override
    public boolean isUmaAcaoGenerica() {
        return acaoOrigem.isUmaAcaoGenerica();
    }

    @Override
    public boolean isUmaAcaoGestaoDominio() {
        return acaoOrigem.isUmaAcaoGestaoDominio();
    }

    @Override
    public boolean isUmaAcaoSessaoMenu() {
        return acaoOrigem.isUmaAcaoSessaoMenu();
    }

    @Override
    public boolean isUmaAcaoDeEntidade() {
        return acaoOrigem.isUmaAcaoDeEntidade();
    }

    @Override
    public boolean isUmaAcaoController() {
        return acaoOrigem.isUmaAcaoController();
    }

    @Override
    public String getNomeDominio() {
        return acaoOrigem.getNomeDominio();
    }

    @Override
    public ItfAcaoFormulario getComoFormulario() {
        return acaoOrigem.getComoFormulario();
    }

    @Override
    public ItfAcaoFormularioEntidade getComoFormularioEntidade() {
        return acaoOrigem.getComoFormularioEntidade();
    }

    @Override
    public ItfAcaoGerenciarEntidade getComoGestaoEntidade() {
        return acaoOrigem.getAcaoDeGestaoEntidade();
    }

    @Override
    public ItfAcaoController getComoController() {
        return acaoOrigem.getComoController();
    }

    @Override
    public ItfAcaoSecundaria getComoSecundaria() {
        return acaoOrigem.getComoSecundaria();
    }

    @Override
    public ItfAcaoControllerEntidade getComoControllerEntidade() {
        return acaoOrigem.getComoControllerEntidade();
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return acaoOrigem.getAcaoDeGestaoEntidade();
    }

    @Override
    public String getNomeUnicoSlug() {
        return acaoOrigem.getNomeUnicoSlug();
    }

    @Override
    public boolean validar() {
        return acaoOrigem.validar();
    }

    @Override
    public List<ItfMensagem> validarComMensagens() {
        return acaoOrigem.validarComMensagens();
    }

    @Override
    public String getNomeCurto() {
        return acaoOrigem.getNomeCurto();
    }

    @Override
    public String getNome() {
        return nomeAlternativo;
    }

    @Override
    public String getIconeDaClasse() {
        return acaoOrigem.getIconeDaClasse();
    }

    @Override
    public String getXhtmlVisao() {
        return acaoOrigem.getXhtmlVisao();
    }

    @Override
    public int getId() {
        return acaoOrigem.getId();
    }

    @Override
    public String getImgPequena() {
        return acaoOrigem.getImgPequena();
    }

    @Override
    public int configIDPeloNome() {
        return acaoOrigem.configIDPeloNome();
    }

    @Override
    public String getNomeDoObjeto() {
        return acaoOrigem.getNomeDoObjeto();
    }

    @Override
    public String getNomeDoObjetoPlural() {
        return acaoOrigem.getNomeDoObjetoPlural();
    }

    @Override
    public void adicionarItemNaLista(String nomeDaLista) {
        acaoOrigem.adicionarItemNaLista(nomeDaLista);
    }

    @Override
    public void setNome(String pNome) {
        acaoOrigem.setNome(pNome);
    }

    @Override
    public List<ItfCampoInstanciado> getCamposInstaciadosInvalidos() {
        return acaoOrigem.getCamposInstaciadosInvalidos();
    }

    @Override
    public ItfCampoInstanciado getCampoByNomeOuAnotacao(String pNome) {
        return acaoOrigem.getCampoByNomeOuAnotacao(pNome);
    }

    @Override
    public ItfCampoInstanciado getCampoByCaminhoCampo(ItfCaminhoCampo pNome) {
        return acaoOrigem.getCampoByCaminhoCampo(pNome);
    }

    @Override
    public Object getValorCampoByCaminhoCampo(ItfCaminhoCampo pNome) {
        return acaoOrigem.getValorCampoByCaminhoCampo(pNome);
    }

    @Override
    public List<ItfCaminhoCampo> getEntidadesVinculadas() {
        return acaoOrigem.getEntidadesVinculadas();
    }

    @Override
    public ItfBeanSimples getBeanSimplesPorNomeCampo(String pNomeCampo) {
        return acaoOrigem.getBeanSimplesPorNomeCampo(pNomeCampo);
    }

    @Override
    public ItfBeanSimples getItemPorCaminhoCampo(ItfCaminhoCampo pCaminho) {
        return acaoOrigem.getItemPorCaminhoCampo(pCaminho);
    }

    @Override
    public List<ItfBeanSimples> getListaPorCaminhoCampo(ItfCaminhoCampo pCaminho) {
        return acaoOrigem.getListaPorCaminhoCampo(pCaminho);
    }

    @Override
    public List<ItfCaminhoCampoInvalido> getCamposInvalidos() {
        return acaoOrigem.getCamposInvalidos();
    }

    @Override
    public Field getCampoReflexaoByAnotacao(FabTipoAtributoObjeto pInfoCampo) {
        return acaoOrigem.getCampoReflexaoByAnotacao(pInfoCampo);
    }

    @Override
    public String getNomeCampo(FabTipoAtributoObjeto pInfocampo) {
        return acaoOrigem.getNomeCampo(pInfocampo);
    }

    public ItfAcaoDoSistema getAcaoOrigem() {
        return acaoOrigem;
    }

    public String getNomeInicialAcao() {
        return nomeInicialAcao;
    }

    @Override
    public boolean uploadFotoTamanhoGrande(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTodosOsFormatos(this, pStream);
    }

    @Override
    public boolean uploadFotoTamanhoMedio(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTamanhoMedio(this, pStream);
    }

    @Override
    public boolean uploadFotoTamanhoPequeno(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTamanhoPequeno(this, pStream);
    }

    @Override
    public boolean uploadFotoTodosFormatos(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTodosOsFormatos(this, pStream);
    }

    @Override
    public boolean uploadArquivoDeEntidade(ItfCampoInstanciado prcampo, byte[] pStream, String pNomeArquivo) {
        return SBCore.getCentralDeArquivos().salvarArquivo(prcampo, pStream, pNomeArquivo);
    }

    @Override
    public void adicionarJustificativaExecucaoAcao(ItfAcaoDoSistema pAcao, String pJustificativa) {
        acaoOrigem.adicionarJustificativaExecucaoAcao(pAcao, pJustificativa);
    }

    @Override
    public String getJustificativa(ItfAcaoDoSistema pAcao) {
        return acaoOrigem.getJustificativa(pAcao);
    }

    @Override
    public void prepararNovoObjeto(Object... parametros) {
        try {
            acaoOrigem.prepararNovoObjeto();
        } catch (ErroPreparandoObjeto t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando ação extendida", t);
        }
    }

    @Override
    public String getSlugIdentificador() {
        return acaoOrigem.getSlugIdentificador();
    }

    @Override
    public boolean isAcaoTemModal() {
        return false;
    }

    @Override
    public String getIcone() {
        return getIconeAcao();
    }

    @Override
    public List<ItfCampoInstanciado> getCamposInstanciados() {
        return acaoOrigem.getCamposInstanciados();
    }

    @Override
    public boolean isTemCampoAnotado(FabTipoAtributoObjeto pCampo) {
        return acaoOrigem.isTemCampoAnotado(pCampo);
    }

    @Override
    public void adicionarSubItem(String pNomeCampo) {
        acaoOrigem.adicionarItemNaLista(pNomeCampo);
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciadoByNomeOuAnotacao(String pNome) {
        return acaoOrigem.getCampoInstanciadoByNomeOuAnotacao(pNome);
    }

    @Override
    public void adicionarAssitenteLocalizacao(ItfAssistenteDeLocalizacao pLocalizacao) {

    }

    @Override
    public ItfAssistenteDeLocalizacao getAssistenteLocalizacao(ItfCampoInstanciado pCampoInst, TipoOrganizacaoDadosEndereco pTipo) {
        return acaoOrigem.getAssistenteLocalizacao(pCampoInst, pTipo);
    }

    @Override
    public void copiaDados(Object pObjetoReferencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto pTipocampo) {
        return acaoOrigem.getCampoInstanciadoByAnotacao(pTipocampo);
    }

    @Override
    public boolean isTemImagemPequenaAdicionada() {
        return false;
    }

    @Override
    public String getXhtmlVisaoMobile() {
        return MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(this.getClass());
    }

    @Override
    public String getXhtmlVisao(int numeroColunas) {
        return MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(this.getClass());
    }

}

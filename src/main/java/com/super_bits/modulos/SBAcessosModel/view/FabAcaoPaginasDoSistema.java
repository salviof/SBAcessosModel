/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.view;

import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulos.SBAcessosModel.model.tokens.TokenAcesso;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;

/**
 *
 *
 *
 *
 *
 *
 *
 * @author Sálvio Furbino
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.PAGINAS_DO_SISTEMA)
public enum FabAcaoPaginasDoSistema implements ItfFabricaAcoes {

    @InfoTipoAcaoGestaoEntidade(xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_HOME, icone = "fa fa-heart-o", precisaPermissao = false)
    PAGINA_NATIVA_HOME_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-lock", precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_ACESSO_NEGADO)
    PAGINA_NATIVA_ACESSO_NEGADO_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-lock",
            precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_PARAMETRO_URL_INVALIDO)
    PAGINA_NATIVA_PARAMETRO_NAO_ENCONTRADO_MB,
    @InfoTipoAcaoFormulario(icone = "fa fa-lock", precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_ACESSO_NEGADO_SUB_FORM)
    PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.REG_ATUALIZAR, precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_SESSAO_EXPIROU)
    PAGINA_NATIVA_VIEW_EXPIROU_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_LOGIN)
    PAGINA_NATIVA_LOGIN_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_RECUPERACAO_DE_SENHA, entidade = TokenAcesso.class)
    PAGINA_NATIVA_RECUPERACAO_SENHA_MB,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_ACESSO_DINAMICO_VIA_TOKEN, entidade = TokenAcessoDinamico.class)
    PAGINA_NATIVA_TOKEN_DINAMICO_MB,
    @InfoTipoAcaoFormulario(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_RECUPERACAO_DE_SENHA_GERAR_SENHA)
    PAGINA_NATIVA_RECUPERACAO_SENHA_FRM_GERAR_NOVA_SENHA,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_ERRO_CRITICO)
    PAGINA_NATIVA_ERRO_CRITICO_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.REG_PESQUISA_AVANCADA, precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_PESQUISA_ITEM_AVANCADO)
    PAGINA_PESQUISA_AVANCADA_MB_PADRAO,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.REG_PESQUISA_AVANCADA, precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_JUSTIFICATIVA)
    PAGINA_NATIVA_MODAL_JUSTIFICATIVA_MB,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.REG_PESQUISA_AVANCADA, precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_COMUNICACAO)
    PAGINA_NATIVA_MODAL_COMUNICACAO_GENERICA_MB,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.REG_PESQUISA_AVANCADA, precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_MODAL_COMUNICACAO_ACAO_TRANSIENTE)
    PAGINA_NATIVA_MODAL_COMUNICACAO_ACAO_TRANSIENT_MB,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_CARTAO_DE_ACESSO, precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_OAUTH_LOGIN)
    PAGINA_NATIVA_LOGIN_OAUTH_MB,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM, precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_API_RESTFUL_CONTROLLER)
    PAGINA_NATIVA_RESTFUL_RESP_MB_GESTAO,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM, precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_API_JSON_WEB_VIEW)
    PAGINA_NATIVA_JSON_WEBVIEW_MB_GESTAO,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM, precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_API_JSON_WEB_VIEW)
    PAGINA_NATIVA_JSON_WEBCONTROLLER_MB_GESTAO,
    @InfoTipoAcaoController(icone = "fa fa-laptop", nomeAcao = "registrar")
    PAGINA_NATIVA_JSON_WEBVIEW_CTR_REGISTRAR_DISPOSITIVO,
    @InfoTipoAcaoGestaoEntidade(precisaPermissao = false, xhtmlDaAcao = FabAcaoPaginasDoSistema.FORMULARIO_API_JSON_WEB_VIEW, icone = "fa fa-link")
    PAGINA_NATIVA_LINK_RAPIDO_MB_GESTAO;

    ;
    public final static String FORMULARIO_API_JSON_WEB_VIEW = "/resources/json-webview/jsonwebview.xhtml";
    public final static String FORMULARIO_HOME = "/site/home.xhtml";
    public final static String FORMULARIO_PARAMETRO_URL_INVALIDO = "/resources/SBComp/SBSystemPages/parametroURLInvalido.xhtml";
    public final static String FORMULARIO_MODAL_COMUNICACAO_ACAO_TRANSIENTE = "/resources/SBComp/modal/dialogo/comunicacaoAcaoTransiente.xhtml";
    public final static String FORMULARIO_MODAL_JUSTIFICATIVA = "/resources/SBComp/modal/dialogo/justificativa.xhtml";
    public final static String FORMULARIO_MODAL_COMUNICACAO = "/resources/SBComp/modal/dialogo/respostaComunicacao.xhtml";

    /**
     * opções Utilitárias do desenvolvedor
     */
    public final static String FORMULARIO_MODAL_UTIL_DESENVOLVEDOR = "/resources/SBComp/modal/admin/adminUtil";

    /**
     * Informações da pagina Atual
     */
    public final static String FORMULARIO_MODAL_INFO_PAGINA = "/resources/SBComp/modal/admin/infoPagina";

    public final static String FORMULARIO_MODAL_PESQUISA_ITEM_AVANCADO = "/resources/SBComp/modal/pesquisaItemAvancada.xhtml";

    public final static String FORMULARIO_MODAL_REQUISITO = "/resources/SBComp/modal/admin/requisitarAlteracao.xhtml";

    /**
     * Atenção: Caminho do arquivo sem extenção, o Framework de Dialogo do
     * primefaces não gosta de extenção
     */
    public final static String FORMULARIO_MODAL_ENVIO_ARQUIVO = "/resources/SBComp/modal/envioArquivo";
    public final static String FORMULARIO_ACESSO_NEGADO = "/resources/SBComp/SBSystemPages/acessoNegado.xhtml";

    public final static String FORMULARIO_OAUTH_LOGIN = "/resources/oauth/login.xhtml";

    public final static String FORMULARIO_API_RESTFUL_CONTROLLER = "/resources/restful/respostaController.xhtml";

    public final static String FORMULARIO_ACESSO_NEGADO_SUB_FORM = "/resources/SBComp/SBSystemPages/acessoNegadoSubForm.xhtml";

    public final static String FORMULARIO_RECUPERACAO_DE_SENHA = "/resources/SBComp/SBSystemPages/recSenhaGestao.xhtml";
    public final static String FORMULARIO_ACESSO_DINAMICO_VIA_TOKEN = "/resources/SBComp/SBSystemPages/acessoTokenExclusivoDinamico.xhtml";
    public final static String FORMULARIO_RECUPERACAO_DE_SENHA_GERAR_SENHA = "/resources/SBComp/SBSystemPages/recSenhaGerar.xhtml";
    public final static String FORMULARIO_LOGIN = "/resources/SBComp/SBSystemPages/login.xhtml";
    public final static String FORMULARIO_SESSAO_EXPIROU = "/resources/SBComp/SBSystemPages/viewExpirou.xhtml";
    public final static String FORMULARIO_ERRO_CRITICO = "/resources/SBComp/SBSystemPages/erroGenerico.xhtml";

    public final static String FORMULARIO_API_JSON_WEB_CONTROLLER = "/resources/json-webview/jsonwebviewController.xhtml";

    @Override
    public Class getEntidadeDominio() {
        return AcaoDoSistema.class;

    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

    @Override
    public ItfAcaoDoSistema getRegistro() {
        ItfAcaoDoSistema acao = (ItfAcaoDoSistema) UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
        return acao;
    }

}

package br.org.coletivoJava.fw.projetos.fw.api.model.acaocontroller;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoController;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AcaoController.class)
public enum CPAcaoController {
	_IDMETODO, _TEMLOGEXECUCAO, _PRECISACOMUNICACAO, _PRECISAJUSTIFICATIVA, _ACAOTEMMODAL, _TEXTOCOMUNICACAOPERSONALIZADO, _CAMPOJUSTIFICATIVA, _XHTMLMODALVINCULADO, _TIPOCOMUNICACAO, _PARAMETROS;

	public static final String idmetodo = "idMetodo";
	public static final String temlogexecucao = "temLogExecucao";
	public static final String precisacomunicacao = "precisaComunicacao";
	public static final String precisajustificativa = "precisaJustificativa";
	public static final String acaotemmodal = "acaoTemModal";
	public static final String textocomunicacaopersonalizado = "textoComunicacaoPersonalizado";
	public static final String campojustificativa = "campoJustificativa";
	public static final String xhtmlmodalvinculado = "xhtmlModalVinculado";
	public static final String tipocomunicacao = "tipoComunicacao";
	public static final String parametros = "parametros";
}
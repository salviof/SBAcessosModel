package br.org.coletivoJava.fw.projetos.fw.api.model.tokenacessodinamico;

import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TokenAcessoDinamico.class)
public enum CPTokenAcessoDinamico {
	_SLUGACAOFORMULARIO, _CODIGOENTIDADE, _NOMEENTIDADEDOACESSO;

	public static final String slugacaoformulario = "slugAcaoFormulario";
	public static final String codigoentidade = "codigoEntidade";
	public static final String nomeentidadedoacesso = "nomeEntidadeDoAcesso";
}
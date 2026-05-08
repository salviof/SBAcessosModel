package br.org.coletivoJava.fw.projetos.fw.api.model.tokenacesso;

import com.super_bits.modulos.SBAcessosModel.model.tokens.TokenAcesso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TokenAcesso.class)
public enum CPTokenAcesso {
	_ID, _CODIGO, _EMAIL, _VALIDADE, _DATAHORACRIACAO;

	public static final String id = "id";
	public static final String codigo = "codigo";
	public static final String email = "email";
	public static final String validade = "validade";
	public static final String datahoracriacao = "dataHoraCriacao";
}
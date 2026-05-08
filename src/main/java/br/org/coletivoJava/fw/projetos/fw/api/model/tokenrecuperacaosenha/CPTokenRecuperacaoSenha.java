package br.org.coletivoJava.fw.projetos.fw.api.model.tokenrecuperacaosenha;

import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenRecuperacaoDeSenha.TokenRecuperacaoSenha;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TokenRecuperacaoSenha.class)
public enum CPTokenRecuperacaoSenha {
	_EMAILUSUARIO;

	public static final String emailusuario = "emailUsuario";
}
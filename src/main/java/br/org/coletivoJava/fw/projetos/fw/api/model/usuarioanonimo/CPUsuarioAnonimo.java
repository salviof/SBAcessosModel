package br.org.coletivoJava.fw.projetos.fw.api.model.usuarioanonimo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = UsuarioAnonimo.class)
public enum CPUsuarioAnonimo {
	_ID, _NOME, _DATAHORAALTERACAO, _DATAHORAINSERSAO, _USUARIOINSERCAO, _USUARIOALTERACAO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String datahoraalteracao = "dataHoraAlteracao";
	public static final String datahorainsersao = "dataHoraInsersao";
	public static final String usuarioinsercao = "usuarioInsercao";
	public static final String usuarioalteracao = "usuarioAlteracao";
}
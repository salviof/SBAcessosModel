package br.org.coletivoJava.fw.projetos.fw.api.model.logregradenegocio;

import com.super_bits.modulos.SBAcessosModel.model.logsRegraDeNegocio.LogRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = LogRegraDeNegocio.class)
public enum CPLogRegraDeNegocio {
	_ID, _NOMECLASSE, _USUARIORESPONSAVEL, _DESCRITIVO;

	public static final String id = "id";
	public static final String nomeclasse = "nomeClasse";
	public static final String usuarioresponsavel = "usuarioResponsavel";
	public static final String descritivo = "descritivo";
}
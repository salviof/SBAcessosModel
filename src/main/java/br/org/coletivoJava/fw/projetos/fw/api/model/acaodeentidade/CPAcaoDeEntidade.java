package br.org.coletivoJava.fw.projetos.fw.api.model.acaodeentidade;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AcaoDeEntidade.class)
public enum CPAcaoDeEntidade {
	_CLASSERELACIONADA;

	public static final String classerelacionada = "classeRelacionada";
}
package br.org.coletivoJava.fw.projetos.fw.api.model.assuntofaleconosco;

import com.super_bits.modulos.SBAcessosModel.model.FaleConosco.AssuntoFaleConosco;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AssuntoFaleConosco.class)
public enum CPAssuntoFaleConosco {
	_ID, _ATIVO, _ASSUNTO, _DATAINCLUSAO, _DATAALTERACAO, _DESTINATARIO, _TEMPORESPOSTA, _TIPOASSUNTO;

	public static final String id = "id";
	public static final String ativo = "ativo";
	public static final String assunto = "assunto";
	public static final String datainclusao = "dataInclusao";
	public static final String dataalteracao = "dataAlteracao";
	public static final String destinatario = "destinatario";
	public static final String temporesposta = "tempoResposta";
	public static final String tipoassunto = "tipoAssunto";
}
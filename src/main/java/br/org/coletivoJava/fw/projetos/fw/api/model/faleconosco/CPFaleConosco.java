package br.org.coletivoJava.fw.projetos.fw.api.model.faleconosco;

import com.super_bits.modulos.SBAcessosModel.model.FaleConosco.FaleConosco;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = FaleConosco.class)
public enum CPFaleConosco {
	_ID, _DATAINCLUSAO, _DATARESPOSTA, _REMETENTE, _TIPOFALECONOSCO, _EMAIL, _EMAILDESTINATARIO, _ASSUNTO, _MENSAGEM, _ATIVO, _DATAALTERACAO, _USUARIOINSERCAO;

	public static final String id = "id";
	public static final String datainclusao = "dataInclusao";
	public static final String dataresposta = "dataResposta";
	public static final String remetente = "remetente";
	public static final String tipofaleconosco = "tipoFaleConosco";
	public static final String email = "email";
	public static final String emaildestinatario = "emailDestinatario";
	public static final String assunto = "assunto";
	public static final String mensagem = "mensagem";
	public static final String ativo = "ativo";
	public static final String dataalteracao = "dataAlteracao";
	public static final String usuarioinsercao = "usuarioInsercao";
}
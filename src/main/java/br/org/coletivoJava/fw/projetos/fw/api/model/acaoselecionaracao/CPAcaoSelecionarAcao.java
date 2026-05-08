package br.org.coletivoJava.fw.projetos.fw.api.model.acaoselecionaracao;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoSelecionarAcao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AcaoSelecionarAcao.class)
public enum CPAcaoSelecionarAcao {
	_NOMEUNICOACOESDISPONIVEIS, _ACOES;

	public static final String nomeunicoacoesdisponiveis = "nomeUnicoAcoesDisponiveis";
	public static final String acoes = "acoes";
}
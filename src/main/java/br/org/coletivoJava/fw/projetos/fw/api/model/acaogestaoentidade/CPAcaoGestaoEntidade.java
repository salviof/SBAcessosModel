package br.org.coletivoJava.fw.projetos.fw.api.model.acaogestaoentidade;

import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AcaoGestaoEntidade.class)
public enum CPAcaoGestaoEntidade {
	_UTILIZARMESMOFORMEDITARINSERIR, _ENUMACOESVINCULADAS;

	public static final String utilizarmesmoformeditarinserir = "utilizarMesmoFormEditarInserir";
	public static final String enumacoesvinculadas = "enumAcoesVinculadas";
}
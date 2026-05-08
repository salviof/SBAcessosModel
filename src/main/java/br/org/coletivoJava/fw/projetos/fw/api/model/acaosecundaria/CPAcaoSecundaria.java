package br.org.coletivoJava.fw.projetos.fw.api.model.acaosecundaria;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AcaoSecundaria.class)
public enum CPAcaoSecundaria {
	_ACAOPRINCIPAL;

	public static final String acaoprincipal = "acaoPrincipal";
}
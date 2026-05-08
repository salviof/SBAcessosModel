package br.org.coletivoJava.fw.projetos.fw.api.model.acaoformulario;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AcaoFormulario.class)
public enum CPAcaoFormulario {
	_XHTML, _ACAOEXECTARFORMULARIO, _CAMPOS, _GRUPOS, _ESTADOFORMULARIO;

	public static final String xhtml = "xhtml";
	public static final String acaoexectarformulario = "acaoExectarFormulario";
	public static final String campos = "campos";
	public static final String grupos = "grupos";
	public static final String estadoformulario = "estadoFormulario";
}
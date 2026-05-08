package br.org.coletivoJava.fw.projetos.fw.api.model.acaodeentidadecontroller;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDeEntidadeController;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AcaoDeEntidadeController.class)
public enum CPAcaoDeEntidadeController {
	_ACAOPRINCIPAL, _ACAOCONTROLLERVINCULADO;

	public static final String acaoprincipal = "acaoPrincipal";
	public static final String acaocontrollervinculado = "acaoControllerVinculado";
}
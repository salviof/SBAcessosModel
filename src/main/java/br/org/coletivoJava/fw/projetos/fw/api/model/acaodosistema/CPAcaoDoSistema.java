package br.org.coletivoJava.fw.projetos.fw.api.model.acaodosistema;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AcaoDoSistema.class)
public enum CPAcaoDoSistema {
	_TIPOACAO, _TIPOACAOGENERICA, _ID, _NOMEACAO, _ICONEACAO, _COR, _DESCRICAO, _PRECISAPERMISSAO, _MODULO, _IDDESCRITIVOJIRA, _TIPOACAODB, _NOMEUNICO, _DIRETORIOBASEARQUIVOS, _NOMESLUGDOMINIO, _PARAMETROTELA, _ENUMACAO, _FABRICASVALORESTATICO, _PARAMETROESTATICOIDENTIFICADORESUNICOS;

	public static final String tipoacao = "tipoAcao";
	public static final String tipoacaogenerica = "tipoAcaoGenerica";
	public static final String id = "id";
	public static final String nomeacao = "nomeAcao";
	public static final String iconeacao = "iconeAcao";
	public static final String cor = "cor";
	public static final String descricao = "descricao";
	public static final String precisapermissao = "precisaPermissao";
	public static final String modulo = "modulo";
	public static final String iddescritivojira = "idDescritivoJira";
	public static final String tipoacaodb = "tipoAcaoDB";
	public static final String nomeunico = "nomeUnico";
	public static final String diretoriobasearquivos = "diretorioBaseArquivos";
	public static final String nomeslugdominio = "nomeSlugDominio";
	public static final String parametrotela = "parametroTela";
	public static final String enumacao = "enumAcao";
	public static final String fabricasvalorestatico = "fabricasValorEstatico";
	public static final String parametroestaticoidentificadoresunicos = "parametroEstaticoIdentificadoresUnicos";
}
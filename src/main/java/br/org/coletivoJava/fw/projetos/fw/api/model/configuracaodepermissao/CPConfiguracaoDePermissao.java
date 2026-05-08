package br.org.coletivoJava.fw.projetos.fw.api.model.configuracaodepermissao;

import com.super_bits.modulos.SBAcessosModel.model.ConfiguracaoDePermissao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ConfiguracaoDePermissao.class)
public enum CPConfiguracaoDePermissao {
	_ID, _NOMECONFIG, _ULTIMAVERSAOBANCO, _PERMITIRCRIACAODEGRUPOS, _PERMITIRPERMISSAODEUSUARIO, _GRUPOUSUARIOPADRAO;

	public static final String id = "id";
	public static final String nomeconfig = "nomeConfig";
	public static final String ultimaversaobanco = "ultimaVersaoBanco";
	public static final String permitircriacaodegrupos = "permitirCriacaoDeGrupos";
	public static final String permitirpermissaodeusuario = "permitirPermissaoDeUsuario";
	public static final String grupousuariopadrao = "grupoUsuarioPadrao";
}
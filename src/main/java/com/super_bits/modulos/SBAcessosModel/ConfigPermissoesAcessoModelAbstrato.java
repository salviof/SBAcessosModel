package com.super_bits.modulos.SBAcessosModel;

import com.super_bits.modulos.SBAcessosModel.controller.UtilSBControllerAcessosModel;
import com.super_bits.modulos.SBAcessosModel.model.ConfiguracaoDePermissao;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenRecuperacaoDeSenha.TokenRecuperacaoSenha;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoConsultaComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreCriptrografia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreListasObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreObjetoSB;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;

import com.super_bits.modulosSB.SBCore.modulos.Controller.ConfigPermissaoSBCoreAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenRecuperacaoEmail;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/*

 */
/**
 *
 * @author desenvolvedor
 */
public abstract class ConfigPermissoesAcessoModelAbstrato extends ConfigPermissaoSBCoreAbstrato {

    private static boolean ACOES_DO_SISTEMA_CRIADAS = false;
    private static boolean PERMISSOES_CRIADAS = false;
    private final List<ItfFabricaAcoes> acoesPerisistidas = new ArrayList();

    public ConfigPermissoesAcessoModelAbstrato(Class[] pClassesControllers) {
        super(pClassesControllers);
    }

    /**
     *
     * Cria as ações do sistema no banco de dados
     *
     */
    protected void persistirAcoesNoBancoDeDados() {

        if (ACOES_DO_SISTEMA_CRIADAS || !houveAlteracoes()) {
            ACOES_DO_SISTEMA_CRIADAS = true;
            return;
        }
        try {

            List<ItfAcaoGerenciarEntidade> acoesGestao = MapaAcoesSistema.getListaTodasGestao();

            for (Iterator<ItfAcaoGerenciarEntidade> it = acoesGestao.iterator(); it.hasNext();) {
                ItfAcaoGerenciarEntidade acaoGestao = it.next();
                try {

                    if ((acaoGestao.isPossuiSubAcaoComPermissao() || acaoGestao.isPrecisaPermissao())) {
                        acaoGestao.getAcoesVinculadas().stream().filter(acao -> acao.isPrecisaPermissao()).forEach(subAcao
                                -> {
                            try {

                                if (!acoesPerisistidas.contains(subAcao.getEnumAcaoDoSistema())) {
                                    if (UtilSBPersistencia.mergeRegistro(subAcao) == null) {
                                        throw new UnsupportedOperationException("Erro salvando " + subAcao.getNomeUnico());
                                    } else {
                                        acoesPerisistidas.add(subAcao.getEnumAcaoDoSistema());

                                    }
                                }
                            } catch (Throwable t) {

                                throw new UnsupportedOperationException("Erro salvando " + subAcao.getNomeUnico());
                            }
                        }
                        );

                        if (UtilSBPersistencia.getRegistroByID(acaoGestao.getClass(), UtilSBController.gerarIDAcaoDoSistema(acaoGestao.getEnumAcaoDoSistema())) == null) {
                            if (!acoesPerisistidas.contains(acaoGestao.getEnumAcaoDoSistema())) {
//                        acao.getAcoesVinculadas().forEach(subAcao -> ent.merge(subAcao));
                                if (UtilSBPersistencia.mergeRegistro(acaoGestao) == null) {

                                    throw new UnsupportedOperationException("Erro Salvando ação >" + acaoGestao.getNomeUnico());
                                } else {
                                    System.out.println("Armazenou" + acaoGestao.getEnumAcaoDoSistema());
                                    acoesPerisistidas.add(acaoGestao.getEnumAcaoDoSistema());
                                }
                            }
                        }

                    }
                } catch (EntityNotFoundException t) {
                    acaoGestao.getAcoesVinculadas();
                    System.out.println(acaoGestao.getEnumAcaoDoSistema().toString());
                    String msg = t.getLocalizedMessage();
                    String msg2 = t.getMessage();
                    String nomeEntidadeNaoEncontrada = "Não detedminada";
                    try {
                        Long id = Long.valueOf(UtilSBCoreStringFiltros.getNumericosDaString(msg));

                        ItfAcaoDoSistema acaoEnt = MapaAcoesSistema.getAcaoDoSistemaById(id);
                        if (acaoEnt == null) {
                            id = id - id * 2;
                            acaoEnt = MapaAcoesSistema.getAcaoDoSistemaById(id);
                        }
                        if (acaoEnt != null) {
                            nomeEntidadeNaoEncontrada = acaoEnt.getEnumAcaoDoSistema().toString();
                            //contornoBugEntityNotFoundExceptionHibernate(acaoGestao, acaoEnt, 0);
                        }
                        throw new UnsupportedOperationException("Erro de EntityNotFoundException >" + acaoGestao.getNomeUnico() + " Entity não encontrada->" + acaoEnt.getNomeUnico());

                    } catch (Throwable tt) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Salvando ação >" + acaoGestao.getNomeUnico(), tt);
                    }
                    //         String msg3 = t.getCause().getMessage();
                    //throw new UnsupportedOperationException("Erro Salvando ação >" + acaoGestao.getNomeUnico() + " a ação vinculada:" + nomeEntidadeNaoEncontrada + "não foi encontrada");
                } catch (Throwable t) {
                    throw new UnsupportedOperationException("Erro Salvando ação >" + acaoGestao.getNomeUnico(), t);
                }
            }

            ACOES_DO_SISTEMA_CRIADAS = true;
        } catch (Throwable t) {
            ACOES_DO_SISTEMA_CRIADAS = false;
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro persistindo ações do Banco de dados", t);

        } finally {
            //UtilSBPersistencia.fecharEM(em);
        }

    }

    @Override
    public ItfUsuario getUsuarioByEmail(String pEmail) {
        ItfUsuario usuarioEncontrado;
        if (pEmail == null || !pEmail.contains("@")) {
            return null;
        }
        usuarioEncontrado = (ItfUsuario) new ExecucaoConsultaComGestaoEntityManager() {
            @Override
            public Object regraDeNegocioRetornandoResultado() {
                try {
                    if (pEmail.equals(new UsuarioSistemaRoot().getEmail())) {
                        return new UsuarioSistemaRoot();
                    }
                    UsuarioSB usuario
                            = (UsuarioSB) UtilSBPersistencia.getRegistroByJPQL("from UsuarioSB where email like'" + pEmail + "'", getEm());
                    if (usuario == null) {
                        return null;
                    }
                    usuario.getGrupo();
                    usuario.isAtivo();
                    return usuario;

                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro consultando usuário logado", t);
                    return null;
                }
            }
        }.getResultado();
        return usuarioEncontrado;

    }

    private boolean houveAlteracoes() {
        try {
            ConfiguracaoDePermissao configPermissao = null;
            if (SBCore.isEmModoDesenvolvimento()) {
                configPermissao = (ConfiguracaoDePermissao) UtilSBPersistencia.getRegistroByID(ConfiguracaoDePermissao.class, 0l);
                if (configPermissao == null) {
                    return true;
                }
                if (configPermissao.getUltimaVersaoBanco() == null) {
                    return true;
                }
                return !configPermissao.getUltimaVersaoBanco().equals(SBPersistencia.getDevOps().getHashBancoGerado());

            } else {

                return false;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro detectando se ouve alterações no banco", t);
            return true;
        }
    }

    protected boolean persistirPermissoesNoBanco() {
        try {

            if (!PERMISSOES_CRIADAS) {
                if (!houveAlteracoes()) {
                    PERMISSOES_CRIADAS = true;
                    return true;
                } else {

                    PERMISSOES_CRIADAS = UtilSBControllerAcessosModel.criarPermissoesDeAcao();
                    if (PERMISSOES_CRIADAS) {

                        ConfiguracaoDePermissao permissaoAtual = (ConfiguracaoDePermissao) UtilSBPersistencia.getRegistroByID(ConfiguracaoDePermissao.class, 0l);
                        if (permissaoAtual == null) {
                            permissaoAtual = new ConfiguracaoDePermissao();
                        }
                        if (permissaoAtual.getUltimaVersaoBanco() != null) {
                            if (!permissaoAtual.getUltimaVersaoBanco().equals(SBPersistencia.getDevOps().getHashBancoGerado())) {
                                permissaoAtual.setUltimaVersaoBanco(SBPersistencia.getDevOps().getHashBancoGerado());
                                if (UtilSBPersistencia.mergeRegistro(permissaoAtual) == null) {
                                    throw new UnsupportedOperationException("Erro Persistindo nova versão do banco em tabela de configuracao");
                                }
                            }
                        }

                    }

                }

            }

            return PERMISSOES_CRIADAS;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Atualizando informações de segurança", t);
            return false;
        }
    }

    /**
     *
     * Retorna uma lista atualizada com as permissões do sistema
     *
     *
     * @return
     */
    @Override
    public List<ItfPermissao> configuraPermissoes() {
        if (houveAlteracoes()) {

            persistirAcoesNoBancoDeDados();
            persistirPermissoesNoBanco();
        }
        List<ItfPermissao> resp = new ArrayList<>();
        EntityManager em = null;
        try {
            //Exemplo busca acessos no banco de dados
            em = UtilSBPersistencia.getNovoEM();
            resp = (List) UtilSBPersistencia.getListaTodos(PermissaoSB.class, em);

            return resp;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo lista de acessos", t);

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return resp;
    }

    @Override
    public List<ItfUsuario> configuraUsuarios() {

        List<ItfUsuario> resposta = (List) UtilSBPersistencia.getListaTodos(UsuarioSB.class
        );

        return resposta;
    }

    @Override
    @Deprecated
    public void atualizarInformacoesDePermissoesDoSistema() {
        if (houveAlteracoes()) {
            persistirAcoesNoBancoDeDados();
            persistirPermissoesNoBanco();
        }

    }

    public static List<AcaoDoSistema> listarAcoesDoGrupo(@NotNull GrupoUsuarioSB pGrpUsuario, @NotNull ModuloAcaoSistema pModulo) {
        List<AcaoDoSistema> resp
                = (List) new ExecucaoConsultaComGestaoEntityManager() {
                    @Override
                    public Object regraDeNegocioRetornandoResultado() {
                        List<AcaoDoSistema> resposta = new ArrayList<>();

                        for (ItfAcaoDoSistema acao : pModulo.getAcoes()) {
                            PermissaoSB permissao = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class,
                                    acao.getId(), getEm());
                            //TODO sobrescrever metodo permissao no modulo SBPErmissao utilizando loadBY
                            //   permissao = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class, permissao.getId(), em);
                            if (permissao != null) {
                                if (permissao.getGruposPermitidos().contains(pGrpUsuario)) {
                                    resposta.add((AcaoDoSistema) acao);
                                }
                            }

                        }
                        return resposta;
                    }
                }.getResultado();

        return resp;
    }

    @Override
    public boolean isAcaoPermitidaUsuario(ItfUsuario pUsuario, ItfAcaoDoSistema acao) {
        return isPermitidoUsuario(pUsuario, new PermissaoSB((AcaoDoSistema) acao));
    }

    @Override
    public boolean isAcaoPermitidaUsuarioLogado(ItfAcaoDoSistema acao) {
        return isPermitidoUsuario(SBCore.getUsuarioLogado(), new PermissaoSB((AcaoDoSistema) acao));
    }

    @Override
    public boolean isPermitidoUsuario(ItfUsuario pUsuario, ItfPermissao pPermissao) {
        EntityManager em = null;
        try {
            if (!PERMISSOES_CRIADAS) {
                configuraPermissoes();
            }
            System.out.println("PEsquisando lista de acesso negado");
            if (pUsuario.getEmail().equals(new UsuarioSistemaRoot().getEmail())) {
                return true;
            }
            if (!pPermissao.getAcao().isPrecisaPermissao()) {
                return true;
            }

            em = UtilSBPersistencia.getNovoEM();

            PermissaoSB pAcesso = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class,
                    pPermissao.getId(), em);
            if (pAcesso == null) {
                throw new UnsupportedOperationException("Nenhuma permissao foi encontrada para" + pPermissao);
            }

            for (ItfUsuario usuario : pAcesso.getUsuariosNegados()) {
                if (usuario.getId() == pUsuario.getId()) {
                    System.out.println("Acesso negado de:" + pUsuario.getNome() + "registro usuario encontrado:" + usuario.getId());
                    return false;
                }
            }
            for (ItfGrupoUsuario grupo : pAcesso.getGruposNegados()) {

                for (ItfUsuario user : grupo.getUsuarios()) {
                    if (pUsuario.getGrupo().getId() == pUsuario.getGrupo().getId()) {
                        return false;
                    }
                    if (user.getId() == pUsuario.getId()) {
                        return false;
                    }
                }
            }

            System.out.println("Listando permitidos");
            for (ItfUsuario usuario : pAcesso.getUsuariosPermitidos()) {
                System.out.println("permitido:" + usuario.getNome() + pAcesso.getAcao());
                if (usuario.getId() == pUsuario.getId()) {
                    return true;
                }
            }

            for (ItfGrupoUsuario grupo : pAcesso.getGruposPermitidos()) {
                if (pUsuario.getGrupo().getId() == grupo.getId()) {
                    return true;
                }

                for (ItfUsuario user : grupo.getUsuarios()) {
                    if (user.getId() == pUsuario.getId()) {
                        return true;
                    }
                }
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro verificando permissao", t);
            return false;
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return false;
    }

    @Override
    public ItfTokenRecuperacaoEmail gerarTokenRecuperacaoDeSenha(ItfUsuario pUsuario, int pMinutosValidade) {
        TokenRecuperacaoSenha recuperacaoDeSenha = new TokenRecuperacaoSenha();
        recuperacaoDeSenha.setCodigo(UUID.randomUUID().toString().replace("-", "_"));
        recuperacaoDeSenha.setEmail(pUsuario.getEmail());
        recuperacaoDeSenha.setValidade(UtilSBCoreDataHora.incrementaMinutos(new Date(), pMinutosValidade));
        return UtilSBPersistencia.mergeRegistro(recuperacaoDeSenha);
    }

    @Override
    public ItfTokenAcessoDinamico gerarTokenDinamico(ItfFabricaAcoes pAcao, ItfBeanSimplesSomenteLeitura pItem, String pEmail) {

        EntityManager entityGerarToken = UtilSBPersistencia.getEntyManagerPadraoNovo();
        try {
            try {
                ConsultaDinamicaDeEntidade consultaToken
                        = new ConsultaDinamicaDeEntidade(TokenAcessoDinamico.class, entityGerarToken);

                consultaToken.addcondicaoCampoIgualA("slugAcaoFormulario", pAcao.getRegistro().getNomeUnico());
                consultaToken.addcondicaoCampoIgualA("codigoEntidade", String.valueOf(pItem.getId()));
                consultaToken.addcondicaoCampoIgualA("nomeEntidadeDoAcesso", UtilSBCoreReflexaoObjeto.getClassExtraindoProxy(pItem.getClass().getSimpleName()).getSimpleName());
                if (pEmail != null) {
                    consultaToken.addcondicaoCampoIgualA("email", pEmail);
                }
                List<TokenAcessoDinamico> tokens = consultaToken.resultadoRegistros();
                if (!tokens.isEmpty()) {
                    UtilSBCoreListasObjeto.ordernarPorCampoReverso(tokens, "dataHoraCriacao");
                    TokenAcessoDinamico ultimoToken = tokens.get(0);
                    Date agora = new Date();
                    Date validade = ultimoToken.getValidade();
                    if (validade.getTime() > agora.getTime()) {
                        return ultimoToken;
                    }
                }
                TokenAcessoDinamico token = new TokenAcessoDinamico();
                token.setSlugAcaoFormulario(pAcao.getRegistro().getNomeUnico());
                token.setCodigoEntidade(String.valueOf(pItem.getId()));
                token.setCodigo(UUID.randomUUID().toString().replace("-", "_"));
                token.setDataHoraCriacao(new Date());
                token.setEmail(pEmail);
                token.setValidade(UtilSBCoreDataHora.incrementaDias(new Date(), 4));
                token.setEntidadeDoAcesso(MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pItem.getClass().getSimpleName()).getSimpleName());
                token = UtilSBPersistencia.mergeRegistro(token);
                return token;
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro registrando token dinamico" + pAcao + " " + pItem + " " + pEmail + " " + t.getMessage(), t);
            }

        } finally {
            UtilSBPersistencia.fecharEM(entityGerarToken);
        }
        return null;
    }

    @Override
    public boolean isTokenDinamicoExiste(ItfFabricaAcoes pAcao, ItfBeanSimplesSomenteLeitura pItem, String pEmail) {
        EntityManager entityGerarToken = UtilSBPersistencia.getEntyManagerPadraoNovo();
        try {
            try {
                ConsultaDinamicaDeEntidade consultaToken
                        = new ConsultaDinamicaDeEntidade(TokenAcessoDinamico.class, entityGerarToken);

                consultaToken.addcondicaoCampoIgualA("slugAcaoFormulario", pAcao.getRegistro().getNomeUnico());
                consultaToken.addcondicaoCampoIgualA("codigoEntidade", String.valueOf(pItem.getId()));

                List<TokenAcessoDinamico> tokens = consultaToken.resultadoRegistros();
                if (!tokens.isEmpty()) {
                    UtilSBCoreListasObjeto.ordernarPorCampoReverso(tokens, "dataHoraCriacao");
                    TokenAcessoDinamico ultimoToken = tokens.get(0);
                    Date agora = new Date();
                    Date validade = ultimoToken.getValidade();
                    if (validade.getTime() > agora.getTime()) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro registrando token dinamico" + pAcao + " " + pItem + " " + pEmail + " " + t.getMessage(), t);
            }

        } finally {
            UtilSBPersistencia.fecharEM(entityGerarToken);
        }
        return false;

    }

    @Override
    public void persitirMergePermissoes() {
        persistirPermissoesNoBanco();
    }

    @Override
    public boolean isObjetoPermitidoUsuario(ItfUsuario pUsuario, ItfBeanSimplesSomenteLeitura pObjeto) {
        return true;
    }

}

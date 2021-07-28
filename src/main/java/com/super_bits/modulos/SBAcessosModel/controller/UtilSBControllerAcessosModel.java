/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.modulos.SBAcessosModel.model.ConfiguracaoDePermissao;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulos.SBAcessosModel.model.Permitido_Grupos;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.hibernate.CacheMode;
import org.hibernate.Session;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBControllerAcessosModel {

    public static boolean criarPermissoesDeAcao() {
        return criarPermissoesDeAcao(true);
    }

    public static boolean criarPermissoesDeAcao(boolean recriarPermissaoExistente) {
        EntityManager em = null;

        try {
            em = UtilSBPersistencia.getEntyManagerPadraoNovo();

            List<Integer> permissoesPersistidas;
            permissoesPersistidas = new ArrayList<>();
            if (!recriarPermissaoExistente) {

                List<PermissaoSB> permissoesExistente = UtilSBPersistencia.getListaTodos(PermissaoSB.class, em);
                permissoesExistente.stream().map(pm -> pm.getId()).forEach(permissoesPersistidas::add);

            }

            Session sessaoSQLHibernate = em.unwrap(Session.class);
            sessaoSQLHibernate.clear();
            sessaoSQLHibernate.setCacheMode(CacheMode.REFRESH);
            sessaoSQLHibernate.setFlushMode(FlushModeType.COMMIT);
            if (!recriarPermissaoExistente) {
                UtilSBPersistencia.getListaTodos(PermissaoSB.class, em).stream().map(perm -> perm.getId()).forEach(permissoesPersistidas::add);
            }
            //Persistindo possíveis permissões de Gestão
            MapaAcoesSistema.getListaTodasGestao().stream().filter((acao)
                    -> (acao.isPrecisaPermissao() || acao.isPossuiSubAcaoComPermissao())).forEach((acao) -> {
                PermissaoSB novaPermissao = new PermissaoSB(acao.getEnumAcaoDoSistema());
                int idPemirssao = novaPermissao.getId();
                if (!permissoesPersistidas.contains(idPemirssao)) {
                    if (UtilSBPersistencia.mergeRegistro(novaPermissao) == null) {
                        throw new UnsupportedOperationException("Erro persistindo permissão em banco de dados");
                    }
                } else {
                    permissoesPersistidas.add(novaPermissao.getId());
                }
            });
            //Persistindo SubAções
            for (ItfAcaoDoSistema acao : MapaAcoesSistema.getListaTodasAcoes()) {
                AcaoDoSistema ac = (AcaoDoSistema) acao;
                if (ac.isPrecisaPermissao()) {
                    //Verificando se a permissao já existe
                    PermissaoSB novaPermissao = new PermissaoSB(ac);
                    int idPemirssao = novaPermissao.getId();

                    if (!permissoesPersistidas.contains(idPemirssao)) {
                        System.out.println("permissao" + idPemirssao + "não foi encontrada, executando merge");
                        try {
                            UtilSBPersistencia.iniciarTransacao(em);
                            // sessaoSQLHibernate.saveOrUpdate(novaPermissao);
                            if (UtilSBPersistencia.mergeRegistro(novaPermissao, em) == null) {
                                throw new UnsupportedOperationException("Erro persistindo permissão em banco de dados");
                            }
                            if (ac.isTemAcaoPrincipal()) {
                                if (UtilSBPersistencia.mergeRegistro(ac.getAcaoDeGestaoEntidade(), em) == null) {
                                    throw new UnsupportedOperationException("Erro persistindo permissão em banco de dados");
                                }
                                if (UtilSBPersistencia.mergeRegistro(ac, em) == null) {
                                    throw new UnsupportedOperationException("Erro persistindo permissão em banco de dados");
                                }
                            }
                            System.out.println(novaPermissao.getId());

                            permissoesPersistidas.add(novaPermissao.getId());
                            UtilSBPersistencia.finalizarTransacao(em);

                        } catch (Throwable t) {
                            boolean ignorarErro = false;
                            if (t.getCause() != null) {
                                if (t.getCause().getMessage().contains("ConstraintViolationException")) {
                                    ignorarErro = true;
                                }
                            }
                            if (ignorarErro) {
                                System.out.println("O Hibernate fez confusão e tentou executar um insert no lugar do merg");
                            } else {
                                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro atualizando persistencia de permissões", t);
                                return false;
                            }
                        }
                    }

                }
            }
            UtilSBPersistencia.iniciarTransacao(em);
            ConfiguracaoDePermissao config = new ConfiguracaoDePermissao();
            config.setUltimaVersaoBanco(SBPersistencia.getDevOps().getHashBancoGerado());
            UtilSBPersistencia.mergeRegistro(config, em);
            return UtilSBPersistencia.finalizarTransacao(em);

        } catch (Throwable t) {
            return false;
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

    }

    public static UsuarioSB getUsuarioLogado(EntityManager pEm) {

        int idUsuarioLogado = SBCore.getControleDeSessao().getSessaoAtual().getUsuario().getId();
        if (idUsuarioLogado == 0) {
            throw new UnsupportedOperationException("Não foi possível identficar o usuario");
        }
        UsuarioSB usuarioAlteracao = UtilSBPersistencia.getRegistroByID(UsuarioSB.class, idUsuarioLogado, pEm);
        if (usuarioAlteracao == null) {
            throw new UnsupportedOperationException("Não foi possível identficar o usuario");
        }
        return usuarioAlteracao;

    }

    public static void adicionarPermissao(GrupoUsuarioSB pGrupo, ItfAcaoDoSistema pAcao) {

        new ExecucaoComGestaoEntityManager() {
            @Override
            public void regraDeNegocio() {
                try {
                    Permitido_Grupos permissao = new Permitido_Grupos();
                    permissao.setAcesso(UtilSBPersistencia.getRegistroByID(PermissaoSB.class, UtilSBController.gerarIDAcaoDoSistema(pAcao.getEnumAcaoDoSistema()), getEm()));
                    GrupoUsuarioSB grupoAtualizado = UtilSBPersistencia.loadEntidade(pGrupo, getEm());
                    if (permissao.getAcesso() == null) {
                        throw new UnsupportedOperationException("O Acesso da ação não foi definido ação->" + pAcao);
                    }
                    permissao.setGrupo(grupoAtualizado);

                    if (permissao == null) {
                        throw new UnsupportedOperationException("Erro adionando permissao ao grupo");
                    }
                    if (!grupoAtualizado.getPermissoesConcedidas().contains(permissao)) {
                        grupoAtualizado.getPermissoesConcedidas().add(permissao);
                    }
                    atualizarEntidade(grupoAtualizado);
                } catch (ErroEmBancoDeDados pErro) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro atualizção permissao do grupo", pErro);
                    throw new UnsupportedOperationException("Erro adionando permissao ao grupo");
                }
            }
        };

    }
}

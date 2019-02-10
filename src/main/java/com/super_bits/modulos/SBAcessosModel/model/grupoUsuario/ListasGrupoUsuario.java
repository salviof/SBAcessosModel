/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.grupoUsuario;

import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulos.SBAcessosModel.model.Permitido_Grupos;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoConsultaComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ParametroLista;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.listas.InfoLista;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.listas.ItfListas;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;

import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public enum ListasGrupoUsuario implements ItfListas {

    @InfoLista(descricao = "", nomeParametro = "", parametros = {
        @ParametroLista(classe = ItfAcaoGerenciarEntidade.class, nomeParametro = "Gestão", valorPadrao = "")})
    PERMISSAO_POR_GESTAO,
    PERMISSOES_DO_GRUPO;

    @Override
    public List getLista(Object... entidade) {
        try {
            GrupoUsuarioSB pGrupo;
            pGrupo = (GrupoUsuarioSB) entidade[0];

            switch (this) {
                case PERMISSAO_POR_GESTAO:
                    List<PermissaoSB> permissoesEncontradas = new ArrayList<>();
                    ItfAcaoGerenciarEntidade acao = (ItfAcaoGerenciarEntidade) entidade[1];
                    for (Permitido_Grupos permissao : pGrupo.getPermissoesConcedidas()) {
                        if (permissao.getAcesso().getAcao().equals(acao)) {
                            permissoesEncontradas.add(permissao.getAcesso());
                        }
                    }
                    return permissoesEncontradas;
                case PERMISSOES_DO_GRUPO:
                    List<PermissaoSB> permissoesGrupo
                            = (List<PermissaoSB>) new ExecucaoConsultaComGestaoEntityManager() {
                                @Override
                                public Object regraDeNegocioRetornandoResultado() {
                                    List<PermissaoSB> permissoes = new ArrayList<>();

                                    List<Permitido_Grupos> listapermitidoGrupo = UtilSBPersistencia.
                                            getListaRegistrosByHQL("from Permitido_Grupos where grupo_id=?0", 0, getEm(), pGrupo.getId());
                                    listapermitidoGrupo.forEach(pg -> permissoes.add(pg.getAcesso()));
                                    return permissoes;
                                }
                            }.getResultado();

                    return permissoesGrupo;

                default:
                    throw new AssertionError(
                            this.name());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro listando dados do comprador", t);
        }
        return null;
    }

    @Override
    public Class
            getClasse() {
        return GrupoUsuarioSB.class;
    }

}

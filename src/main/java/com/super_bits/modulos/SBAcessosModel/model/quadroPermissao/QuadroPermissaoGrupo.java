/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.quadroPermissao;

import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoConsultaComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanComIcone;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SalvioF
 */
@InfoObjetoSB(tags = {"Quadro de Permissão do Grupo"}, descricao = "Exibe as informações de permissão do grupo", plural = "Permissoes do grupo")

public class QuadroPermissaoGrupo extends ItemSimples implements ItfBeanComIcone {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String descricao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    private String icone;
    private final List<ItfAcaoDoSistema> acoesDisponiveis;
    private final ItfAcaoGerenciarEntidade acaoGestao;
    private List<ItfAcaoDoSistema> acoesPermitidas;
    private GrupoUsuarioSB grupoUsuario;

    public QuadroPermissaoGrupo(GrupoUsuarioSB pGrupo, ItfAcaoGerenciarEntidade pAcaoGestao) {
        acoesDisponiveis = new ArrayList<>();
        acaoGestao = MapaAcoesSistema.getAcaoDoSistema(pAcaoGestao.getEnumAcaoDoSistema()).getComoGestaoEntidade();
        id = acaoGestao.getId();
        descricao = acaoGestao.getNomeAcao();
        acoesPermitidas = new ArrayList<>();
        icone = acaoGestao.getIcone();
        grupoUsuario = pGrupo;

        new ExecucaoConsultaComGestaoEntityManager() {
            @Override
            public Object regraDeNegocioRetornandoResultado() {
                List<PermissaoSB> permissoes
                        = UtilSBPersistencia.
                                getListaRegistrosByHQL("from PermissaoSB where idAcaoGestao= ?0 ",
                                        0, getEm(), UtilSBController.gerarIDAcaoDoSistema(acaoGestao.getEnumAcaoDoSistema()));

                permissoes.forEach((p) -> {
                    acoesDisponiveis.add(MapaAcoesSistema.getAcaoDoSistema(p.getAcao().getEnumAcaoDoSistema()));
                });

                GrupoUsuarioSB grupo = UtilSBPersistencia.loadEntidade(pGrupo, getEm());

                grupo.getAcessos().forEach((permissao) -> {
                    acoesPermitidas.add(MapaAcoesSistema.getAcaoDoSistema(permissao.getAcao().getEnumAcaoDoSistema()));
                });

                return acoesPermitidas;
            }
        };

    }

    @Override
    public String getIcone() {
        return icone;
    }

    @Override
    public boolean isTemImagemPequenaAdicionada() {
        return super.isTemImagemPequenaAdicionada(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getClassePontoIdentificador() {
        return super.getClassePontoIdentificador(); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ItfAcaoDoSistema> getAcoesDisponiveis() {
        return acoesDisponiveis;
    }

    public ItfAcaoGerenciarEntidade getAcaoGestao() {
        return acaoGestao;
    }

    public List<ItfAcaoDoSistema> getAcoesPermitidas() {
        return acoesPermitidas;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public void setAcoesPermitidas(List<ItfAcaoDoSistema> acoesPermitidas) {
        this.acoesPermitidas = acoesPermitidas;
    }

    public GrupoUsuarioSB getGrupoUsuario() {
        return grupoUsuario;
    }

}

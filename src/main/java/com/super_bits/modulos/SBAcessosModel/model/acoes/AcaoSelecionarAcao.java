/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormEntidadeSec;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSelecionarAcao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoSelecaoDeAcao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Ação para selação de ações"}, plural = "Ações de seleção")
public class AcaoSelecionarAcao extends AcaoFormEntidadeSec implements ItfAcaoSelecionarAcao {

    private String[] nomeUnicoAcoesDisponiveis;
    @Transient
    private List<ItfAcaoDoSistema> acoes;

    @Deprecated
    public AcaoSelecionarAcao() {
    }

    public AcaoSelecionarAcao(
            ItfAcaoGerenciarEntidade pAcaoPrincipal,
            ItfFabricaAcoes pFabAcao) {
        super(pAcaoPrincipal, pFabAcao, FabTipoAcaoSistemaGenerica.SELECAO_DE_ACAO);

    }

    public AcaoSelecionarAcao(List<ItfAcaoDoSistema> acoes) {
        super();

    }

    @Override
    public List<ItfAcaoDoSistema> getAcoes() {
        if (acoes == null) {
            if (MapaAcoesSistema.isMapaCriado()) {

                for (String strAcao : nomeUnicoAcoesDisponiveis) {
                    if (!strAcao.isEmpty()) {

                        ItfFabricaAcoes fabrica = SBCore.getFabricaByNOME_UNICO(strAcao);
                        if (fabrica != null) {
                            if (acoes == null) {
                                acoes = new ArrayList<>();
                            }

                            acoes.add(fabrica.getRegistro());
                        }
                    }
                }
            }
        }
        return acoes;
    }

    public void configurarAnotacao(InfoTipoAcaoSelecaoDeAcao pAnotacao) {
        nomeUnicoAcoesDisponiveis = pAnotacao.acoesDisponiveis();

    }

}

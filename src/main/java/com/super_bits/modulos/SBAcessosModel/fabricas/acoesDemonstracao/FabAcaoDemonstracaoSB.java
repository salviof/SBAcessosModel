/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.fabricas.acoesDemonstracao;

import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

/**
 *
 *
 *
 * @author desenvolvedor
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.ADMIN_TOOLS)
public enum FabAcaoDemonstracaoSB implements ItfFabricaAcoes {

    DEMONSTRACAO_MB_COMPONENTE,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-sitemap",
            xhtmlDaAcao = "/resources/SBComp/projeto/gerenciarRecursos.xhtml")
    DEMONSTRACAO_MB_RECURSOS,
    DEMONSTRACAO_MB_VALIDACAO,
    @InfoTipoAcaoGestaoEntidade(
            xhtmlDaAcao = "/resources/SBComp/debug/conformidadeInputCampo.xhtml",
            icone = "fa fa-pencil-square-o")
    TESTES_CAMPO_MB;

    @Override
    public ItfAcaoDoSistema getRegistro() {
        AcaoDoSistema acao = (AcaoDoSistema) UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
        switch (this) {

            case DEMONSTRACAO_MB_COMPONENTE:
                ItfAcaoGerenciarEntidade acaoDemoComponent = (ItfAcaoGerenciarEntidade) acao;
                acaoDemoComponent.setXhtml("/resources/SBComp/SBSystemPages/exemplos/demoSuperBitsFrameWork.xhtml");
                acao.setPrecisaPermissao(false);
                acao.setIconeAcao("fa fa-heart-o");

                break;
            case DEMONSTRACAO_MB_VALIDACAO:
                ItfAcaoGerenciarEntidade acaoDemoValidacao = (ItfAcaoGerenciarEntidade) acao;
                acaoDemoValidacao.setXhtml("/resources/SBComp/SBSystemPages/exemplosAdamantium/validacao.xhtml");
                acao.setIconeAcao("fa fa-heart-o");
                acao.setPrecisaPermissao(false);
                break;

        }

        return acao;
        // Dica: è possivel que este código pareça estranho para voce.
        // afinal estamos alterando a acaoDemoComponent, e retornando ação,
        // mas acredite, isso funciona,  devido a  um dos motivos do Java ser especial,
        // A passagem de valor no java é por referencia(Pequise sobre isso), exceto em tipos primitivos e String (A passagem por referencia é bem popular em  linguagens que valorizam a programação orientadas a objeto)
    }

    @Override
    public Class getEntidadeDominio() {

        return UsuarioSB.class;
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

}

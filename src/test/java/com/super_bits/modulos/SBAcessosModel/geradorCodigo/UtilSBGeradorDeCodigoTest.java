/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.geradorCodigo;

import com.super_bits.modulos.SBAcessosModel.TesteAcessosModelPadraoTest;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.junit.Test;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBGeradorDeCodigoTest extends TesteAcessosModelPadraoTest {

    List<ComoAcaoDoSistema> ACOES_PARA_TESTE = new ArrayList<>();
    List<EstruturaDeEntidade> LISTA_ESTRUTURA_DE_ENTIDADE = new ArrayList<>();

    EstruturaDeEntidade ESTRUTURA_DE_ENTIDADE1 = new EstruturaDeEntidade();
    EstruturaDeEntidade ESTRUTURA_DE_ENTIDADE2 = new EstruturaDeEntidade();
    EstruturaDeEntidade ESTRUTURA_DE_ENTIDADE3 = new EstruturaDeEntidade();

    public UtilSBGeradorDeCodigoTest() {
    }

    //@Test
    public void testMakeAnotacaoDaAcao() {
        try {
            System.out.println("Teste obtendo propriedades");
            for (ComoAcaoDoSistema acao : ACOES_PARA_TESTE) {

                // String anotacaoDaAcao = UtilSBGeradorDeCodigo.makeAnotacaoDaAcao(acao);
                System.out.println(acao.getNomeAcao());
                System.out.println(acao.getIconeAcao());
                if (acao.isUmaAcaoFormulario()) {
                    System.out.println(acao.getComoFormulario().getXhtml());
                }
//                SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Anotação gerada para " + acao.getNomeAcao() + "= \n " + anotacaoDaAcao);
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo anotação da ação", t);
        }
    }
    // @Test

    /*
    public void testMakeEnumFabricaDeAcoes() {
        try {
            System.out.println("Teste obtendo propriedades");
            for (ComoAcaoDoSistema acao : ACOES_PARA_TESTE) {

                System.out.println(acao.getNomeAcao());
                System.out.println(acao.getIconeAcao());
                ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_CTR_ALTERAR_STATUS.getAcaoDoSistema().getComoControllerEntidade());

                if (acao.isUmaAcaoFormulario()) {
                    System.out.println(acao.getComoFormulario().getXhtml());
                }
            }
            ItfModuloAcaoSistema modulo = FabAcaoSeguranca.GRUPO_MB_GERENCIAR.getAcaoDoSistema().getModulo();
            String classeEnumformada = UtilSBGeradorDeCodigo.makeEnumFabricaDeAcoes((List) ACOES_PARA_TESTE, modulo);
            SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Enum Gerado= \n " + classeEnumformada);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo anotação da ação", t);
        }
    }

     */
    //@Test
    public void testMakeClasseAnotacaoInfoAcao() {

    }

    //@Test
    public void testMakeEnumListas() {
        try {

            // String codigoGerado = UtilSBGeradorDeCodigoBase.makeEnumListas(ESTRUTURA_DE_ENTIDADE1);
            //   SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Classe gerada \n" + codigoGerado);
        } catch (Throwable t) {
            //  lancarErroJUnit(t);
        }
    }

    //@Test
    public void testMakeListasAnotacao() {

        try {
            //    String codigoGerado = UtilSBGeradorDeCodigo.makeListasAnotacao(ESTRUTURA_DE_ENTIDADE1);
            //     SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Classe gerada \n" + codigoGerado);

        } catch (Throwable t) {
            //    lancarErroJUnit(t);
        }
    }

    //@Test
    public void testMakeEnumCalculos() {
        try {
            //     String codigoGerado = UtilSBGeradorDeCodigo.makeListasAnotacao(ESTRUTURA_DE_ENTIDADE1);
            //     SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Classe gerada \n" + codigoGerado);
        } catch (Throwable t) {
            //     lancarErroJUnit(t);
        }

    }

    //@Test
    public void testMakeCalculoAnotacaos() {

        try {

            //      String codigoGerado = UtilSBGeradorDeCodigo.makeCalculoAnotacaos(ESTRUTURA_DE_ENTIDADE1);
            //      SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Classe gerada \n" + codigoGerado);
        } catch (Throwable t) {
            //      lancarErroJUnit(t);
        }

    }

    //@Test
    public void testMakeEntidade() {
        try {

            //     String codigoGerado = UtilSBGeradorDeCodigo.makeEntidade(ESTRUTURA_DE_ENTIDADE1);
            //      SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Classe gerada \n" + codigoGerado);
        } catch (Throwable t) {
            //     lancarErroJUnit(t);
        }

    }

    @Test
    public void testCriarArquivosDoSistema() {

        //    UtilSBGeradorDeCodigo.criarArquivosDoSistema(LISTA_ESTRUTURA_DE_ENTIDADE, ACOES_PARA_TESTE);
    }

}

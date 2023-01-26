/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import config.ConfiguradorCoreSBAcessosModelTestes;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormEntidadeSec;

import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author desenvolvedor
 */
public class UtilFabricaDeAcoesTest {

    @Before
    public void configAmbiente() {
        SBCore.configurar(new ConfiguradorCoreSBAcessosModelTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

    }

    @Test
    public void testGetAcaoPrincipalDoDominio() {

        ItfFabricaAcoes acaoRetronada = null;
        acaoRetronada = UtilFabricaDeAcoesAcessosModel.getAcaoPrincipalDoDominio(FabTEste.OBJETO_CTR_ALTERAR_STATUS);
        assertEquals("A ação managede bean do dominio Obejto não foi retornada corretamente", FabTEste.OBJETO_MB_GERENCIAR, acaoRetronada);
        acaoRetronada = UtilFabricaDeAcoesAcessosModel.getAcaoPrincipalDoDominio(FabTEste.OBJETO_FRM_NOVO);
        assertEquals("A ação managede bean do dominio Obejto não foi retornada corretamente", FabTEste.OBJETO_MB_GERENCIAR, acaoRetronada);
        acaoRetronada = UtilFabricaDeAcoesAcessosModel.getAcaoPrincipalDoDominio(FabTEste.OBJETO_MB_GERENCIAR);
        assertEquals("A ação managede bean do dominio Obejto não foi retornada corretamente", null, acaoRetronada);

        acaoRetronada = UtilFabricaDeAcoesAcessosModel.getAcaoPrincipalDoDominio(FabTEste.USUARIO_TESTE_FRM_EDITAR);
        assertEquals("A ação managede bean do dominio Obejto não foi retornada corretamente", FabTEste.USUARIO_TESTE_MB_GERENCIAR, acaoRetronada);
        acaoRetronada = UtilFabricaDeAcoesAcessosModel.getAcaoPrincipalDoDominio(FabTEste.USUARIO_TESTE_FRM_NOVO);
        assertEquals("A ação managede bean do dominio Obejto não foi retornada corretamente", FabTEste.USUARIO_TESTE_MB_GERENCIAR, acaoRetronada);
        acaoRetronada = UtilFabricaDeAcoesAcessosModel.getAcaoPrincipalDoDominio(FabTEste.USUARIO_TESTE_MB_GERENCIAR);
        assertEquals("A ação managede bean do dominio Obejto não foi retornada corretamente", null, acaoRetronada);

    }

    @Test
    public void testGetNovaAcao() {

        ItfAcaoDoSistema acaoGerada = (ItfAcaoDoSistema) FabTEste.OBJETO_CTR_ALTERAR_STATUS.getRegistro();

        assertNotNull("Gerou ação com Ação generica nula", acaoGerada.getTipoAcaoGenerica());
        assertEquals("O tipo generico não parece ser o tipo esperado pela nomeclatura", acaoGerada.getTipoAcaoGenerica(), FabTipoAcaoSistemaGenerica.CONTROLLER_ATIVAR_DESATIVAR);
        assertEquals(acaoGerada.getClass().getName(), AcaoDeEntidadeController.class.getName());

        acaoGerada = (ItfAcaoDoSistema) FabTEste.OBJETO_FRM_NOVO.getRegistro();

        assertEquals("O tipo generico não parece ser o tipo esperado pela nomeclatura", acaoGerada.getTipoAcaoGenerica(), FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO);
        assertEquals(acaoGerada.getClass().getName(), AcaoFormEntidadeSec.class.getName());

        acaoGerada = (ItfAcaoDoSistema) FabTEste.OBJETO_MB_GERENCIAR.getRegistro();

        FabTipoAcaoSistemaGenerica gerenciar = acaoGerada.getTipoAcaoGenerica();
        assertEquals("O tipo generico não parece ser o tipo esperado pela nomeclatura", acaoGerada.getTipoAcaoGenerica(), FabTipoAcaoSistemaGenerica.GERENCIAR_DOMINIO);
        assertEquals(acaoGerada.getClass().getName(), AcaoGestaoEntidade.class.getName());

        acaoGerada = (ItfAcaoDoSistema) FabTEste.USUARIO_TESTE_FRM_EDITAR.getRegistro();

        assertEquals("O tipo generico não parece ser o tipo esperado pela nomeclatura", acaoGerada.getTipoAcaoGenerica(), FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR);
        assertEquals(acaoGerada.getClass().getName(), AcaoFormEntidadeSec.class.getName());

        acaoGerada = (ItfAcaoDoSistema) FabTEste.USUARIO_TESTE_FRM_NOVO.getRegistro();

        assertEquals("O tipo generico não parece ser o tipo esperado pela nomeclatura", acaoGerada.getTipoAcaoGenerica(), FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO);
        assertEquals(acaoGerada.getClass().getName(), AcaoFormEntidadeSec.class.getName());

        acaoGerada = (ItfAcaoDoSistema) FabTEste.USUARIO_TESTE_MB_GERENCIAR.getRegistro();

        assertEquals("O tipo generico não parece ser o tipo esperado pela nomeclatura", acaoGerada.getTipoAcaoGenerica(), FabTipoAcaoSistemaGenerica.GERENCIAR_DOMINIO);
        assertEquals(acaoGerada.getClass().getName(), AcaoGestaoEntidade.class.getName());

    }

}

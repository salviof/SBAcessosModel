/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel;

import config.ConfiguradorCoreSBAcessosModelTestes;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 *
 * @author desenvolvedor
 */
public class TesteAcessosModelPadraoTest extends TesteJunitSBPersistencia {

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreSBAcessosModelTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

    }

    @Test
    public void teste() {
        List<ItfAcaoDoSistema> acoesDoSistema = MapaAcoesSistema.getListaTodasAcoes();

        for (FabTipoAcaoSistemaGenerica tipoAcao : FabTipoAcaoSistemaGenerica.values()) {

            Optional<ItfAcaoDoSistema> accaoDoTipo = acoesDoSistema.stream().filter(acao -> acao.getTipoAcaoGenerica().equals(tipoAcao)).findFirst();
            assertTrue("Tipo ação não encontrada para teste" + tipoAcao.name(), accaoDoTipo.isPresent());

        }

    }

}

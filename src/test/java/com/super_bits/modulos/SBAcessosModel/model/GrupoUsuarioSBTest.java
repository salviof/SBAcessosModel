/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import config.ConfiguradorCoreSBAcessosModelTestes;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import config.ConfigPersistenciaTestesAcesso;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 * @author desenvolvedor
 */
public class GrupoUsuarioSBTest extends TesteJunitSBPersistencia {

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreSBAcessosModelTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());

    }

    /*
    public GrupoUsuarioSBTest() {
    }
    private List<ModuloAcaoSistema> modulos;

    @Before
    public void setUp() {
    }



    private void configurarSelecaoDeAcoes(GrupoUsuarioSB grupoSelecionado) {
        for (ModuloAcaoSistema modulo : modulos) {
            modulo.getSelecaoAcoes().clear();
            for (AcaoDoSistema acao : ModuloSeguranca.listarAcoesDoGrupo(grupoSelecionado, modulo)) {
                modulo.getSelecaoAcoes().add(acao);
            }
        }
    }

    @Test
    public void testeEntityManager() {
        try {
            EntityManager em = getEMTeste();

            UtilSBAcessosModel.criarNovosAcessosNoBanco(em);
            ControllerAppAbstratoSBCore.reloadAcessos();
            List<GrupoUsuarioSB> listaGrupo = UtilSBPersistencia.getListaTodos(GrupoUsuarioSB.class, em);
            modulos = UtilSBPersistencia.getListaTodos(ModuloAcaoSistema.class, em);
            int tamanhoinicial = listaGrupo.size();
            GrupoUsuarioSB grupoNovo = new GrupoUsuarioSB();
            grupoNovo.setNome("wewdfsadf");
            grupoNovo.setDescricao("afasdfasdf");

            configurarSelecaoDeAcoes(grupoNovo);
            ModuloSeguranca.grupoDeUsuariosSalvarAlteracoes(grupoNovo, modulos, em);
            listaGrupo = UtilSBPersistencia.getListaTodos(GrupoUsuarioSB.class, em);
            int tamanhoFinal = listaGrupo.size();
            assertTrue("O novo registro não foi listado", tamanhoinicial < tamanhoFinal);
            grupoNovo = listaGrupo.get(0);

            grupoNovo.setNome("teste");
            ModuloSeguranca.grupoDeUsuariosSalvarAlteracoes(grupoNovo, modulos, em);
            listaGrupo = UtilSBPersistencia.getListaTodos(GrupoUsuarioSB.class, em);
            assertTrue("O nome do grupo não foi alterado", "teste".equals(grupoNovo.getNome()));

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

     */
}

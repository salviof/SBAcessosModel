/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import config.ConfiguradorCoreSBAcessosModelTestes;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import config.ConfigPersistenciaTestesAcesso;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 * @author desenvolvedor
 */
public class Permitido_GruposTest extends TesteJunitSBPersistencia {

    UsuarioSB usuario1 = new UsuarioSB();
    UsuarioSB usuario2 = new UsuarioSB();
    UsuarioSB usuario3 = new UsuarioSB();
    UsuarioSB usuario4 = new UsuarioSB();
    GrupoUsuarioSB grupo1 = new GrupoUsuarioSB();
    GrupoUsuarioSB grupo2 = new GrupoUsuarioSB();

    public Permitido_GruposTest() {
    }

    @Before
    public void setUp() {
        try {
            usuario1.setApelido("usrTeste1");
            usuario2.setApelido("usrTeste2");
            usuario3.setApelido("usrTeste3");
            usuario4.setApelido("usrTeste4");

            usuario1.setGrupo(grupo1);
            usuario2.setGrupo(grupo1);
            usuario3.setGrupo(grupo1);
            usuario4.setGrupo(grupo2);

            usuario1.setId(1l);
            usuario2.setId(2l);
            usuario3.setId(3l);
            usuario4.setId(4l);

            usuario1.ajustaConfiguracoes();
            usuario2.ajustaConfiguracoes();
            usuario3.ajustaConfiguracoes();
            usuario4.ajustaConfiguracoes();
            grupo1.setId(1l);
            grupo2.setId(2l);
            grupo1.setNome("Grupo 1");
            grupo2.setNome("Grupo 2");

            UtilSBPersistencia.mergeRegistro(grupo1, getEMTeste());
            UtilSBPersistencia.mergeRegistro(grupo2, getEMTeste());

            UtilSBPersistencia.mergeRegistro(usuario1, getEMTeste());
            UtilSBPersistencia.mergeRegistro(usuario2, getEMTeste());
            UtilSBPersistencia.mergeRegistro(usuario3, getEMTeste());
            UtilSBPersistencia.mergeRegistro(usuario4, getEMTeste());

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    @Test
    public void testarPermissoes() {
        try {
            PermissaoSB permissaoTeste = new PermissaoSB();
            permissaoTeste.setId(11l);
            permissaoTeste.addGrupoPermitido(grupo1);
            permissaoTeste.addGrupoPermitido(grupo2);
            permissaoTeste.setId(11l);
            permissaoTeste = (PermissaoSB) UtilSBPersistencia.mergeRegistro(permissaoTeste, getEMTeste());
            permissaoTeste.getGruposPermitidos().remove(0);
            UtilSBPersistencia.mergeRegistro(permissaoTeste, getEMTeste());
            permissaoTeste = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class, 11l);
            assertTrue("A permissão não foi removida", permissaoTeste.getGruposDisponiveis().size() == 1);
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreSBAcessosModelTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());
    }

}

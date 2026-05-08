/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.fabricas;

import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabSegurancaGruposPadrao implements ComoFabrica {

    GRUPO_ADMINISTRADOR, GRUPO_USUARIOS, GRUPO_USUARIO_AVANCADO;

    @Override
    public GrupoUsuarioSB getRegistro() {
        GrupoUsuarioSB novoGrupo = new GrupoUsuarioSB();
        novoGrupo.setId((long) this.ordinal());
        novoGrupo.setNome(this.name());

        switch (this) {
            case GRUPO_ADMINISTRADOR:
                novoGrupo.setPaginaInicial(FabAcaoProjetoSB.PROJETO_FRM_VISAO_GERAL);
                novoGrupo.setModuloPrincipal(FabModulosSistemaSB.ADMIN_TOOLS.getModulo());
                break;
            case GRUPO_USUARIOS:
                novoGrupo.setPaginaInicial(FabAcaoProjetoSB.PROJETO_FRM_VISAO_GERAL);
                novoGrupo.setModuloPrincipal(FabModulosSistemaSB.PAGINAS_DO_SISTEMA.getModulo());
                break;
            case GRUPO_USUARIO_AVANCADO:
                novoGrupo.setPaginaInicial(FabAcaoProjetoSB.PROJETO_FRM_VISAO_GERAL);
                novoGrupo.setModuloPrincipal(FabModulosSistemaSB.ADMIN_TOOLS.getModulo());
                break;
            default:
                throw new AssertionError(this.name());

        }
        return novoGrupo;
    }

}

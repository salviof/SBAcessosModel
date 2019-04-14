/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistemaNativo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreGeradorDeID;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ItfFabricaModulo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ItfFabricaMenu;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreGeradorDeIDSimples;

/**
 *
 * @author desenvolvedor
 */
public enum FabModulosSistemaSB implements ItfFabricaModulo {
    @InfoObjetoDaFabrica()
    SEGURANCA,
    COMUNICACAO,
    PAGINAS_DO_SISTEMA,
    DOCUMENTOS_INTRANET_GESTAO,
    DOCUMENTOS_INTRANET_EXTERNO,
    ADMIN_TOOLS;

    @Override
    public ModuloAcaoSistema getModulo() {
       
        ModuloAcaoSistemaNativo modulo = new ModuloAcaoSistemaNativo();
        modulo.setUmModuloNativo(true);
        modulo.setEnumVinculado(this);
        switch (this) {
            case SEGURANCA:
                modulo.setNome("Segurança");
                modulo.setDescricao("Define as configurações de segurança do sistema");
                modulo.setIconeDaClasse("fa fa-key");
                modulo.setUmModuloNativo(true);
                break;
            case COMUNICACAO:
                modulo.setNome("Comunicação");
                modulo.setDescricao("Módulo de  comunicação básico do sistema");
                modulo.setIconeDaClasse("fa fa-comments-o");
                modulo.setUmModuloNativo(true);
                break;
            case PAGINAS_DO_SISTEMA:
                modulo.setNome("Paginas do Sistema");
                modulo.setDescricao("Paginas Genericas do sistema, como Login, acesso negado, e outros");
                modulo.setIconeDaClasse("fa fa-cogs");
                modulo.setUmModuloNativo(true);
                break;
            case DOCUMENTOS_INTRANET_GESTAO:
                modulo.setNome("Gestão Documentos em Núvem");
                modulo.setDescricao("Gestão de arquivos compatilhados em núvem");
                modulo.setIconeDaClasse("fa fa-file-text-o");
                modulo.setUmModuloNativo(true);
                break;
            case DOCUMENTOS_INTRANET_EXTERNO:
                modulo.setNome("Documentos Compartilhados");
                modulo.setDescricao("Acesso a documentos compartilhados em núvem");
                modulo.setUmModuloNativo(true);
                modulo.setIconeDaClasse(FabIconeFontAwesome.ESCRITORIO_DOCUMENTO_TEXTO.getIcone().getTagHtml());
                break;
            case ADMIN_TOOLS:
                modulo.setNome("SB Admin Tools");
                modulo.setDescricao("Ferramentas de administração do projeto");
                modulo.setUmModuloNativo(true);
                modulo.setIconeDaClasse("fa fa-suitcase");

                break;
            default:
                throw new AssertionError(this.name());

        }
        
       
       modulo.setId(UtilSBCoreGeradorDeIDSimples.gerarIdUnicoObejtoVinculadoAFabrica(modulo));

        return modulo;
    }

    @Override
    public ModuloAcaoSistema getRegistro() {
        return getModulo();
    }

    @Override
    public ItfFabricaMenu getMenuPadrao() {
        switch (this) {
            case SEGURANCA:
                break;
            case COMUNICACAO:
                break;
            case PAGINAS_DO_SISTEMA:
                break;
            case DOCUMENTOS_INTRANET_GESTAO:
                break;
            case DOCUMENTOS_INTRANET_EXTERNO:
                break;
            case ADMIN_TOOLS:
                return FabMenuAdmin.ROOT;

            default:
                throw new AssertionError(this.name());

        }
        return null;

    }
}

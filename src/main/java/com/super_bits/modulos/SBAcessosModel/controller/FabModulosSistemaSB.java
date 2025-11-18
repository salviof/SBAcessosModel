/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistemaNativo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ComoFabricaModulo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreGeradorDeIDSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoFabricaMenu;

/**
 *
 * @author desenvolvedor
 */
public enum FabModulosSistemaSB implements ComoFabricaModulo {
    @InfoObjetoDaFabrica()
    SEGURANCA,
    COMUNICACAO,
    PAGINAS_DO_SISTEMA,
    DOCUMENTOS_INTRANET_GESTAO,
    DOCUMENTOS_INTRANET_EXTERNO,
    ADMIN_TOOLS,
    AGENDA,
    COMERCIO_SERVICOS,
    COMERCIO_PRODUTOS,
    LEAD;

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
            case AGENDA:
                modulo.setNome("Agendamento");
                modulo.setDescricao("Sistema core para gestão de Agendamento de atividades e Reservas");
                modulo.setUmModuloNativo(true);
                modulo.setIconeDaClasse("fa fa-calendar-check-o");
                break;
            case COMERCIO_SERVICOS:
                modulo.setNome("Comercio Serviços");
                modulo.setDescricao("Sistema core para gestão de serviços");
                modulo.setUmModuloNativo(true);
                modulo.setIconeDaClasse("fa fa-suitcase");
                break;
            case COMERCIO_PRODUTOS:
                modulo.setNome("Comercio Produtos");
                modulo.setDescricao("Sistema core para gestão de produtos");
                modulo.setUmModuloNativo(true);
                modulo.setIconeDaClasse("fa fa-cube");
                break;
            case LEAD:
                modulo.setNome("Marketing, Lead");
                modulo.setDescricao("Sistema Core para gestão de Leads");
                modulo.setUmModuloNativo(true);
                modulo.setIconeDaClasse("fa fa-address-book-o");
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
    public ComoFabricaMenu getMenuPadrao() {
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
            case AGENDA:
                break;
            case COMERCIO_SERVICOS:
                break;
            case COMERCIO_PRODUTOS:
                break;
            case LEAD:
                break;

            default:
                throw new AssertionError(this.name());

        }
        return null;

    }
}

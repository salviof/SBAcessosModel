/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.modulos.SBAcessosModel.fabricas.FabAcaoProjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ItfFabricaMenu;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ItfMenuSB;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.SessaoMenuSB;
import java.util.List;
import org.coletivojava.fw.api.objetoNativo.view.menu.MenuSBFW;

/**
 *
 * @author SalvioF
 */
public enum FabMenuAdmin implements ItfFabricaMenu {

    ROOT;

    @Override
    public List<ItfMenuSB> getTodosMenus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MenuSBFW getMenuPrincipal() {
        MenuSBFW menu = new MenuSBFW();
        SessaoMenuSB sessao = new SessaoMenuSB("Admin", FabIconeFontAwesome.SISTEMA_ENGRENAGEM.getIcone().getTagHtml());

        sessao.addAcao(FabAcaoProjetoSB.PROJETO_MB_GERENCIAR.getRegistro());
        sessao.addAcao(FabAcaoProjetoSB.PROJETO_FRM_VISAO_GERAL.getRegistro());
        sessao.addAcao(FabAcaoProjetoSB.PROJETO_FRM_VISUALIZAR_ACOES.getRegistro());
        sessao.addAcao(FabAcaoProjetoSB.PROJETO_FRM_VISUALIZAR_BANCO_DE_DADOS.getRegistro());

        return menu;
    }

    @Override
    public MenuSBFW getMenuSecundario() {
        return new MenuSBFW();
    }

}

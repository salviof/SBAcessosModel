/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.modulos.SBAcessosModel.fabricas.FabAcaoProjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.icones.FabIconeFontAwesome;
import org.coletivojava.fw.api.objetoNativo.view.menu.SessaoMenuSB;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.objetoNativo.view.menu.MenuSBFW;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoMenuSB;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoFabricaMenu;

/**
 *
 * @author SalvioF
 */
public enum FabMenuAdmin implements ComoFabricaMenu {

    ROOT;

    @Override
    public List<ComoMenuSB> getTodosMenus() {
        return new ArrayList<>();
    }

    @Override
    public MenuSBFW getMenuPrincipal() {
        MenuSBFW menu = new MenuSBFW();

        return menu;
    }

    @Override
    public MenuSBFW getMenuSecundario() {
        return new MenuSBFW();
    }

}

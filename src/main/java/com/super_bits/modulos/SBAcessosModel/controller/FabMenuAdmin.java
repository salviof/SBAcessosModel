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
import org.coletivojava.fw.api.objetoNativo.view.menu.SessaoMenuSB;
import java.util.ArrayList;
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

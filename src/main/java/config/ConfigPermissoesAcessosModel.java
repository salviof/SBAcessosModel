/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import com.super_bits.modulos.SBAcessosModel.ConfigPermissoesAcessoModelAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ComoTokenAcessoBasico;

import org.coletivojava.fw.api.objetoNativo.view.menu.MenusDaSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoMenusDeSessao;

/**
 * \
 *
 * @author desenvolvedor
 */
public abstract class ConfigPermissoesAcessosModel extends ConfigPermissoesAcessoModelAbstrato {

    private static Class[] getClasses() {
        Class[] classes = {};
        return classes;
    }

    public ConfigPermissoesAcessosModel(Class[] pClassesControllers) {
        super(pClassesControllers);
    }

    public ConfigPermissoesAcessosModel() {
        super(getClasses());
    }

    @Override
    public ComoMenusDeSessao definirMenu(ComoGrupoUsuario pGrupo) {

        return new MenusDaSessao(pGrupo.getModuloPrincipal().getEnumVinculado().getMenuPadrao().getMenuPrincipal(), pGrupo.getModuloPrincipal().getEnumVinculado().getMenuPadrao().getMenuSecundario());
    }

}

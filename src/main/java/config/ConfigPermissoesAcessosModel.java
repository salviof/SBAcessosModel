/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import com.super_bits.modulos.SBAcessosModel.ConfigPermissoesAcessoModelAbstrato;
import com.super_bits.modulos.SBAcessosModel.controller.FabMenuAdmin;
import com.super_bits.modulos.SBAcessosModel.fabricas.FabAcaoProjetoSB;
import com.super_bits.modulos.SBAcessosModel.fabricas.acoesDemonstracao.FabAcaoDemonstracaoSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ErroDadosDeContatoUsuarioNaoEncontrado;
import com.super_bits.modulosSB.SBCore.modulos.erp.FabTipoAgenteOrganizacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;

import org.coletivojava.fw.api.objetoNativo.view.menu.MenusDaSessao;
import org.coletivojava.fw.api.objetoNativo.view.menu.MenuSBFW;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.contato.ComoContatoHumano;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoMenusDeSessao;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StackType;

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

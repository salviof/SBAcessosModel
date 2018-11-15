/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import java.io.Serializable;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author desenvolvedor
 */
public class GeradorIdPermissao implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object obj) throws HibernateException {
        try {
            PermissaoSB permissao = ((PermissaoSB) obj);
            int id = UtilSBController.gerarIDAcaoDoSistema(permissao.getAcao().getEnumAcaoDoSistema());

            return id;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando id para permissao", t);
        }

        return 0;
    }

}

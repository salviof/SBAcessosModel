/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author desenvolvedor
 */
public class GeradorIdAcao implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object pObj) throws HibernateException {
        try {
            AcaoDoSistema acao = ((AcaoDoSistema) pObj);

            return UtilSBController.gerarIDAcaoDoSistema(acao.getEnumAcaoDoSistema());

        } catch (Throwable t) {
            throw new HibernateException("Erro determinando id para Ação" + pObj + t.getMessage());

        }

    }

}

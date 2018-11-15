/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.io.Serializable;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author desenvolvedor
 */
public class GeradorIDentityManualMysql implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sessao, Object obj) throws HibernateException {
        try {

            ItfBeanSimplesSomenteLeitura item = (ItfBeanSimplesSomenteLeitura) obj;
            List<Object> resultado = sessao.createNativeQuery("select id from " + item.getClass().getSimpleName() + " order by id desc limit 1 ").getResultList();
            if (resultado == null || resultado.isEmpty()) {
                return 1;
            } else {

                Integer valor = Integer.parseInt(resultado.get(0).toString());

                return valor + 1;
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando id manual Mysql para " + obj, t);
            return null;
        }

    }
}

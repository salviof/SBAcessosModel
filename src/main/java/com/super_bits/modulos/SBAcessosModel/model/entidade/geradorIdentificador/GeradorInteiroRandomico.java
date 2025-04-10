/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulos.SBAcessosModel.model.entidade.geradorIdentificador;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreNumeros;
import java.io.Serializable;
import javax.persistence.EntityManager;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author salvio
 */
public class GeradorInteiroRandomico implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object pObj) throws HibernateException {
        try {
            EntidadeSimples entidade = ((EntidadeSimples) pObj);
            boolean numeroGerado = false;
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            Long codigo = entidade.getId();
            while (!numeroGerado) {
                Long novoCodigo = (long) UtilSBCoreNumeros.getNumeroRandomico(1000, Integer.MAX_VALUE);
                Class classeEntidade = entidade.getClass();
                EntidadeSimples entidadeExistente = (EntidadeSimples) UtilSBPersistencia.getRegistroByID(classeEntidade, novoCodigo, em);
                if (entidadeExistente == null) {
                    codigo = novoCodigo;
                    numeroGerado = true;
                }
            }
            UtilSBPersistencia.fecharEM(em);
            return codigo;

        } catch (Throwable t) {
            throw new HibernateException("Erro determinando id para Ação" + pObj + t.getMessage());

        }

    }
}

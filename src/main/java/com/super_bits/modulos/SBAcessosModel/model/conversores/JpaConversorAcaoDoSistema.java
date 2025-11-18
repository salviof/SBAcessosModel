/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.conversores;

import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import javax.persistence.AttributeConverter;

/**
 *
 * @author desenvolvedor
 */
public class JpaConversorAcaoDoSistema implements AttributeConverter<ComoFabricaAcoes, String> {

    @Override
    public String convertToDatabaseColumn(ComoFabricaAcoes attribute) {
        if (attribute == null) {
            throw new UnsupportedOperationException("A fabrica de ações não pode ser nula para conversão compatível com persistencia");
        }
        return attribute.getNomeUnico();
    }

    @Override
    public ComoFabricaAcoes convertToEntityAttribute(String dbData) {
        return MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(dbData).getEnumAcaoDoSistema();
    }

}

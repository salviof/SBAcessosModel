/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.conversores;

import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import javax.persistence.AttributeConverter;

/**
 *
 * @author desenvolvedor
 */
public class JpaConversorAcaoDoSistema implements AttributeConverter<ItfFabricaAcoes, String> {

    @Override
    public String convertToDatabaseColumn(ItfFabricaAcoes attribute) {
        if (attribute == null) {
            throw new UnsupportedOperationException("A fabrica de ações não pode ser nula para conversão compatível com persistencia");
        }
        return attribute.getNomeUnico();
    }

    @Override
    public ItfFabricaAcoes convertToEntityAttribute(String dbData) {
        return MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(dbData).getEnumAcaoDoSistema();
    }

}

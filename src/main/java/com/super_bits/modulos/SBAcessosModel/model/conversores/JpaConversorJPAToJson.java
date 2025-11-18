/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.conversores;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import javax.persistence.AttributeConverter;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimplesSomenteLeitura;

/**
 *
 * @author desenvolvedor
 */
public class JpaConversorJPAToJson implements AttributeConverter<String, ComoEntidadeSimplesSomenteLeitura> {
    //private static final ObjectMapper om = new ObjectMapper();

    @Override
    public ComoEntidadeSimplesSomenteLeitura convertToDatabaseColumn(String attribute) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String convertToEntityAttribute(ComoEntidadeSimplesSomenteLeitura dbData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

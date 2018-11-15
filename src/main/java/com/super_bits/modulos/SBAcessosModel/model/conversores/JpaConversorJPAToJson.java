/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.conversores;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import javax.persistence.AttributeConverter;

/**
 *
 * @author desenvolvedor
 */
public class JpaConversorJPAToJson implements AttributeConverter<String, ItfBeanSimplesSomenteLeitura> {
    //private static final ObjectMapper om = new ObjectMapper();

    @Override
    public ItfBeanSimplesSomenteLeitura convertToDatabaseColumn(String attribute) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String convertToEntityAttribute(ItfBeanSimplesSomenteLeitura dbData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

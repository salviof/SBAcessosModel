/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.grupoUsuario;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author desenvolvedor
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ListaGrupoUsuario {

    public ListasGrupoUsuario lista();

}

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.view;

import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author desenvolvedor
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoAcaoPaginaDoSistema {

    FabAcaoPaginasDoSistema acao();

    public boolean padraoBloqueado() default false;
}

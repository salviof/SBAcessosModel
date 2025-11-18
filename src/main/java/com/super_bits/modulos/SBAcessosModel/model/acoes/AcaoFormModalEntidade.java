/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormEntidadeSec;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Ação formulario Modal relacionado a entidade"}, plural = "Ações de formulario Modal")
public class AcaoFormModalEntidade extends AcaoFormEntidadeSec {

    private static String formularioPadrao = "/resources/SBComp/resources/formularios/formularioModalGenerico.xhtml";

    @Deprecated
    public AcaoFormModalEntidade() {
        super();
    }

    public AcaoFormModalEntidade(ItfAcaoGerenciarEntidade pAcaoPrincipal, ComoFabricaAcoes enumAcao) {
        super(pAcaoPrincipal, enumAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_MODAL);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.FORMULARIO_MODAL;

    }

}

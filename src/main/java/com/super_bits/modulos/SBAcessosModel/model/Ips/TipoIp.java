/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90
 */
package com.super_bits.modulos.SBAcessosModel.model.Ips;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Tipo ip"}, plural = "Tipos de Ip")
public class TipoIp extends EntidadeSimples {

    @Id
    @GeneratedValue
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    private String descricao;

    public TipoIp() {
        super();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

}

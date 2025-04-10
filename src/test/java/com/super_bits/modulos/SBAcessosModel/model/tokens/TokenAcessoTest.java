/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.tokens;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedorninja01
 */
public class TokenAcessoTest {

    public TokenAcessoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class TokenAcesso.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        TokenAcesso instance = new TokenAcesso();
        Long expResult = 0l;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class TokenAcesso.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = 0l;
        TokenAcesso instance = new TokenAcesso();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCodigo method, of class TokenAcesso.
     */
    @Test
    public void testGetCodigo() {
        System.out.println("getCodigo");
        TokenAcesso instance = new TokenAcesso();
        String expResult = "";
        String result = instance.getCodigo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCodigo method, of class TokenAcesso.
     */
    @Test
    public void testSetCodigo() {
        System.out.println("setCodigo");
        String codigo = "";
        TokenAcesso instance = new TokenAcesso();
        instance.setCodigo(codigo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class TokenAcesso.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        TokenAcesso instance = new TokenAcesso();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class TokenAcesso.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        TokenAcesso instance = new TokenAcesso();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidade method, of class TokenAcesso.
     */
    @Test
    public void testGetValidade() {
        System.out.println("getValidade");
        TokenAcesso instance = new TokenAcesso();
        Date expResult = null;
        Date result = instance.getValidade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValidade method, of class TokenAcesso.
     */
    @Test
    public void testSetValidade() {
        System.out.println("setValidade");
        Date validade = null;
        TokenAcesso instance = new TokenAcesso();
        instance.setValidade(validade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataHoraCriacao method, of class TokenAcesso.
     */
    @Test
    public void testGetDataHoraCriacao() {
        System.out.println("getDataHoraCriacao");
        TokenAcesso instance = new TokenAcesso();
        Date expResult = null;
        Date result = instance.getDataHoraCriacao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataHoraCriacao method, of class TokenAcesso.
     */
    @Test
    public void testSetDataHoraCriacao() {
        System.out.println("setDataHoraCriacao");
        Date dataHoraCriacao = null;
        TokenAcesso instance = new TokenAcesso();
        instance.setDataHoraCriacao(dataHoraCriacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

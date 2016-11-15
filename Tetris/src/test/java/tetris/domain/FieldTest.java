/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author isjani
 */
public class FieldTest {
    private Field f;
    private Piece p;
    
    public FieldTest() {
    }
    
    @Before
    public void setUp() {
        f=new Field(10,20);
        p= new Piece(5,18,Formation.O);
    }
    @Test
    public void freezePiece(){
        assertEquals(0,f.getNumberOfBlocksOnRow(0));
        f.freezePiece(p);
        assertEquals(2,f.getNumberOfBlocksOnRow(0));
//        assertEquals(2,f.getNumberOfBlocksOnRow(1));
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

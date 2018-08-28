package com.ecarx.juint;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version $Rev$
 * @authod:Guoliang.Han
 * @des ${TODO}
 * @updateAuthor $authod$
 * @updateDes ${TODO}
 */
public class TestCalcService {
    @Test
    public void testAdd(){
        CalcService service = new CalcService();
        int result = service.add(3, 5);
        assertEquals(8,result);
    }
}

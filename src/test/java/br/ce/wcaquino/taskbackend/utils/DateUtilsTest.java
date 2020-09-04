package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.Assert;

public class DateUtilsTest {

    @Test
    public void deveRetornarTrueParaDatasFuturas() {
        LocalDate date = LocalDate.of(2030, 01, 01);
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));        
    }

    @Test
    public void deveRetornarTrueParaDatasPassadas() {
        LocalDate date = LocalDate.of(2010, 01, 01);
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));        
    }

    @Test
    public void deveRetornarTrueParaDataAtual() {
        LocalDate date = LocalDate.of(LocalDate.now());
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));        
    }
}
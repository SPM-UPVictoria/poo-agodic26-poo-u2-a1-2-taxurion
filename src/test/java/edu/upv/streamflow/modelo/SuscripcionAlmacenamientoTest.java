package edu.upv.streamflow.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

public class SuscripcionAlmacenamientoTest {

    @Test
    public void deberiaCobrarTarifaBaseDentroDelLimiteGratuito() {
        SuscripcionAlmacenamiento suscripcion = new SuscripcionAlmacenamiento("ALM-01", 100.0, 50, 5.0);
        suscripcion.registrarConsumo(30);
        assertEquals(100.0, suscripcion.calcularMontoFacturable(), 0.001);
    }

    @Test
    public void deberiaCobrarTarifaBaseEnElLimiteExacto() {
        SuscripcionAlmacenamiento suscripcion = new SuscripcionAlmacenamiento("ALM-02", 100.0, 50, 5.0);
        suscripcion.registrarConsumo(50);
        assertEquals(100.0, suscripcion.calcularMontoFacturable(), 0.001);
    }

    @Test
    public void deberiaCalcularExcedenteDeAlmacenamiento() {
        SuscripcionAlmacenamiento suscripcion = new SuscripcionAlmacenamiento("ALM-03", 100.0, 50, 5.0);
        suscripcion.registrarConsumo(70);
        assertEquals(200.0, suscripcion.calcularMontoFacturable(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deberiaLanzarExcepcionAlRegistrarConsumoNegativo() {
        SuscripcionAlmacenamiento suscripcion = new SuscripcionAlmacenamiento("ALM-04", 100.0, 50, 5.0);
        suscripcion.registrarConsumo(-15);
    }
}
package edu.upv.streamflow.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

public class SuscripcionStreamingTest {

    @Test
    public void deberiaCalcularTarifaSinPerfilesExtra() {
        SuscripcionStreaming suscripcion = new SuscripcionStreaming("STR-01", 150.0, 0, 50.0);
        assertEquals(150.0, suscripcion.calcularMontoFacturable(), 0.001);
    }

    @Test
    public void deberiaCalcularTarifaConPerfilesExtra() {
        SuscripcionStreaming suscripcion = new SuscripcionStreaming("STR-02", 150.0, 2, 50.0);
        assertEquals(250.0, suscripcion.calcularMontoFacturable(), 0.001);
    }

    @Test
    public void deberiaIncrementarCostoAlAgregarPerfil() {
        SuscripcionStreaming suscripcion = new SuscripcionStreaming("STR-03", 150.0, 1, 50.0);
        suscripcion.agregarPerfil();
        assertEquals(250.0, suscripcion.calcularMontoFacturable(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deberiaLanzarExcepcionSiPerfilesInicialesEsNegativo() {
        new SuscripcionStreaming("STR-04", 150.0, -2, 50.0);
    }
}
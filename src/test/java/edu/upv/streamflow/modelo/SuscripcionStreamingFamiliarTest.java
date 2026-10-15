package edu.upv.streamflow.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

public class SuscripcionStreamingFamiliarTest {

    @Test
    public void deberiaBonificarUnicoPerfilAdicional() {
        SuscripcionStreamingFamiliar suscripcion = new SuscripcionStreamingFamiliar("STR-FAM-1", 200.0, 1, 60.0);
        assertEquals(200.0, suscripcion.calcularMontoFacturable(), 0.001);
    }

    @Test
    public void deberiaCobrarPerfilesExcedentesDespuesDeBonificar() {
        SuscripcionStreamingFamiliar suscripcion = new SuscripcionStreamingFamiliar("STR-FAM-2", 200.0, 3, 60.0);
        assertEquals(320.0, suscripcion.calcularMontoFacturable(), 0.001);
    }

    @Test
    public void deberiaMantenerTarifaBaseSiNoHayPerfilesExtra() {
        SuscripcionStreamingFamiliar suscripcion = new SuscripcionStreamingFamiliar("STR-FAM-3", 200.0, 0, 60.0);
        assertEquals(200.0, suscripcion.calcularMontoFacturable(), 0.001);
    }
}
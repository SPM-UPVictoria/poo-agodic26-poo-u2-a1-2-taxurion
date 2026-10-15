package edu.upv.streamflow.modelo;

import edu.upv.streamflow.excepciones.SuscripcionInactivaException;
import edu.upv.streamflow.excepciones.TransaccionRechazadaException;
import edu.upv.streamflow.interfaces.Facturable;
import org.junit.Test;
import static org.junit.Assert.*;

public class CicloCobroPolimorficoTest {

    @Test
    public void deberiaProcesarCobroPolimorficoEnSuscripcionStreaming() throws TransaccionRechazadaException {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 1000.0);
        Facturable servicio = new SuscripcionStreaming("STR-01", 150.0, 2, 50.0);

        servicio.procesarCobro(cliente);
        assertEquals(750.0, cliente.getSaldoDisponible(), 0.001);
    }

    @Test
    public void deberiaFallarCobroPorSaldoInsuficiente() {
        Cliente cliente = new Cliente("CLI-2", "Luis", 50.0);
        SuscripcionStreaming servicio = new SuscripcionStreaming("STR-02", 150.0, 2, 50.0);

        try {
            servicio.procesarCobro(cliente);
            fail("Se esperaba TransaccionRechazadaException");
        } catch (TransaccionRechazadaException e) {
            assertEquals(50.0, cliente.getSaldoDisponible(), 0.001);
        }
    }

    @Test(expected = SuscripcionInactivaException.class)
    public void deberiaLanzarExcepcionNoMarcadaAlCobrarServicioCancelado() throws TransaccionRechazadaException {
        Cliente cliente = new Cliente("CLI-3", "Pedro", 1000.0);
        SuscripcionStreaming servicio = new SuscripcionStreaming("STR-03", 150.0, 0, 0.0);

        servicio.cancelarServicio();
        servicio.procesarCobro(cliente);
    }

    @Test
    public void deberiaPermitirCancelarServicioActivo() {
        SuscripcionStreaming servicio = new SuscripcionStreaming("STR-04", 150.0, 0, 0.0);
        assertTrue(servicio.estaActivo());

        servicio.cancelarServicio();
        assertFalse(servicio.estaActivo());
    }
}
package edu.upv.streamflow.modelo;

import edu.upv.streamflow.excepciones.TransaccionRechazadaException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {

    @Test
    public void deberiaInstanciarClienteValido() {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 1500.0);
        assertEquals("CLI-1", cliente.getIdCliente());
        assertEquals("Ana Gomez", cliente.getNombre());
        assertEquals(1500.0, cliente.getSaldoDisponible(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deberiaLanzarExcepcionPorNombreInvalido() {
        new Cliente("CLI-2", "", 1000.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deberiaLanzarExcepcionPorSaldoInicialNegativo() {
        new Cliente("CLI-3", "Carlos", -1.0);
    }

    @Test
    public void deberiaAbonarSaldoCorrectamente() {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 1000.0);
        cliente.abonarSaldo(500.0);
        assertEquals(1500.0, cliente.getSaldoDisponible(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deberiaLanzarExcepcionAlAbonarMontoNegativo() {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 1000.0);
        cliente.abonarSaldo(-50.0);
    }

    @Test
    public void deberiaDebitarSaldoExitosamente() throws TransaccionRechazadaException {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 800.0);
        cliente.debitarSaldo(300.0);
        assertEquals(500.0, cliente.getSaldoDisponible(), 0.001);
    }

    @Test(expected = TransaccionRechazadaException.class)
    public void deberiaLanzarExcepcionPropiaAlDebitarMontoMayorAlSaldo() throws TransaccionRechazadaException {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 200.0);
        cliente.debitarSaldo(500.0);
    }
}
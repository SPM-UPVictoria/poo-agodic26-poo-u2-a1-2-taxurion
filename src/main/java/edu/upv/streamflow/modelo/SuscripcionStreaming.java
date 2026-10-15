package edu.upv.streamflow.modelo;

public class SuscripcionStreaming extends Suscripcion {
    protected int perfilesAdicionales;
    protected double costoPorPerfilAdicional;

    public SuscripcionStreaming(String codigoContrato, double tarifaBase, int perfilesAdicionales, double costoPorPerfilAdicional) {
        super(codigoContrato, tarifaBase);
        if (perfilesAdicionales < 0 || costoPorPerfilAdicional < 0) {
            throw new IllegalArgumentException("Los perfiles adicionales y su costo no pueden ser negativos.");
        }
        this.perfilesAdicionales = perfilesAdicionales;
        this.costoPorPerfilAdicional = costoPorPerfilAdicional;
    }

    public void agregarPerfil() {
        this.perfilesAdicionales++;
    }

    @Override
    public double calcularMontoFacturable() {
        return tarifaBase + (perfilesAdicionales * costoPorPerfilAdicional);
    }
}
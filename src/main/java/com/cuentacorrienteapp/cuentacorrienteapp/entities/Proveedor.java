package com.cuentacorrienteapp.cuentacorrienteapp.entities;

public class Proveedor {

    private Integer idProveedor;
    private Integer idUser;
    private String nombreProveedor;
    private Long numeroCelular;
    private String emailProveedor;
    private String direccionProveedor;

    public Proveedor(Integer idProveedor, Integer idUser, String nombreProveedor, Long numeroCelular,
            String emailProveedor, String direccionProveedor) {
        this.idProveedor = idProveedor;
        this.idUser = idUser;
        this.nombreProveedor = nombreProveedor;
        this.numeroCelular = numeroCelular;
        this.emailProveedor = emailProveedor;
        this.direccionProveedor = direccionProveedor;
    }

    public Proveedor() {
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Long getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(Long numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

}

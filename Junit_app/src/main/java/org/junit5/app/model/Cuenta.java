package org.junit5.app.model;

import org.junit5.app.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;

public class Cuenta {
    private String persona;
    private BigDecimal salso;

    private Banco banco;

    public Cuenta(String persona, BigDecimal salso) {
        this.salso = salso;
        this.persona = persona;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSalso() {
        return salso;
    }

    public void setSalso(BigDecimal salso) {
        this.salso = salso;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void debito(BigDecimal monto){
        BigDecimal nuevoSaldo =   this.salso.subtract(monto);
        if(nuevoSaldo.compareTo(BigDecimal.ZERO)  < 0){
            throw  new DineroInsuficienteException("Dinero Insuficiente");
        }
        this.salso = nuevoSaldo;
    }

    public void credito(BigDecimal monto){
        this.salso =  this.salso.add(monto);
    }


    @Override
    public boolean equals(Object obj){

        if(!(obj instanceof Cuenta)){
            return false;
        }
        Cuenta cuenta = (Cuenta) obj;

        if (this.persona == null || this.salso == null){
            return false;
        }
        return persona.equals(cuenta.getPersona()) && this.salso.equals(cuenta.salso);
       // return  super.equals(obj);
    }
}

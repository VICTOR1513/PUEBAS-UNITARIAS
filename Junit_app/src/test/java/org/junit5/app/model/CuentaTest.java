package org.junit5.app.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit5.app.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {
    @Test
    @DisplayName("probando el nombre de la cuenta") /*NOMBRE DEL TEST */
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta("Victor", new BigDecimal("10.10"));
        //cuenta.setPersona("Victor");
        //cuenta.setSalso(new BigDecimal(10));
        String esperado = "Victor";
        String real = cuenta.getPersona();

        assertNotNull(real, "La cuneta no puede ser nula");
        assertEquals(esperado, real, "El nombre de la cuenta no es el que se esperaba");/* DEBE SER IGUAL AL ESPERADO */
        assertTrue(real.equals("Victor"),"Nombre de la cuenta esperada debe ser igual a la real");/*COMPARA DOS OBJETOS Y DEBEN SER IGUALES PARA PARA PASAR EL TEST*/

    }

    @Test
    @DisplayName("probando el nombre de la cuenta Corriente que no sea null, mayor que cero , valor esperado") /*NOMBRE DEL TEST */
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Victor", new BigDecimal("10.10"));
        assertEquals(10.10, cuenta.getSalso().doubleValue());
        assertFalse(cuenta.getSalso().compareTo(BigDecimal.ZERO) < 0);/*SI LA CONDICION ES FALSA PASA LA PRUEVA*/
        assertTrue(cuenta.getSalso().compareTo(BigDecimal.ZERO) > 0);/*SI LA CONDICION ES FALSA PASA LA PRUEVA*/

    }


    @Test
    @DisplayName("Testiando referencias que sean iguales con el metodo equals.") /*NOMBRE DEL TEST */
    void testReferenciaCuenta() {
        Cuenta cuenta1 = new Cuenta("Victor", new BigDecimal("10.10"));
        Cuenta cuenta2 = new Cuenta("Victor", new BigDecimal("10.10"));
        // assertNotEquals(cuenta2,cuenta1); // epera que no son iguales
        assertEquals(cuenta2, cuenta1);//compare por valor que sean iguales
    }

    @Test
    void testDebitoCuenta() {
        Cuenta cuenta = new Cuenta("Victor", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSalso()); // VALIDA QUE NO SEA NULO
        assertEquals(900, cuenta.getSalso().intValue());
        assertEquals("900.12345", cuenta.getSalso().toPlainString());
    }

    @Test
    void testCreditoCuenta() {
        Cuenta cuenta = new Cuenta("Victor", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSalso()); // VALIDA QUE NO SEA NULO
        assertEquals(1100, cuenta.getSalso().intValue());
        assertEquals("1100.12345", cuenta.getSalso().toPlainString());
    }

    @Test
    void dineroInsuficienteExceptioCuenta() {
        Cuenta cuenta = new Cuenta("Victor", new BigDecimal("1000.12345"));
        /*Control de exceptiones con junit*/
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado, actual);

    }

    @Test
    void trasferrirdienroCuentas() {
        Cuenta cuenta1 = new Cuenta("Victor", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Ana", new BigDecimal("1500.8989"));
        Banco banco = new Banco();
        banco.setNombre("Banco del estado");
        banco.trasferir(cuenta2, cuenta1, new BigDecimal(500));
        assertEquals("1000.8989", cuenta2.getSalso().toPlainString());
        assertEquals("3000", cuenta1.getSalso().toPlainString());
    }

    @Test
    @DisplayName("Probando relaciones entre las cuentas y el banco con assertAll.") /*NOMBRE DEL TEST */
    //@Disabled //desabilitar o que salte el metodo TEST
    void testRelacionBancoCuentas() {
        //fail(); /*FORZAR UN ERROR*/
        Cuenta cuenta1 = new Cuenta("Victor", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Ana", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);

        banco.setNombre("Banco del estado");
        banco.trasferir(cuenta2, cuenta1, new BigDecimal(500));
        /* EJECUTAR TODOS LOS TEST ASSERTESALL*/
        assertAll(
                () -> assertEquals("1000.8989", cuenta2.getSalso().toPlainString()),
                () -> assertEquals("3000", cuenta1.getSalso().toPlainString()),
                () -> assertEquals(2, banco.getCuentas().size()),
                () -> assertEquals("Banco del estado", cuenta1.getBanco().getNombre()),
                () -> {
                    assertEquals("Ana", banco.getCuentas().stream()
                            .filter(cuenta -> cuenta.getPersona().equals("Ana"))
                            .findFirst()
                            .get().getPersona());
                },
                () -> {
                    assertTrue(banco.getCuentas().stream()
                            .filter(cuenta -> cuenta.getPersona().equals("Ana"))
                            .findFirst().isPresent());
                },
                () -> {
                    assertTrue(banco.getCuentas().stream()
                            .anyMatch(cuenta -> cuenta.getPersona().equals("Ana")));
                }
        );


    }

}
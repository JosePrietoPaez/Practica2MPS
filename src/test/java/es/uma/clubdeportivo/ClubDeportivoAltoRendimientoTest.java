package es.uma.clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClubDeportivoAltoRendimientoTest {

    ClubDeportivoAltoRendimiento club;

    @BeforeEach
    void setup() throws ClubException {
        club = new ClubDeportivoAltoRendimiento("Club de Prueba", 20, 10);
    }

    @Test
    @DisplayName("Test anyadirActividad - Formato correcto")
    void anyadirActividad_actividad_actividadAnyadida() throws ClubException{
        String[] datos = { "Actividad 1", "Futbol", "20", "10", "50.0" };
        club.anyadirActividad(datos);
        assertEquals(10, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test anyadirActividad - Formato incorrecto")
    void anyadirActividad_formatoIncorrecto_lanzaExcepcion(){
        String[] datosMod = { "Actividad 1", "Futbol", "veinte", "10", "50.0"};
        assertThrows(ClubException.class, () -> club.anyadirActividad(datosMod));
    }

    @Test
    @DisplayName("Test anyadirActividad - Faltan Datos")
    void anyadirActividad_faltanDatos_lanzaExcepcion(){
        String[] datosMod = { "Actividad 1", "Futbol", "20", "10"};
        assertThrows(ClubException.class, () -> club.anyadirActividad(datosMod));
    }

    @Test
    @DisplayName("Test anyadirActividad - Numero de plazas mayor que al permitido")
    void anyadirActividad_numeroPlazasMayorQuePermitido_numeroPlazasIgualQuePermitido() throws ClubException{
        String[] datosMod = { "Actividad 1", "Futbol", "30", "10", "50.0"};
        club.anyadirActividad(datosMod);
        assertEquals(10, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test ingresos - Incremento 10%")
    void ingresos_incremento10() {
        assertEquals(0, club.ingresos());
    }



}

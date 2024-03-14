package es.uma.clubdeportivo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClubDeportivoTest {

    private ClubDeportivo club;
    private final String[] datos = { "Actividad 1", "Futbol", "20", "10", "50.0" };

    private static final Grupo grupo;

    static {
        try {
            grupo = new Grupo("Actividad 1", "Futbol", 20, 10, 50.0);
        } catch (ClubException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setup() throws ClubException {
        club = new ClubDeportivo("Club de Prueba");
    }

    @Test
    @DisplayName("Test 1: anyadirActividad")
    void anyadirActividad_actividad_actividadAnyadida() throws ClubException{
        club.anyadirActividad(datos);
        assertEquals(10, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test 2: anyadirActividad - Formato incorrecto")
    void anyadirActividad_formatoIncorrecto_lanzaExcepcion(){
        String[] datosMod = { "Actividad 1", "Futbol", "veinte", "10", "50.0"};
        assertThrows(ClubException.class, () -> club.anyadirActividad(datosMod));
    }

    @Test
    @DisplayName("Test 3: anyadirActividad - Grupo nulo")
    void anyadirActividad_actividadNula_lanzaExcepcion(){
        Grupo g = null;
        assertThrows(ClubException.class, () -> club.anyadirActividad(g));
    }

    @Test
    @DisplayName("Test 4: anyadirActividad - Grupo nuevo")
    void anyadirActividad_grupoNuevo_grupoAnyadido() throws ClubException {
        club.anyadirActividad(grupo);
        assertEquals(10, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test 5: anyadirActividad - Grupo existente")
    void anyadirActividad_grupoExistente_grupoAnyadido() throws ClubException {
        club.anyadirActividad(grupo);
        Grupo g2 = new Grupo("Actividad 1", "Futbol", 30, 10, 50.0);
        club.anyadirActividad(g2);
        assertEquals(20, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test 6: plazasLibres - Actividad no existente")
    void plazasLibres_actividadNoExistente_ceroPlazasLibres() {
        assertEquals(0, club.plazasLibres("zzzzz"));
    }

    @Test
    @DisplayName("Test 7: plazasLibres - Actividad existente")
    void plazasLibres_actividadExistente_plazasLibres() throws ClubException {
        Grupo g = new Grupo("Actividad 1", "Futbol", 20, 10, 50.0);
        club.anyadirActividad(g);
        assertEquals(10, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test 8: matricular - Actividad no existente")
    void matricular_actividadNoExistente_devuelveExcepcion() {
        assertThrows(ClubException.class, () -> club.matricular("zzzzz", 5));
    }

    @Test
    @DisplayName("Test 9: matricular - Plazas insuficientes")
    void matricular_plazasInsuficientes_devuelveExcepcion() throws ClubException {
        Grupo g = new Grupo("Actividad 1", "Futbol", 20, 10, 50.0);
        club.anyadirActividad(g);
        assertThrows(ClubException.class, () -> club.matricular("Futbol", 15));
    }

    @Test
    @DisplayName("Test 10: matricular")
    void matricular_matriculado_matriculado() throws ClubException {
        Grupo g = new Grupo("Actividad 1", "Futbol", 20, 10, 50.0);
        club.anyadirActividad(g);
        club.matricular("Futbol", 5);
        assertEquals(5, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test 11: ingresos")
    void ingresos_ingresosCalculados_devuelveIngresos() throws ClubException {
        Grupo g = new Grupo("Actividad 1", "Futbol", 20, 10, 50.0);
        club.anyadirActividad(g);
        club.matricular("Futbol", 5);
        assertEquals(750.0, club.ingresos());
    }

    @Test
    @DisplayName("Test 12: toString")
    void toString_datosClub_devuelveToString() throws ClubException {
        Grupo g = new Grupo("Actividad 1", "Futbol", 20, 10, 50.0);
        club.anyadirActividad(g);
        assertEquals("Club de Prueba --> [ (Actividad 1 - Futbol - 50.0 euros - P:20 - M:10) ]", club.toString());
    }


}

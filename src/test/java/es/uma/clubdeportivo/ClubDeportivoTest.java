package es.uma.clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClubDeportivoTest {

    private ClubDeportivo club;
    private final String[] datos = { "Actividad 1", "Futbol", "20", "10", "50.0" };

    private static Grupo grupo;

    @BeforeEach
    void setup() throws ClubException {
        club = new ClubDeportivo("Club de Prueba");
        grupo = new Grupo("Actividad 1", "Futbol", 20, 10, 50.0);
    }

    @Test
    @DisplayName("Test constructor - numero de grupos invalidos")
    void constructor_gruposInvalidos_lanzaExcepcion() {
        assertThrows(ClubException.class, () -> new ClubDeportivo("Club de Prueba", 0));
    }

    @Test
    @DisplayName("Test anyadirActividad(String[]) - Formato correcto")
    void anyadirActividad_actividad_actividadAnyadida() throws ClubException{
        club.anyadirActividad(datos);
        assertEquals(10, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test anyadirActividad(String[]) - Formato incorrecto")
    void anyadirActividad_formatoIncorrecto_lanzaExcepcion(){
        String[] datosMod = { "Actividad 1", "Futbol", "veinte", "10", "50.0"};
        assertThrows(ClubException.class, () -> club.anyadirActividad(datosMod));
    }

    @Test
    @DisplayName("Test anyadirActividad(Grupo) - Grupo nulo")
    void anyadirActividad_actividadNula_lanzaExcepcion(){
        Grupo g = null;
        assertThrows(ClubException.class, () -> club.anyadirActividad(g));
    }

    @Test
    @DisplayName("Test anyadirActividad(Grupo) - Grupo nuevo")
    void anyadirActividad_grupoNuevo_grupoAnyadido() throws ClubException {
        club.anyadirActividad(grupo);
        assertEquals(10, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test anyadirActividad(Grupo) - Grupo existente")
    void anyadirActividad_grupoExistente_grupoAnyadido() throws ClubException {
        club.anyadirActividad(grupo);
        Grupo g2 = new Grupo("Actividad 1", "Futbol", 30, 10, 50.0);
        club.anyadirActividad(g2);
        assertEquals(20, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test anyadirActividad(Grupo) - sin espacio")
    void anyadirActividad_sinEspacio_lanzaExcepcion() throws ClubException {
        ClubDeportivo clubMax = new ClubDeportivo("Club de Prueba Max", 1);
        clubMax.anyadirActividad(grupo);
        Grupo g2 = new Grupo("Actividad 2", "Baloncesto", 30, 10, 50.0);
        assertThrows(ClubException.class, () -> clubMax.anyadirActividad(g2));
    }

    @Test
    @DisplayName("Test plazasLibres - Actividad no existente")
    void plazasLibres_actividadNoExistente_ceroPlazasLibres() {
        assertEquals(0, club.plazasLibres("zzzzz"));
    }

    @Test
    @DisplayName("Test plazasLibres - Actividad existente")
    void plazasLibres_actividadExistente_plazasLibres() throws ClubException {
        Grupo g = new Grupo("Actividad 1", "Futbol", 20, 10, 50.0);
        club.anyadirActividad(g);
        assertEquals(10, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test matricular - Actividad no existente")
    void matricular_actividadNoExistente_devuelveExcepcion(){
        assertThrows(ClubException.class, () -> club.matricular("zzzzz", 5));
    }

    @Test
    @DisplayName("Test matricular - Cero personas en ninguna plaza disponible")
    void matricular_ceroPersonasEnCeroPlazas_sinCambios() throws ClubException {
        Grupo g = new Grupo("Actividad 1", "Futbol", 20, 20, 50.0);
        club.anyadirActividad(g);
        club.matricular("Futbol", 0);
        assertEquals(0, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test matricular - Plazas insuficientes en primer grupo, reparte en segundo grupo")
    void matricular_plazasInsuficientesPrimerGrupo_matriculaEnSegundo() throws ClubException {
        Grupo g = new Grupo("Actividad 2", "Futbol", 20, 10, 50.0);
        club.anyadirActividad(grupo);
        club.anyadirActividad(g);
        club.matricular("Futbol", 15);
        assertEquals(5, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("Test matricular - Datos correctos")
    void matricular_matriculado_matriculado() throws ClubException {
        club.anyadirActividad(grupo);
        Grupo g = new Grupo("Actividad 1", "Baloncesto", 20, 10, 50.0);
        club.anyadirActividad(g);
        club.matricular("Baloncesto", 5);
        assertEquals(5, club.plazasLibres("Baloncesto"));
    }

    @Test
    @DisplayName("Test ingresos")
    void ingresos_ingresosCalculados_devuelveIngresos() throws ClubException {
        Grupo g = new Grupo("Actividad 1", "Futbol", 20, 10, 50.0);
        club.anyadirActividad(g);
        club.matricular("Futbol", 5);
        assertEquals(750.0, club.ingresos());
    }

    @Test
    @DisplayName("Test toString")
    void toString_datosClub_devuelveToString() throws ClubException {
        Grupo g = new Grupo("Actividad 1", "Futbol", 20, 10, 50.0);
        club.anyadirActividad(g);
        assertEquals("Club de Prueba --> [ (Actividad 1 - Futbol - 50.0 euros - P:20 - M:10) ]", club.toString());
    }


}

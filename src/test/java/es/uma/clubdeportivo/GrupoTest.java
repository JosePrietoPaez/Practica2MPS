package es.uma.clubdeportivo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrupoTest {

    private Grupo grupo;

    @Test
    public void constructor_argumentosValidos_creaElGrupo(){
        // Arrange
        String codigo = "a",
                actividad = "b";
        int nplazas = 100,
                matriculados = 50;
        double tarifa = 10d;

        // Act, con assert para controlar la excepcion
        assertDoesNotThrow(() -> grupo = new Grupo(codigo,actividad,nplazas,matriculados,tarifa));

        // Assert
        assertEquals(codigo,"a");
        assertEquals(actividad,"b");
        assertEquals(nplazas, 100);
        assertEquals(matriculados, 50);
        assertEquals(tarifa, 10d);
    }

    @Test
    public void constructor_excesoMatriculados_lanzaExcepcion(){
        // Arrange
        String codigo = "a",
                actividad = "b";
        int nplazas = 100,
                matriculados = 500;
        double tarifa = 10d;

        // Act, con assert para controlar la excepcion
        assertThrows(ClubException.class,() -> grupo = new Grupo(codigo,actividad,nplazas,matriculados,tarifa));
    }

    @Test
    public void constructor_plazasNegativas_lanzaExcepcion(){
        // Arrange
        String codigo = "a",
                actividad = "b";
        int nplazas = -1,
                matriculados = 50;
        double tarifa = 10d;

        // Act, con assert para controlar la excepcion
        assertThrows(ClubException.class,() -> grupo = new Grupo(codigo,actividad,nplazas,matriculados,tarifa));
    }

    @Test
    public void constructor_matriculadosNegativos_lanzaExcepcion(){
        // Arrange
        String codigo = "a",
                actividad = "b";
        int nplazas = 100,
                matriculados = -1;
        double tarifa = 10d;

        // Act, con assert para controlar la excepcion
        assertThrows(ClubException.class,() -> grupo = new Grupo(codigo,actividad,nplazas,matriculados,tarifa));
    }

    @Test
    public void constructor_tarifaNegativa_lanzaExcepcion(){
        // Arrange
        String codigo = "a",
                actividad = "b";
        int nplazas = 100,
                matriculados = 50;
        double tarifa = -1d;

        // Act, con assert para controlar la excepcion
        assertThrows(ClubException.class,() -> grupo = new Grupo(codigo,actividad,nplazas,matriculados,tarifa));
    }

    @Test
    public void getCodigo_devuelveElCodigo() {
        // Arrange
        String codigoEsperado = "codigo";
        assertDoesNotThrow(() -> grupo = new Grupo(codigoEsperado,"",1,1,1));

        // Act
        String codigoObtenido = grupo.getCodigo();

        // Assert
        assertEquals(codigoEsperado,codigoObtenido);
    }

    @Test
    public void getActividad_devuelveLaActividad() {
        // Arrange
        String actividadEsperada = "codigo";
        assertDoesNotThrow(() -> grupo = new Grupo("",actividadEsperada,1,1,1));

        // Act
        String actividadObtenida = grupo.getActividad();

        // Assert
        assertEquals(actividadEsperada,actividadObtenida);
    }

    @Test
    public void getPlazas_devuelveLasPlazas() {
        // Arrange
        int plazasEsperadas = 100;
        assertDoesNotThrow(() -> grupo = new Grupo("","",plazasEsperadas,1,1));

        // Act
        int plazasObtenidas = grupo.getPlazas();

        // Assert
        assertEquals(plazasEsperadas,plazasObtenidas);
    }

    @Test
    public void getMatriculados_devuelveLosMatriculados() {
        // Arrange
        int matriculadosEsperados = 100;
        assertDoesNotThrow(() -> grupo = new Grupo("","",1000,matriculadosEsperados,1));

        // Act
        int matriculadosObtenidos = grupo.getMatriculados();

        // Assert
        assertEquals(matriculadosEsperados,matriculadosObtenidos);
    }

    @Test
    public void getTarifa_devuelveLaTarifa() {
        // Arrange
        double tarifaEsperada = 100;
        assertDoesNotThrow(() -> grupo = new Grupo("","",1,1,tarifaEsperada));

        // Act
        double tarifaObtenida = grupo.getTarifa();

        // Assert
        assertEquals(tarifaEsperada,tarifaObtenida);
    }

    @Test
    public void matricular_argumentoValido_cambiaLosMatriculadosAlValorIndicado(){
        
    }
}

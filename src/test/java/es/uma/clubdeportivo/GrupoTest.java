package es.uma.clubdeportivo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    public void plazasLibres_devuelveElNumeroDePlazasMenosLasOcupadas(){
        // Arrange
        int plazasOcupadas = 100,matriculas = 20;
        assertDoesNotThrow(() -> grupo = new Grupo("","",plazasOcupadas,matriculas,1d));

        // Act
        int plazasRestantes = grupo.plazasLibres();

        // Assert
        assertEquals(plazasOcupadas - matriculas, plazasRestantes);
    }

    @Test
    public void actualizarPlazas_argumentoValido_cambiaElTotalDePlazas(){
        // Arrange
        int plazasIniciales = 100, plazasFinales = 150,matriculas = 20;
        assertDoesNotThrow(() -> grupo = new Grupo("","",plazasIniciales,matriculas,1d));

        // Act
        assertDoesNotThrow(() -> grupo.actualizarPlazas(plazasFinales));
        int plazasObtenidas = grupo.getPlazas();

        // Assert
        assertEquals(plazasFinales, plazasObtenidas);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,10})
    public void actualizarPlazas_argumentoNegativoOInsuficiente_lanzaExcepcion(int plazasFinales){
        // Arrange
        int plazasIniciales = 100, matriculas = 20;
        assertDoesNotThrow(() -> grupo = new Grupo("","",plazasIniciales,matriculas,1d));

        // Act
        assertThrows(ClubException.class,() -> grupo.actualizarPlazas(plazasFinales));
    }

    @Test
    public void matricular_argumentoValido_cambiaLosMatriculadosAlValorIndicado(){
        // Arrange
        int plazasNuevas = 100,matriculas = 20;
        assertDoesNotThrow(() -> grupo = new Grupo("","",200,matriculas,1d));

        // Act
        assertDoesNotThrow(() -> grupo.matricular(plazasNuevas));
        int plazasRestantes = grupo.plazasLibres();

        // Assert
        assertEquals(plazasNuevas - matriculas, plazasRestantes);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,100})
    public void matricular_excesoPlazasYNegativo_lanzaExcepcion(int plazasMatriculadas){
        // Arrange
        int matriculas = 20;
        assertDoesNotThrow(() -> grupo = new Grupo("","",30,matriculas,1d));

        // Act
        assertThrows(ClubException.class, () -> grupo.matricular(plazasMatriculadas));
    }

    @Test
    public void toString_contieneTodosLosDatos() throws ClubException {
        // Arrange
        String codigo = "a",
                actividad = "b";
        int nplazas = 100,
                matriculados = 50;
        double tarifa = 10d;
        grupo = new Grupo(codigo,actividad,nplazas,matriculados,tarifa);

        // Act
        String resultado = grupo.toString();

        // Assert
        assertTrue(resultado.contains(codigo));
        assertTrue(resultado.contains(actividad));
        assertTrue(resultado.contains(Integer.toString(nplazas)));
        assertTrue(resultado.contains(Integer.toString(matriculados)));
        assertTrue(resultado.contains(Double.toString(tarifa)));
    }

    @Test
    public void equals_gruposIguales_devuelveTrue() throws ClubException {
        // Assert
        String codigo = "a",
                actividad = "b";
        int nplazas = 100,
                matriculados = 50;
        double tarifa = 10d;
        Grupo primerGrupo = new Grupo(codigo,actividad,nplazas,matriculados,tarifa);
        Grupo otro = new Grupo(codigo,actividad,nplazas,matriculados,tarifa);

        // Act
        boolean sonIguales = primerGrupo.equals(otro);

        // Assert
        assertTrue(sonIguales);
    }

    @Test
    public void equals_gruposConCodigosDistintos_devuelveFalse() throws ClubException {
        // Assert
        String codigo1 = "a", codigo2 = "c",
                actividad = "b";
        int nplazas = 100,
                matriculados = 50;
        double tarifa = 10d;
        Grupo primerGrupo = new Grupo(codigo1,actividad,nplazas,matriculados,tarifa);
        Grupo otro = new Grupo(codigo2,actividad,nplazas,matriculados,tarifa);

        // Act
        boolean sonIguales = primerGrupo.equals(otro);

        // Assert
        assertFalse(sonIguales);
    }

    @Test
    public void equals_gruposConActividadesDistintas_devuelveFalse() throws ClubException {
        // Assert
        String codigo = "a",
                actividad1 = "b", actividad2 = "c";
        int nplazas = 100,
                matriculados = 50;
        double tarifa = 10d;
        Grupo primerGrupo = new Grupo(codigo,actividad1,nplazas,matriculados,tarifa);
        Grupo otro = new Grupo(codigo,actividad2,nplazas,matriculados,tarifa);

        // Act
        boolean sonIguales = primerGrupo.equals(otro);

        // Assert
        assertFalse(sonIguales);
    }

    @Test
    public void equals_gruposDiferentes_devuelveFalse() throws ClubException {
        // Assert
        String codigo = "a",
                actividad = "b";
        int nplazas = 100,
                matriculados = 50;
        double tarifa = 10d;
        grupo = new Grupo(codigo,actividad,nplazas,matriculados,tarifa);
        Grupo otro = new Grupo(codigo,"",nplazas,matriculados,tarifa);

        // Act
        boolean sonIguales = grupo.equals(otro);

        // Assert
        assertFalse(sonIguales);
    }

    @Test
    public void equals_objetoNoRelacionado_devuelveFalse() throws ClubException {
        // Assert
        String codigo = "a",
                actividad = "b";
        int nplazas = 100,
                matriculados = 50;
        double tarifa = 10d;
        grupo = new Grupo(codigo,actividad,nplazas,matriculados,tarifa);
        Integer otro = 23;

        // Act
        boolean sonIguales = grupo.equals(otro);

        // Assert
        assertFalse(sonIguales);
    }

    @Test
    public void equals_argumentoNull_devuelveFalse() throws ClubException {
        // Assert
        String codigo = "a",
                actividad = "b";
        int nplazas = 100,
                matriculados = 50;
        double tarifa = 10d;
        grupo = new Grupo(codigo,actividad,nplazas,matriculados,tarifa);
        Object nulo = null;

        // Act
        boolean sonIguales = grupo.equals(nulo);

        // Assert
        assertFalse(sonIguales);
    }

    @Test
    public void hashCode_noCambiaConPlazasMatriculaOTarifa() throws ClubException {
        // Assert
        String codigo = "a",
                actividad = "b";
        int plazas1 = 100, matriculas1 = 100,
                plazas2 = 200, matriculas2 = 50;
        double tarifa1 = 100d, tarifa2 = 200d;
        Grupo grupo1 = new Grupo(codigo,actividad,plazas1,matriculas1,tarifa1),
            grupo2 = new Grupo(codigo,actividad,plazas1,matriculas1,tarifa2),
            grupo3 = new Grupo(codigo,actividad,plazas1,matriculas2,tarifa1),
            grupo4 = new Grupo(codigo,actividad,plazas2,matriculas1,tarifa1);

        // Act
        int hash1 = grupo1.hashCode(),
                hash2 = grupo2.hashCode(),
                hash3 = grupo3.hashCode(),
                hash4 = grupo4.hashCode();

        // Assert, solo se comprueba 3 veces por la propiedad transitiva
        assertEquals(hash1,hash2);
        assertEquals(hash2,hash3);
        assertEquals(hash3,hash4);
    }
}

package tests;

import logica.Liga;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Campeon;
import modelo.Partida;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para la clase Liga
 * 
 * @author PelayoPS
 */
class LigaTest {

    private Liga liga;
    private static final String TEST_XML_PATH = "ficheros/test_database.xml";
    private static final String TEST_XML_PATH_BACKUP = "ficheros/database_backup.xml";

    @BeforeEach
    void setUp() {
        liga = new Liga(TEST_XML_PATH, TEST_XML_PATH_BACKUP);
    }

    @AfterEach
    void tearDown() {
        liga = null;
    }

    // Tests para Equipos
    @Test
    void testAgregarEquipo() {
        // Arrange
        Equipo equipo = new Equipo("Test Team");
        equipo.agregarJugador(new Jugador("Test Player", "Mid"));

        // Act
        liga.agregarEquipo(equipo);
        Equipo equipoEncontrado = liga.buscarEquipo("Test Team");

        // Assert
        assertNotNull(equipoEncontrado, "El equipo debería encontrarse después de agregarlo");
        assertEquals("Test Team", equipoEncontrado.getNombre(), "El nombre del equipo debe coincidir");
        assertEquals(1, equipoEncontrado.getJugadores().size(), "El equipo debe tener un jugador");
    }

    @Test
    void testBuscarEquipoExistente() {
        // Arrange
        Equipo equipo = new Equipo("Test Team");
        liga.agregarEquipo(equipo);

        // Act
        Equipo equipoEncontrado = liga.buscarEquipo("Test Team");

        // Assert
        assertNotNull(equipoEncontrado, "El equipo debería ser encontrado");
        assertEquals("Test Team", equipoEncontrado.getNombre(), "El nombre del equipo debe coincidir");
    }

    @Test
    void testBuscarEquipoInexistente() {
        // Act
        Equipo equipoEncontrado = liga.buscarEquipo("Equipo Inexistente");

        // Assert
        assertNull(equipoEncontrado, "No debería encontrar un equipo inexistente");
    }

    @Test
    void testActualizarEquipo() {
        // Arrange
        Equipo equipo = new Equipo("Test Team");
        liga.agregarEquipo(equipo);

        Equipo equipoActualizado = new Equipo("Test Team");
        equipoActualizado.agregarJugador(new Jugador("New Player", "Top"));

        // Act
        boolean actualizado = liga.actualizarEquipo(equipoActualizado);
        Equipo equipoEncontrado = liga.buscarEquipo("Test Team");

        // Assert
        assertTrue(actualizado, "La actualización debería ser exitosa");
        assertEquals(1, equipoEncontrado.getJugadores().size(), "El equipo debería tener un jugador");
        assertEquals("New Player", equipoEncontrado.getJugadores().get(0).getNombre(),
                "El nombre del jugador debe coincidir");
    }

    @Test
    void testEliminarEquipo() {
        // Arrange
        Equipo equipo = new Equipo("Test Team");
        liga.agregarEquipo(equipo);

        // Act
        boolean eliminado = liga.eliminarEquipo("Test Team");
        Equipo equipoEncontrado = liga.buscarEquipo("Test Team");

        // Assert
        assertTrue(eliminado, "La eliminación debería ser exitosa");
        assertNull(equipoEncontrado, "El equipo no debería encontrarse después de eliminarlo");
    }

    // Tests para Campeones
    @Test
    void testAgregarCampeon() {
        // Arrange
        Campeon campeon = new Campeon("Test Champion", "Mid");

        // Act
        liga.agregarCampeon(campeon);
        Campeon campeonEncontrado = liga.buscarCampeon("Test Champion");

        // Assert
        assertNotNull(campeonEncontrado, "El campeón debería encontrarse después de agregarlo");
        assertEquals("Test Champion", campeonEncontrado.getNombre(), "El nombre del campeón debe coincidir");
        assertEquals("Mid", campeonEncontrado.getRol(), "El rol del campeón debe coincidir");
    }

    @Test
    void testBuscarCampeonExistente() {
        // Arrange
        Campeon campeon = new Campeon("Test Champion", "Mid");
        liga.agregarCampeon(campeon);

        // Act
        Campeon campeonEncontrado = liga.buscarCampeon("Test Champion");

        // Assert
        assertNotNull(campeonEncontrado, "El campeón debería ser encontrado");
        assertEquals("Test Champion", campeonEncontrado.getNombre(), "El nombre del campeón debe coincidir");
    }

    @Test
    void testBuscarCampeonInexistente() {
        // Act
        Campeon campeonEncontrado = liga.buscarCampeon("Campeon Inexistente");

        // Assert
        assertNull(campeonEncontrado, "No debería encontrar un campeón inexistente");
    }

    @Test
    void testActualizarCampeon() {
        // Arrange
        Campeon campeon = new Campeon("Test Champion", "Mid");
        liga.agregarCampeon(campeon);

        Campeon campeonActualizado = new Campeon("Test Champion", "Top");

        // Act
        boolean actualizado = liga.actualizarCampeon(campeonActualizado);
        Campeon campeonEncontrado = liga.buscarCampeon("Test Champion");

        // Assert
        assertTrue(actualizado, "La actualización debería ser exitosa");
        assertEquals("Top", campeonEncontrado.getRol(), "El rol del campeón debe estar actualizado");
    }

    @Test
    void testEliminarCampeon() {
        // Arrange
        Campeon campeon = new Campeon("Test Champion", "Mid");
        liga.agregarCampeon(campeon);

        // Act
        boolean eliminado = liga.eliminarCampeon("Test Champion");
        Campeon campeonEncontrado = liga.buscarCampeon("Test Champion");

        // Assert
        assertTrue(eliminado, "La eliminación debería ser exitosa");
        assertNull(campeonEncontrado, "El campeón no debería encontrarse después de eliminarlo");
    }

    // Tests para Partidas
    @Test
    void testAgregarPartida() {
        liga = new Liga(TEST_XML_PATH, TEST_XML_PATH_BACKUP);

        // Arrange
        Equipo equipo = new Equipo("Test Team");
        liga.agregarEquipo(equipo);

        Equipo equipoActualizado = new Equipo("Test Team");
        equipoActualizado.agregarJugador(new Jugador("New Player", "Mid"));

        // Act
        Partida partida = new Partida("Equipo1", "Equipo2", "Ganado");
        liga.agregarPartida(partida);
        List<Partida> partidas = liga.getPartidas();

        // Assert
        assertFalse(partidas.isEmpty(), "La lista de partidas no debería estar vacía");
        assertEquals("Equipo1", partidas.get(0).getEquipo(), "El equipo 1 debe coincidir");
        assertEquals("Ganado", partidas.get(0).getResultado(), "El resultado de la partida debe coincidir");
    }

    @Test
    void testActualizarPartida() {
        // Arrange
        Partida partida = new Partida("Equipo1", "Equipo2", "Ganado");
        liga.agregarPartida(partida);

        Partida partidaActualizada = new Partida("Equipo1", "Equipo2", "Perdido");

        // Act
        boolean actualizado = liga.actualizarPartida(partidaActualizada, 0);
        List<Partida> partidas = liga.getPartidas();

        // Assert
        assertTrue(actualizado, "La actualización debería ser exitosa");
        assertEquals("Perdido", partidas.get(0).getResultado(), "El resultado de la partida debe estar actualizado");
    }

    @Test
    void testEliminarPartida() {
        // Arrange
        Partida partida = new Partida("Equipo1", "Equipo2", "Ganado");
        liga.agregarPartida(partida);
        int initialSize = liga.getPartidas().size();

        // Act
        boolean eliminado = liga.eliminarPartida(0);
        int finalSize = liga.getPartidas().size();

        // Assert
        assertTrue(eliminado, "La eliminación debería ser exitosa");
        assertEquals(initialSize - 1, finalSize, "El tamaño de la lista debe disminuir en 1");
    }

    @Test
    void testActualizarPartidaIndiceInvalido() {
        // Arrange
        Partida partidaActualizada = new Partida("Equipo1", "Equipo2", "Perdido");

        // Act
        boolean actualizado = liga.actualizarPartida(partidaActualizada, -1);

        // Assert
        assertFalse(actualizado, "La actualización con índice inválido debería fallar");
    }

    @Test
    void testEliminarPartidaIndiceInvalido() {
        // Act
        boolean eliminado = liga.eliminarPartida(-1);

        // Assert
        assertFalse(eliminado, "La eliminación con índice inválido debería fallar");
    }

    // Tests para casos límite y validación
    @Test
    void testAgregarEquipoNombreVacio() {
        // Arrange
        Equipo equipo = new Equipo("");

        // Act
        liga.agregarEquipo(equipo);
        Equipo equipoEncontrado = liga.buscarEquipo("");

        // Assert
        assertNull(equipoEncontrado, "No debería permitir agregar equipos con nombre vacío");
    }

    @Test
    void testAgregarEquipoDuplicado() {
        // Arrange
        Equipo equipo1 = new Equipo("Duplicate Team");
        Equipo equipo2 = new Equipo("Duplicate Team");

        // Act
        liga.agregarEquipo(equipo1);
        liga.agregarEquipo(equipo2);

        // Assert
        List<Equipo> equipos = liga.getEquipos();
        long countDuplicates = equipos.stream()
                .filter(e -> e.getNombre().equals("Duplicate Team"))
                .count();
        assertEquals(1, countDuplicates, "No debería permitir equipos duplicados");
    }

    @Test
    void testFormatoRolCampeonInvalido() {

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            Campeon campeon = new Campeon("Test Champion", "RolInvalido");

            liga.agregarCampeon(campeon);

        }, "Debería lanzar excepción por rol inválido");
    }

    // Tests para persistencia XML
    @Test
    void testPersistenciaXML() {
        // Arrange
        liga.agregarEquipo(new Equipo("Test Team"));
        liga.agregarCampeon(new Campeon("Test Champion", "Mid"));
        liga.agregarPartida(new Partida("Equipo1", "Equipo2", "Ganado"));

        // Act
        liga.guardarXML();
        Liga nuevaLiga = new Liga(TEST_XML_PATH_BACKUP, TEST_XML_PATH_BACKUP);

        // Assert
        assertEquals(liga.getEquipos().size(), nuevaLiga.getEquipos().size(), "El número de equipos debe coincidir");
        assertEquals(liga.getCampeones().size(), nuevaLiga.getCampeones().size(),
                "El número de campeones debe coincidir");
        assertEquals(liga.getPartidas().size(), nuevaLiga.getPartidas().size(), "El número de partidas debe coincidir");
    }
}
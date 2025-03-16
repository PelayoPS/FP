package modelo;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private List<Jugador> jugadores;

    public Equipo() {
        jugadores = new ArrayList<>();
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
        jugadores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public boolean eliminarJugador(String nombre) {
        return jugadores.removeIf(j -> j.getNombre().equals(nombre));
    }

    public Jugador buscarJugador(String nombre) {
        return jugadores.stream().filter(j -> j.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Equipo [nombre=" + nombre + ", jugadores=" + jugadores + "]";
    }

}

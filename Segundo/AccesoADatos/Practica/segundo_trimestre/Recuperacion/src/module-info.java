module your.module.name {
    exports logica;
    exports persistencia;
    exports tests;
    exports modelo;
	requires org.junit.jupiter.api;
	requires java.sql;
}

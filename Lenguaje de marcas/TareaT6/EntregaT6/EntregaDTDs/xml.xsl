<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <head>
                <link rel="stylesheet" type="text/css" href="style.css"/>
            </head>
            <body>
                <h2>Equipos de League of Legends</h2>
                <table border="1">
                    <tr>
                        <th>Equipo</th>
                        <th>Jugador</th>
                        <th>Rol</th>
                    </tr>
                    <xsl:for-each select="league_of_legends/equipos/equipo">
                        <xsl:for-each select="jugador">
                            <tr>
                                <td><xsl:value-of select="../@nombre"/></td>
                                <td><xsl:value-of select="nombre"/></td>
                                <td><xsl:value-of select="rol"/></td>
                            </tr>
                        </xsl:for-each>
                    </xsl:for-each>
                </table>
                <h2>Campeones de League of Legends</h2>
                <xsl:for-each select="league_of_legends/campeones/campeon">
                    <p>
                        <xsl:value-of select="@nombre"/>
                        (
                            <xsl:value-of select="rol"/>
                        )
                    </p>
                </xsl:for-each>
                <h2>Partidas de League of Legends</h2>
                <xsl:for-each select="league_of_legends/partidas/partida">
                    <p>
                        <xsl:value-of select="equipo"/> vs <xsl:value-of select="oponente"/>:
                        <xsl:choose>
                            <xsl:when test="resultado = 'Ganado'">
                                <span class="green"><xsl:value-of select="resultado"/></span>
                            </xsl:when>
                            <xsl:otherwise>
                                <span class="red"><xsl:value-of select="resultado"/></span>
                            </xsl:otherwise>
                        </xsl:choose>
                    </p>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" />

    <xsl:template match="/">
        <html>
            <head>
                <title>Continentes</title>
                <link rel="stylesheet" type="text/css" href="style.css" />
            </head>
            <body>
                <div class="bloque">
                    <h1 class="continentes">Continentes</h1>
                    <div class="listas">
                        <ul>
                            <xsl:for-each select="//continentes/continente">
                                <li>
                                    <h2>
                                        <xsl:value-of select="@nombre" />
                                    </h2>
                                    <ul>
                                        <xsl:for-each select="pais">
                                            <li>
                                                <xsl:value-of select="." />
                                            </li>
                                        </xsl:for-each>
                                    </ul>
                                </li>
                            </xsl:for-each>
                        </ul>
                    </div>
                    <h1 class="tituloTabla">Países del mundo</h1>
                    <table border="1">
                        <tr class="cabeceraTabla">
                            <th>Nombre</th>
                            <th>Sistema</th>
                            <th>Superficie</th>
                            <th>Moneda</th>
                            <th>Idioma</th>
                        </tr>
                        <xsl:for-each select="//paises/pais">
                            <tr>
                                <td>
                                    <xsl:value-of select="@nombre" />
                                </td>
                                <td>
                                    <xsl:value-of select="sistema" />
                                </td>
                                <td>
                                    <xsl:value-of select="superficie" />
                                </td>
                                <td>
                                    <xsl:value-of select="moneda" />
                                </td>
                                <td>
                                    <xsl:value-of select="idioma" />
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
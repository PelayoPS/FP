<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" />

  <xsl:template match="/">
    <html>
      <body>
        <h2>Lista de autores ordenados por apellido</h2>
        <xsl:for-each select="biblioteca/libro/autor">
          <xsl:sort select="apellido" />
                    <div>
            <xsl:number value="position()" format="1. " />
            <xsl:value-of select="concat(apellido, nombre)" />
          </div>
        </xsl:for-each>
        <h2>Lista de libros con precio menor a 100</h2>
        <xsl:for-each select="biblioteca/libro[precio &lt; 100]">
          <div>
            <xsl:number value="position()" format="1. " />
            <xsl:value-of select="titulo" />
          </div>
        </xsl:for-each>
        <h2>Lista de libros</h2>
        <table border="1">
          <tr>
            <th>Año de publicación</th>
            <th>Precio</th>
            <th>Título</th>
          </tr>
          <xsl:for-each select="biblioteca/libro">
            <xsl:sort select="@año" />
                        <tr>
              <td style="text-align:center;">
                <xsl:value-of select="@año" />
              </td>
              <td style="font-style:italic;">
                <xsl:value-of select="precio" />
              </td>
              <td>
                <xsl:choose>
                  <xsl:when test="precio > 100">
                    <div style="background-color:red;">
                      <xsl:value-of select="titulo" />
                    </div>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:value-of select="titulo" />
                  </xsl:otherwise>
                </xsl:choose>
              </td>
            </tr>
          </xsl:for-each>
        </table>
        <h2>Lista de libros</h2>
        <table border="1">
          <tr>
            <th>Título</th>
            <th>Autores</th>
            <th>Editores</th>
          </tr>
          <xsl:for-each select="biblioteca/libro">
            <xsl:sort select="titulo" />
                        <tr>
              <td>
                <xsl:choose>
                  <xsl:when test="precio > 100">
                    <span style="color:red;">
                      <xsl:value-of select="concat(titulo, ' (Caro)')" />
                    </span>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:value-of select="titulo" />
                  </xsl:otherwise>
                </xsl:choose>
              </td>
              <td>
                <xsl:for-each select="autor">
                  <xsl:value-of select="concat(nombre, ' ', apellido)" />
                                    <xsl:if
                    test="position() != last()">
                    <br />
                  </xsl:if>
                </xsl:for-each>
              </td>
              <td>
                <xsl:for-each select="editor">
                  <xsl:value-of select="concat(nombre, ' ', apellido, ' (', afiliacion, ')')" />
                                    <xsl:if
                    test="position() != last()">
                    <br />
                  </xsl:if>
                </xsl:for-each>
              </td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
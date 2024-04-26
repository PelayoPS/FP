<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/">
  <html>
    <head>
      <link rel="stylesheet" type="text/css" href="style.css"/>
    </head>
    <body>
    <h1>Florencia Capital del arte</h1>
    <div class="container">
      <div class="elemento">
      <h2>Museos de Florencia</h2>
      <table>
        <tr>
          <th>Nombre</th>
          <th>Arquitecto</th>
          <th>Construcción</th>
        </tr>
        <xsl:for-each select="//museos/museo">
          <tr>
            <td><xsl:value-of select="@nombre"/></td>
            <td><xsl:value-of select="arquitecto"/></td>
            <td><xsl:value-of select="construccion"/></td>
          </tr>
        </xsl:for-each>
      </table>
      </div>
      <div class="elemento">
      <h2>Artistas florencianos</h2>
      <xsl:for-each select="//artistas/artista">
      <p>
         <xsl:value-of select="nombre"/>
         (
            <xsl:value-of select="nacimiento"/>
         -
            <xsl:value-of select="muerte"/>
        )
            
      </p>
      </xsl:for-each>
      </div>
      <div class="elemento">
      <h2>Obras</h2>
      <ul>
             <xsl:for-each select="//obras/obra">
             <li><xsl:value-of select="text()"/></li>
             <ul>
                 <li>Autor : <xsl:value-of select="@autor"/></li>
                 <li>Museo : <xsl:value-of select="@museo"/></li>
             </ul>
             </xsl:for-each>
      </ul>
      </div>
      </div>
    </body>
  </html>
  </xsl:template>
</xsl:stylesheet>

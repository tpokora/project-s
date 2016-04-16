<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <rss>
            <source>
                <xsl:value-of select="rss/channel/link" />
            </source>
            <lastUpdate></lastUpdate>
            <xsl:apply-templates select="rss/channel/item" />
        </rss>
    </xsl:template>

    <xsl:template match="item">
        <feed>
            <title>
                <xsl:value-of select="title"/>
            </title>
            <link>
                <xsl:value-of select="link"/>
            </link>
        </feed>
    </xsl:template>
</xsl:stylesheet>
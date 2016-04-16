<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <rss>
            <source>
                <xsl:value-of select="feed/title" />
            </source>
            <lastUpdate>
                <xsl:value-of select="feed/updated" />
            </lastUpdate>
            <xsl:apply-templates select="feed/entry" />
        </rss>
    </xsl:template>

    <xsl:template match="entry">
        <feed>
            <title>
                <xsl:value-of select="title"/>
            </title>
            <link>
                <xsl:value-of select="link[@rel='alternate']/@href"/>
            </link>
        </feed>
    </xsl:template>
</xsl:stylesheet>
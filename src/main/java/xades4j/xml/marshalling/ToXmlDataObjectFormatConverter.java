/*
 * XAdES4j - A Java library for generation and verification of XAdES signatures.
 * Copyright (C) 2010 Luis Goncalves.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 */
package xades4j.xml.marshalling;

import xades4j.properties.data.PropertyDataObject;
import java.util.Collection;
import xades4j.properties.ObjectIdentifier;
import xades4j.properties.data.DataObjectFormatData;
import xades4j.xml.bind.xades.XmlDataObjectFormatType;
import xades4j.xml.bind.xades.XmlDocumentationReferencesType;
import xades4j.xml.bind.xades.XmlObjectIdentifierType;
import xades4j.xml.bind.xades.XmlSignedPropertiesType;

/**
 *
 * @author Luís
 */
class ToXmlDataObjectFormatConverter implements SignedPropertyDataToXmlConverter
{
    @Override
    public void convertIntoObjectTree(
            PropertyDataObject propData,
            XmlSignedPropertiesType xmlProps)
    {
        DataObjectFormatData dataObjFormatData = (DataObjectFormatData)propData;

        XmlDataObjectFormatType xmlDataObjFormatProp = new XmlDataObjectFormatType();
        xmlDataObjFormatProp.setObjectReference(dataObjFormatData.getObjectRef());
        xmlDataObjFormatProp.setDescription(dataObjFormatData.getDescription());
        xmlDataObjFormatProp.setMimeType(dataObjFormatData.getMimeType());
        xmlDataObjFormatProp.setEncoding(dataObjFormatData.getEncoding());
        xmlDataObjFormatProp.setObjectIdentifier(getXmlObjId(dataObjFormatData));

        xmlProps.getSignedDataObjectProperties().getDataObjectFormat().add(xmlDataObjFormatProp);
    }

    private XmlObjectIdentifierType getXmlObjId(
            DataObjectFormatData dataObjFormatData)
    {
        ObjectIdentifier identifier = dataObjFormatData.getIdentifier();
        if (null == identifier)
            return null;

        XmlObjectIdentifierType xmlObjId = ToXmlUtils.getXmlObjectId(identifier);

        // Documentation references
        Collection<String> docsUris = dataObjFormatData.getDocumentationUris();
        if (docsUris != null && docsUris.size() != 0)
        {
            XmlDocumentationReferencesType docRefs = new XmlDocumentationReferencesType();
            docRefs.getDocumentationReference().addAll(docsUris);
            xmlObjId.setDocumentationReferences(docRefs);
        }

        return xmlObjId;
    }
}
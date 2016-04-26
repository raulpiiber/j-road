/**
 * Copyright 2015 Nortal Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 **/

package com.nortal.jroad.wsdl;

import java.util.HashMap;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.Part;
import javax.xml.namespace.QName;

import org.springframework.ws.wsdl.wsdl11.provider.SuffixBasedMessagesProvider;
import org.w3c.dom.Element;

/**
 * X-Road specific implementation of strategy for adding messages to a <code>Definition</code>, whose
 * {@link XTeeMessagesProvider#populatePart(Definition, Part, QName)} creates messages according to schema elements with
 * suffix Request/Response. Used by {@link XTeeWsdlDefinition}.
 * 
 * @author Lauri Lättemäe (lauri.lattemae@nortal.com) - protocol 4.0
 */
// TODO Lauri: maybe we can remove it or we want to remove Request suffix from input names as it seems to be needed by
// xroad doc?
public class XTeeMessagesProvider extends SuffixBasedMessagesProvider {
  private final Map<String, QName> elementTypeMap = new HashMap<String, QName>();
  private int nscounter = 1;

  // @Override
  // protected void populatePart(Definition definition, Part part, QName elementName) throws WSDLException {
  // QName typeName = elementTypeMap.get(elementName.getLocalPart());
  // part.setTypeName(typeName);
  // part.setName(elementName.getLocalPart());
  // if (definition.getPrefix(typeName.getNamespaceURI()) == null) {
  // definition.addNamespace("xns" + nscounter++, typeName.getNamespaceURI());
  // }
  // }

  @Override
  protected boolean isMessageElement(Element element) {
    boolean result = super.isMessageElement(element);
    if (result) {
      String[] prefixAndType = element.getAttribute("type").split(":");
      String ns;
      String name;
      if (prefixAndType.length > 1) {
        ns = element.lookupNamespaceURI(prefixAndType[0]);
        name = prefixAndType[1];
      } else {
        ns = element.lookupNamespaceURI(null);
        name = prefixAndType[0];
      }
      elementTypeMap.put(element.getAttribute("name"), ns == null ? new QName(name) : new QName(ns, name));
    }
    return result;
  }
}

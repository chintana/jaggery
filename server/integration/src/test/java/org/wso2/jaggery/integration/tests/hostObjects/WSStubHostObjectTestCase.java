/*
*  Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.wso2.jaggery.integration.tests.hostObjects;

import org.custommonkey.xmlunit.XMLAssert;
import org.testng.annotations.Test;
import org.wso2.carbon.integration.framework.ClientConnectionUtil;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static org.testng.Assert.assertNotNull;

/**
 * Test cases for WSstub Host Object
 */
public class WSStubHostObjectTestCase {

    @Test(groups = {"jaggery"},
            description = "Test for WSRequest host object existing")
    public void testWSStubExist() {
        ClientConnectionUtil.waitForPort(9763);

        String finalOutput = "";

        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/wsstub.jag");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    jaggeryServerConnection.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                finalOutput += inputLine;
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assertNotNull(finalOutput, "Result cannot be null");
        }

    }

    @Test(groups = {"jaggery"},
            description = "Test for WSstub host object")
    public void testWSStub() throws IOException, SAXException {
        ClientConnectionUtil.waitForPort(9763);

        String finalOutput = "";

        try {
            URL jaggeryURL = new URL("http://localhost:9763/testapp/wsstub.jag");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    jaggeryServerConnection.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                finalOutput += inputLine;
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            XMLAssert.assertXMLEqual(finalOutput, "<ns:getVersionResponse xmlns:ns=\"http://version.services.core.carbon.wso2.org\">  <return>WSO2 Stratos Manager-2.0.2</return></ns:getVersionResponse>");
        }

    }


}

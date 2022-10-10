/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package io.quarkiverse.myext.it;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/myext")
@ApplicationScoped
public class MyextResource {

    private static final Logger log = LoggerFactory.getLogger(MyextResource.class);

    @GET
    public String hello() {

        List<String> errors = new ArrayList<>();
        ser(Collections.singleton("hello"), errors);
        ser(Set.of("hello"), errors);
        return errors.toString();
    }

    private void ser(Object obj, List<String> errors) {
        try {
            try (
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos)) {

                oos.writeObject(obj);
                oos.flush();

                try (
                        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                        ObjectInputStream ois = new ObjectInputStream(bais)) {

                    ois.readObject();
                }
            }
        } catch (Throwable e) {
            log.error("serialization test error", e);
            errors.add("KO: " + e.getMessage());
        }
    }
}

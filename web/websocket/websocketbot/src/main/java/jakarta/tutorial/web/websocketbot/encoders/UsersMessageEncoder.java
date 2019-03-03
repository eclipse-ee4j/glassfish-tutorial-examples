/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.web.websocketbot.encoders;

import jakarta.tutorial.web.websocketbot.messages.UsersMessage;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/* Encode a UsersMessage as JSON.
 * For example, 
 *   List<String> list = new ArrayList<>();
 *   list.add("Peter");
 *   list.add("Duke");
 *   new UsersMessage(list)
 * is encoded as follows:
 * {
 *   "type": "users",
 *   "userlist": [ "Peter", "Duke" ]
 * }
 */
public class UsersMessageEncoder implements Encoder.Text<UsersMessage> {
    @Override
    public void init(EndpointConfig ec) { }
    
    @Override
    public void destroy() { }
    
    @Override
    public String encode(UsersMessage usersMessage) throws EncodeException {
        StringWriter swriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
            jsonGen.writeStartObject()
                .write("type", "users")
                .writeStartArray("userlist");
            for (String user : usersMessage.getUserList())
                jsonGen.write(user);
            jsonGen.writeEnd().writeEnd();
        }
        return swriter.toString();
    }
}

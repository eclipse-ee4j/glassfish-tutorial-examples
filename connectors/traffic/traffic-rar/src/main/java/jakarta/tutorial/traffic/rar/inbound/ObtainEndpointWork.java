/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.traffic.rar.inbound;

import java.util.logging.Logger;
import jakarta.tutorial.traffic.rar.TrafficResourceAdapter;
import javax.resource.spi.UnavailableException;
import javax.resource.spi.endpoint.MessageEndpoint;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.resource.spi.work.Work;

/* This class is required only because obtaining an MDB endpoint
 * needs to be done in a different thread */
public class ObtainEndpointWork implements Work {

    private static final Logger log = Logger.getLogger("ObtainEndpointWork");
    private TrafficResourceAdapter ra;
    private MessageEndpointFactory mef;
    private MessageEndpoint endpoint;
    
    public ObtainEndpointWork(TrafficResourceAdapter ra, 
                              MessageEndpointFactory mef) {
        this.mef = mef;
        this.ra = ra;
    }
    
    public MessageEndpoint getMessageEndpoint() {
        return endpoint;
    }

    @Override
    public void run() {
        log.info("[ObtainEndpointWork] run()");
        try {
            /* Use the endpoint factory passed by the container upon
             * activation to obtain the MDB endpoint */
            endpoint = mef.createEndpoint(null);
            /* Return back to the resource adapter class */
            ra.endpointAvailable(endpoint);
        } catch (UnavailableException ex ) {
            log.info(ex.getMessage());
        }
    }
    
    @Override
    public void release() { }
    
}

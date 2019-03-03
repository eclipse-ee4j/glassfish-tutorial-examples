/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.batch.webserverlog;

import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.tutorial.batch.webserverlog.items.LogLine;
import javax.batch.api.chunk.listener.ItemProcessListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("InfoItemProcessListener")
public class InfoItemProcessListener implements ItemProcessListener {
    
    private static final Logger logger = 
            Logger.getLogger("InfoItemProcessListener");
    
    public InfoItemProcessListener() { }

    @Override
    public void beforeProcess(Object o) throws Exception {
        LogLine logline = (LogLine) o;
        logger.log(Level.INFO, "Processing entry {0}", logline);
    }

    @Override
    public void afterProcess(Object o, Object o1) throws Exception { }

    @Override
    public void onProcessError(Object o, Exception excptn) throws Exception {
        LogLine logline = (LogLine) o;
        logger.log(Level.WARNING, "Error processing entry {0}", logline);
    }
    
}

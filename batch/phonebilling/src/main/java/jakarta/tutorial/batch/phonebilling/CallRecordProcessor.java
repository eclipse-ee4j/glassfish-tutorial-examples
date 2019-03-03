/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.batch.phonebilling;

import java.math.BigDecimal;
import jakarta.tutorial.batch.phonebilling.items.CallRecord;
import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/* Processor batch artifact.
 * Calculate the price of every call.
 */
@Dependent
@Named("CallRecordProcessor")
public class CallRecordProcessor implements ItemProcessor {
    
    @Inject
    JobContext jobCtx;
    double airPrice;
    
    public CallRecordProcessor() { }

    @Override
    public Object processItem(Object obj) throws Exception {
        CallRecord call;
        double callPrice;
        
        /* Calculate the price of this call */
        String s_airPrice = jobCtx.getProperties().getProperty("airtime_price");
        airPrice = Double.parseDouble(s_airPrice);
        call = (CallRecord) obj;
        callPrice = airPrice*(1.0*call.getMinutes() + call.getSeconds()/60.0);
        call.setPrice(new BigDecimal(callPrice));
        return call;
    }
    
}

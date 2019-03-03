/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.batch.phonebilling.items;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/* This class is a Java Persistence API entity that
 * represents a phone bill in the batch application.
 */
@Entity
public class PhoneBill implements Serializable {

    @Id
    private String phoneNumber;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @OrderBy("datetime ASC")
    private List<CallRecord> calls;
    private BigDecimal amountBase;
    private BigDecimal taxRate;
    private BigDecimal tax;
    private BigDecimal amountTotal;
    private static final long serialVersionUID = -7617311744533479326L;

    public PhoneBill() {
    }
    
    public PhoneBill(String number) {
        this.phoneNumber = number;
        calls = new ArrayList<>();
    }
    
    public void addCall(CallRecord call) {
        calls.add(call);
    }
    
    public void calculate(BigDecimal taxRate) {
        /* Compute the total amount and tax */
        this.taxRate = taxRate;
        amountBase = new BigDecimal(0).setScale(2);
        for (CallRecord call : calls) {
            amountBase = amountBase.add(call.getPrice());
        }
        tax = amountBase.multiply(taxRate).setScale(2, RoundingMode.HALF_EVEN);
        amountTotal = amountBase.add(tax);
    }
    
    public String getPhoneNumber() { return phoneNumber; }
    public List<CallRecord> getCalls() { return calls; }
    public BigDecimal getAmountBase() { return amountBase; }
    public BigDecimal getTax() { return tax; }
    public BigDecimal getTaxRate() { return taxRate; }
    public BigDecimal getAmountTotal() { return amountTotal; }
}

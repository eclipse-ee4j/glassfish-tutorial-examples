/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.roster.entity;

import java.io.Serializable;
import jakarta.tutorial.roster.util.IncorrectSportException;
import javax.persistence.Entity;

@Entity
public class SummerLeague extends League implements Serializable {
    private static final long serialVersionUID = 4846138039113922695L;
    
    /** Creates a new instance of SummerLeague */
    public SummerLeague() {
    }
    
    public SummerLeague(String id, String name, String sport) 
            throws IncorrectSportException {
        this.id = id;
        this.name = name;
        if (sport.equalsIgnoreCase("swimming") ||
                sport.equalsIgnoreCase("soccer") ||
                sport.equalsIgnoreCase("basketball") ||
                sport.equalsIgnoreCase("baseball")) {
            this.sport = sport;
        } else {
            throw new IncorrectSportException("Sport is not a summer sport.");
        }
    }
}

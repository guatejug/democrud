/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherwin.controller.faulttolerance;

import java.util.Arrays;
import java.util.List;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

/**
 *
 * @author tuxtor
 */
public class MoviesFailureHandler
        implements FallbackHandler<List>{

    @Override
    public List handle(ExecutionContext context) {
        return Arrays.asList("Dulce noviembre",
                "Rambo I",
                "Los verduleros I",
                "Los verduleros II: La venganza");
    }
}
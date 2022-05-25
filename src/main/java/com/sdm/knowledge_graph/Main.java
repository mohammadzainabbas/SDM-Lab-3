package com.sdm.knowledge_graph;

import com.sdm.knowledge_graph.ontology.TBOX;
import com.sdm.knowledge_graph.ontology.ABOX;

import com.sdm.knowledge_graph.common.utils;

/**
 * Main class
 */
public class Main 
{
    public static void main( String[] args )
    {
        utils.log( "Hola from the main class!" );
        utils.log( "args: " + String.join(" ", args) );
        utils.log( "args.length: " + args.length );
        
        if (args.length != 1) {
            utils.error("Parameter expected: Pass 'tbox' or 'abox' for creating TBOX and ABOX respectively");
        }

        if (args[0].equals("tbox")) {
            TBOX.createAndSaveTBOX();
        } 
        else if (args[0].equals("abox")) {
            ABOX.createAndSaveABOX();
        } 

    }
}

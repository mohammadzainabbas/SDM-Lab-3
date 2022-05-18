package com.sdm.knowledge_graph;

import com.sdm.knowledge_graph.ontology.TBOX;
import com.sdm.knowledge_graph.common.utils;

/**
 * Main class
 */
public class Main 
{
    public static void main( String[] args )
    {
        utils.print( "Hola from the main class!" );
        utils.print( "args: " + String.join(" ", args) );
        utils.print( "args.length: " + args.length );
        
        if (args.length != 1) {
            utils.error("Parameter expected: Pass 'tbox' or 'abox' for creating TBOX and ABOX respectively");
        }

        if (args[0].equals("tbox")) {
            TBOX.createAndSaveTBOX();
        }

    }
}

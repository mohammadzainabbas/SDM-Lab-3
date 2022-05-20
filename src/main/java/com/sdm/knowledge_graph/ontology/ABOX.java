package com.sdm.knowledge_graph.ontology;

import java.io.FileOutputStream;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import com.sdm.knowledge_graph.common.constants;

public class ABOX {
    public static final String FILE_PATH = "src/main/resources/file.csv";
    
    public static final String OUTPUT_PATH = "src/main/resources/out/";

    public static final String BASE_URL = "http://www.semanticweb.org/";
    public static final String PROPERTY_URL = BASE_URL+"property/";
    public static final String RESOURCE_URL = BASE_URL+"resource/";

    // public static void createPerson() throws IOException {
    //     Model model = ModelFactory.createDefaultModel();

    //     // Read the csv line by line
    //     BufferedReader csvReader = new BufferedReader(new FileReader(FILE_PATH));
    //     String row;
    //     while ((row = csvReader.readLine()) != null) {
    //         String[] row_data = row.split(";");
    //         String[] names = row_data[0].split(" ");
    //         String[] papers = row_data[1].split("\\|");

    //         String lastName = names[names.length-1];

    //         String firstName = names[0];
    //         String personUri = names[0];
    //         for(String name:names){
    //             if(!(name.equals(lastName) || name.equals(firstName))){
    //                 firstName += " "+name;
    //                 personUri += "_"+name;
    //             }
    //         }

    //         String reviewedPaper = row_data[4];
    //         personUri = RESOURCE_URL + personUri + lastName;

    //         String workplaceUri = RESOURCE_URL+row_data[2].replace(" ","_");

    //         Resource currentPerson = model.createResource(personUri)
    //                 .addProperty(FOAF.firstName, firstName)
    //                 .addProperty(FOAF.lastName, lastName)
    //                 .addProperty(model.createProperty(Config.PROPERTY_URL+"works_in"),model.createResource(workplaceUri));

    //         for(String paper:papers){
    //             currentPerson.addProperty(model.createProperty(Config.PROPERTY_URL+"writes"), model.createResource(Config.RESOURCE_URL+paper.replace("/","_")));
    //         }
    //         if(!(reviewedPaper.equals("N/A"))){
    //             for(String paper:reviewedPaper.split("\\|")){
    //                 currentPerson.addProperty(model.createProperty(Config.PROPERTY_URL+"reviews"),
    //                         model.createResource(Config.RESOURCE_URL+row_data[0].replace(" ","_")+"_"+paper
    //                                 .replace("/","_")));
    //             }
    //         }

    //     }
    //     csvReader.close();
    //     model.write(new PrintStream(
    //             new BufferedOutputStream(
    //                     new FileOutputStream(OUTPUT_PATH+"person.nt")), true), "NT");
    // }


    public static void createAndSaveABOX() {

        Model m = ModelFactory.createDefaultModel().read(constants.MODEL_PATH);
        OntModel model = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM_RDFS_INF, m );

        model.write(System.out);

    }
}

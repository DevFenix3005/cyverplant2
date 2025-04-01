package com.cyver.plant.database.customejooq;

import static org.jooq.codegen.GeneratorStrategy.Mode.DAO;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class CyverPlantNameStrategy extends DefaultGeneratorStrategy {

    public String getJavaClassName(Definition definition, Mode mode) {
        final String name = super.getJavaClassName(definition, mode);
        if (mode == DAO) {
            return name.replace("Dao", "Repository");
        }
        return name;
    }

}

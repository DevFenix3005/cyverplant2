package com.cyver.plant.database.customjooq;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

import io.github.encryptorcode.pluralize.Pluralize;

public class CyverPlantNameStrategy extends DefaultGeneratorStrategy {

    private static final String DAO_SUFFIX = "Dao";

    private static final String REPOSITORY_SUFFIX = "Repository";

    private static final String DAOS_SUFFIX = "daos";

    private static final String REPOSITORIES_SUFFIX = "repositories";

    private static final String POJOS_SUFFIX = "pojos";

    private static final String DTOS_SUFFIX = "dtos";

    @Override
    public String getJavaPackageName(final Definition definition, final Mode mode) {
        final String name = super.getJavaPackageName(definition, mode);
        return switch (mode) {
            case DAO -> name.replace(DAOS_SUFFIX, REPOSITORIES_SUFFIX);
            case POJO -> name.replace(POJOS_SUFFIX, DTOS_SUFFIX);
            default -> name;
        };
    }

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        final String name = super.getJavaClassName(definition, mode);
        return switch (mode) {
            case DAO -> Pluralize.singular(name.replace(DAO_SUFFIX, "")) + REPOSITORY_SUFFIX;
            case POJO -> Pluralize.singular(name);
            default -> name;
        };
    }



}

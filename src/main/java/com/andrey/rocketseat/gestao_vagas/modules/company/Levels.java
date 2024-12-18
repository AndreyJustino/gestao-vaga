package com.andrey.rocketseat.gestao_vagas.modules.company;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Levels {
    ESTAGIARIO,
    JUNIOR,
    PLENO,
    SENIOR;

    @JsonCreator
    public static Levels empty(String level){
        if (level == null || level.trim().isEmpty()){
            return null;
        }

        return Levels.valueOf(level.toUpperCase());
    }

}

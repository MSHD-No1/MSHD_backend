package com.earthquake.managementPlatform.entities;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class GradeEvaluationForBrickwoodStructure implements GradeEvaluation {
    @Override
    public String gradeEvaluation(JSONObject data) {
        double basicallyIntactSquare = data.getDouble("basicallyIntactSquare");
        double damagedSquare = data.getDouble("damagedSquare");
        double destroyedSquare = data.getDouble("destroyedSquare");
        double value = 1 - basicallyIntactSquare / (basicallyIntactSquare + damagedSquare + destroyedSquare);
        String gradeCode = "0";
        if (value >= 0 && value < 0.25) {
            gradeCode = "1";
        } else if (value < 0.5) {
            gradeCode = "2";
        } else if (value < 0.75) {
            gradeCode = "3";
        } else if (value <= 1) {
            gradeCode = "4";
        }
        return gradeCode;
    }
}
